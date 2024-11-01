package com.example.hope_dog.service.member;

import com.example.hope_dog.dto.member.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        try {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            Map<String, Object> attributes = oauth2User.getAttributes();

            String email;
            String nickname;
            String providerId;

            if ("naver".equals(registrationId)) {
                Map<String, Object> response = (Map<String, Object>) attributes.get("response");
                email = (String) response.get("email");
                nickname = (String) response.get("name");
                providerId = (String) response.get("id");
            } else if ("kakao".equals(registrationId)) {
                Long id = (Long) attributes.get("id");
                Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
                Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

                email = (String) kakaoAccount.get("email");
                nickname = (String) properties.get("nickname");
                providerId = String.valueOf(id);
            } else {
                throw new OAuth2AuthenticationException("Unsupported provider");
            }

            // 이메일로 기존 회원 확인
            MemberDTO existingMember = memberService.findByEmail(email);

            if (existingMember != null) {
                // 기존 회원이면 바로 세션 설정
                setSessionAttributes(existingMember, registrationId);
            } else {
                // 신규 회원이면 추가 정보 입력 필요
                session.setAttribute("tempEmail", email);
                session.setAttribute("tempNickname", nickname);
                session.setAttribute("tempProviderId", providerId);
                session.setAttribute("provider", registrationId);
                session.setAttribute("needAdditionalInfo", true);
            }

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    attributes,
                    userRequest.getClientRegistration()
                            .getProviderDetails()
                            .getUserInfoEndpoint()
                            .getUserNameAttributeName()
            );
        } catch (Exception e) {
            log.error("Error in loadUser: ", e);
            throw new OAuth2AuthenticationException("Error in OAuth2 login process");
        }
    }

    private void setSessionAttributes(MemberDTO member, String provider) {
        session.setAttribute("memberNo", member.getMemberNo());
        session.setAttribute("memberId", member.getMemberId());
        session.setAttribute("memberName", member.getMemberName());
        session.setAttribute("memberNickname", member.getMemberNickname());
        session.setAttribute("memberEmail", member.getMemberEmail());
        session.setAttribute("memberLoginStatus", provider.toUpperCase());
        session.setAttribute("memberTwoFactorEnabled", member.getMemberTwoFactorEnabled());
    }
}