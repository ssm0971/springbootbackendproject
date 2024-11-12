package com.example.hope_dog.config;

import com.example.hope_dog.service.member.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 설정 클래스
 * 애플리케이션의 보안 설정을 정의
 *
 * @Configuration: 스프링의 설정 클래스임을 나타냄
 * @EnableWebSecurity: Spring Security 활성화
 *                    - 기본적인 웹 보안 기능 활성화
 *                    - WebSecurityConfiguration 임포트
 *                    - SecurityFilterChain 구성 클래스 설정
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Spring Security Filter Chain 설정
     * 애플리케이션의 보안 규칙과 인증/인가 설정을 정의
     *
     * @param http Spring Security의 HTTP 보안 설정을 위한 객체
     * @param customOAuth2UserService 커스텀 OAuth2 사용자 서비스
     * @return 구성된 SecurityFilterChain 객체
     * @throws Exception 보안 설정 중 발생할 수 있는 예외
     *
     * 주요 설정:
     * 1. CSRF 보호 비활성화
     * 2. 모든 요청에 대한 접근 허용
     * 3. 폼 로그인 설정
     * 4. OAuth2 로그인 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CustomOAuth2UserService customOAuth2UserService) throws Exception {
        http
                // CSRF 보호 비활성화
                // - REST API 서버는 CSRF 공격으로부터 안전
                // - 세션을 사용하지 않는 REST API의 경우 불필요

                .csrf(csrf -> csrf.disable())

                // HTTP 요청에 대한 인가 규칙 설정
                .authorizeHttpRequests(auth -> auth
                        // 모든 경로("/**")에 대해 접근 허용
                        .requestMatchers("/**").permitAll()
                )

                // 폼 로그인 설정
                .formLogin(form -> form
                        // 커스텀 로그인 페이지 경로 설정
                        .loginPage("/member/login-select")
                        // 로그인 페이지 접근 허용
                        .permitAll()
                )

                // OAuth2 로그인 설정
                .oauth2Login(oauth2 -> oauth2
                        // OAuth2 로그인 페이지 경로 설정
                        .loginPage("/member/login")
                        // UserInfo 엔드포인트 설정
                        .userInfoEndpoint(userInfo -> userInfo
                                // 커스텀 OAuth2 사용자 서비스 설정
                                .userService(customOAuth2UserService)
                        )
                        // 로그인 성공 핸들러 설정
                        .successHandler(oauth2LoginSuccessHandler())
                        // 로그인 실패 시 리다이렉트 URL 설정
                        .failureUrl("/member/login?error=true")
                );

        return http.build();
    }

    /**
     * OAuth2 로그인 성공 핸들러 빈 등록
     * 소셜 로그인 성공 후의 처리를 담당하는 핸들러
     *
     * @return OAuth2LoginSuccessHandler 인스턴스
     */
    @Bean
    public OAuth2LoginSuccessHandler oauth2LoginSuccessHandler() {
        return new OAuth2LoginSuccessHandler();
    }

    /**
     * 비밀번호 인코더 빈 등록
     * 사용자 비밀번호를 안전하게 해시화하기 위한 인코더
     *
     * @return BCryptPasswordEncoder 인스턴스
     *
     * BCryptPasswordEncoder 특징:
     * - 강력한 해시 함수 사용
     * - 솔트(salt)를 자동으로 생성하여 적용
     * - 높은 보안성 제공
     * - 같은 비밀번호도 매번 다른 해시값 생성
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

