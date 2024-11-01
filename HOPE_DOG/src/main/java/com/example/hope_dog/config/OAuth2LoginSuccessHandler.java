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

@Component
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        HttpSession session = request.getSession();

        try {
            log.info("OAuth2 Login Success. Need additional info: {}",
                    session.getAttribute("needAdditionalInfo"));

            if (session.getAttribute("needAdditionalInfo") != null) {
                response.sendRedirect("/member/additional-info");
                return;
            }

            response.sendRedirect("/main/main");
        } catch (Exception e) {
            log.error("Error in success handler: ", e);
            response.sendRedirect("/member/login?error=true");
        }
    }
}