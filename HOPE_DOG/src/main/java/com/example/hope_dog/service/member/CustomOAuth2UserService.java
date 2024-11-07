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

/**
 * OAuth2 소셜 로그인 처리를 위한 커스텀 서비스 클래스
 * 네이버와 카카오 로그인을 지원
 */
@Service // 스프링의 서비스 컴포넌트로 등록
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
@Slf4j // 로깅 기능 활성화
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    // 필요한 의존성 주입
    private final MemberService memberService;        // 회원 서비스
    private final HttpSession session;                // 세션 관리

    /**
     * OAuth2 인증 후 사용자 정보를 로드하는 메소드
     * 소셜 로그인 제공자(네이버/카카오)로부터 받은 사용자 정보를 처리
     *
     * @param userRequest OAuth2UserRequest 객체 (소셜 로그인 요청 정보 포함)
     * @return OAuth2User 인증된 사용자 정보
     * @throws OAuth2AuthenticationException 인증 과정에서 발생하는 예외
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        try {
            // 소셜 로그인 제공자 식별
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            Map<String, Object> attributes = oauth2User.getAttributes();

            // 사용자 정보를 저장할 변수 선언
            String email;
            String nickname;
            String providerId;

            // 네이버 로그인 처리
            if ("naver".equals(registrationId)) {
                Map<String, Object> response = (Map<String, Object>) attributes.get("response");
                email = (String) response.get("email");
                nickname = (String) response.get("name");
                providerId = (String) response.get("id");
            }
            // 카카오 로그인 처리
            else if ("kakao".equals(registrationId)) {
                Long id = (Long) attributes.get("id");
                Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
                Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

                email = (String) kakaoAccount.get("email");
                nickname = (String) properties.get("nickname");
                providerId = String.valueOf(id);
            }
            // 지원하지 않는 소셜 로그인 제공자
            else {
                throw new OAuth2AuthenticationException("Unsupported provider");
            }

            // 이메일로 기존 회원 확인
            MemberDTO existingMember = memberService.findByEmail(email);

            // 기존 회원 처리
            if (existingMember != null) {
                // 세션에 회원 정보 저장
                setSessionAttributes(existingMember, registrationId);
            }
            // 신규 회원 처리
            else {
                // 추가 정보 입력을 위해 임시 데이터 세션에 저장
                session.setAttribute("tempEmail", email);
                session.setAttribute("tempNickname", nickname);
                session.setAttribute("tempProviderId", providerId);
                session.setAttribute("provider", registrationId);
                session.setAttribute("needAdditionalInfo", true);
            }

            // OAuth2User 객체 생성 및 반환
            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), // 기본 사용자 권한 설정
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

    /**
     * 회원 정보를 세션에 저장하는 private 메소드
     *
     * @param member 회원 정보 객체
     * @param provider 소셜 로그인 제공자 (NAVER/KAKAO)
     */
    private void setSessionAttributes(MemberDTO member, String provider) {
        // 세션에 회원 정보 저장
        session.setAttribute("memberNo", member.getMemberNo());           // 회원번호
        session.setAttribute("memberId", member.getMemberId());          // 회원ID
        session.setAttribute("memberName", member.getMemberName());      // 회원이름
        session.setAttribute("memberNickname", member.getMemberNickname()); // 닉네임
        session.setAttribute("memberEmail", member.getMemberEmail());    // 이메일
        session.setAttribute("memberLoginStatus", provider.toUpperCase()); // 로그인 상태
        session.setAttribute("memberTwoFactorEnabled", member.getMemberTwoFactorEnabled()); // 2차 인증 활성화 여부
    }
}