package com.example.hope_dog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.context.annotation.Bean;

/**
 * 에러 페이지 설정을 위한 Configuration 클래스
 * 각각의 HTTP 상태 코드(403, 404, 500)에 대한 커스텀 에러 페이지를 지정
 */
@Configuration
public class ErrorConfig {

    /**
     * 웹 서버의 에러 페이지를 커스터마이징하는 Bean을 등록
     *
     * @Bean - Spring IoC 컨테이너에 의해 관리되는 Bean으로 등록
     *
     * @return WebServerFactoryCustomizer 객체
     *         - 웹 서버 팩토리를 커스터마이징할 수 있는 인터페이스 구현체
     *
     * WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
     * - WebServerFactoryCustomizer: 웹 서버의 설정을 커스터마이징할 수 있게 해주는 인터페이스
     * - ConfigurableServletWebServerFactory: 서블릿 기반 웹 서버의 설정을 담당하는 팩토리
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        // 람다식을 사용하여 컨테이너 설정을 커스터마이징
        return container -> {
            // 각 HTTP 상태 코드별 에러 페이지 설정

            // 403 Forbidden 에러 - 접근 권한이 없는 경우
            // ErrorPage 객체 생성 시 상태 코드와 리다이렉트할 경로를 지정
            ErrorPage error403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");

            // 404 Not Found 에러 - 요청한 페이지를 찾을 수 없는 경우
            ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");

            // 500 Internal Server Error - 서버 내부 에러 발생 시
            ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

            // 생성한 ErrorPage 객체들을 컨테이너에 추가
            // addErrorPages 메소드를 통해 여러 에러 페이지를 한 번에 등록
            container.addErrorPages(error403, error404, error500);
        };
    }
}