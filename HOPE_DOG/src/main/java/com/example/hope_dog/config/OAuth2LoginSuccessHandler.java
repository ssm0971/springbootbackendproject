package com.example.hope_dog.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * OAuth2 로그인 성공 후 처리를 담당하는 핸들러 클래스
 *
 * @Component: 스프링 컴포넌트로 등록하여 빈으로 관리
 *            - 다른 컴포넌트에서 주입받아 사용 가능
 *            - 스프링의 IoC 컨테이너에 의해 생명주기 관리
 *
 * @Slf4j: Lombok의 로깅 기능 활성화
 *         - 로그를 통한 디버깅 및 모니터링 가능
 *         - 로그 레벨 조정 가능
 *
 * AuthenticationSuccessHandler 구현 이유:
 * 1. 소셜 로그인 성공 후 추가 처리 로직 구현
 * 2. 사용자 정보 보완이 필요한 경우 추가 정보 입력 페이지로 리다이렉트
 * 3. 커스텀 로그인 플로우 구현
 */
@Component
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * OAuth2 로그인 성공 시 실행되는 메소드
     * 로그인 성공 후의 플로우를 제어
     *
     * @param request HTTP 요청 객체
     *                - 세션 정보 접근
     *                - 요청 관련 정보 획득
     *
     * @param response HTTP 응답 객체
     *                 - 리다이렉트 처리
     *                 - 응답 헤더 설정
     *
     * @param authentication 인증 정보 객체
     *                      - 인증된 사용자 정보 포함
     *                      - 권한 정보 포함
     *
     * @throws IOException 입출력 처리 중 발생할 수 있는 예외
     *
     * 처리 흐름:
     * 1. 세션에서 추가 정보 필요 여부 확인
     * 2. 추가 정보가 필요한 경우 추가 정보 입력 페이지로 리다이렉트
     * 3. 추가 정보가 불필요한 경우 메인 페이지로 리다이렉트
     * 4. 예외 발생 시 로그인 페이지로 리다이렉트
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 세션 객체 획득
        HttpSession session = request.getSession();

        try {
            // 추가 정보 필요 여부 로깅
            // 디버깅 및 모니터링을 위한 로그
            log.info("OAuth2 Login Success. Need additional info: {}",
                    session.getAttribute("needAdditionalInfo"));

            // 추가 정보 입력 필요 여부 확인
            // needAdditionalInfo 속성이 존재하면 추가 정보 입력이 필요한 상태
            if (session.getAttribute("needAdditionalInfo") != null) {
                // 추가 정보 입력 페이지로 리다이렉트
                response.sendRedirect("/member/additional-info");
                return;
            }

            // 추가 정보가 필요 없는 경우 메인 페이지로 리다이렉트
            response.sendRedirect("/main/main");

        } catch (Exception e) {
            // 예외 발생 시 로그 기록
            log.error("Error in success handler: ", e);
            // 에러 파라미터와 함께 로그인 페이지로 리다이렉트
            response.sendRedirect("/member/login?error=true");
        }
    }
}