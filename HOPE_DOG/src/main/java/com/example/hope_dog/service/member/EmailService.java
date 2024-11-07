package com.example.hope_dog.service.member;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 이메일 발송을 담당하는 서비스 클래스
 * 회원에게 임시 비밀번호 등을 이메일로 전송하는 기능 제공
 */
@Service // 스프링의 서비스 컴포넌트로 등록
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
public class EmailService {

    // 이메일 발송을 위한 JavaMailSender 의존성 주입
    private final JavaMailSender emailSender;

    // application.properties/yml에서 설정한 이메일 주소를 주입
    @Value("${spring.mail.username}")
    private String senderEmail;

    /**
     * 임시 비밀번호를 이메일로 발송하는 메소드
     *
     * @param to 수신자 이메일 주소
     * @param tempPassword 발급된 임시 비밀번호
     * @throws RuntimeException 이메일 발송 실패 시 발생
     */
    public void sendTempPassword(String to, String tempPassword) {
        try {
            // MimeMessage 객체 생성
            MimeMessage message = emailSender.createMimeMessage();
            // 한글 깨짐 방지를 위한 UTF-8 인코딩 설정과 함께 Helper 클래스 생성
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // 이메일 기본 정보 설정
            helper.setFrom(senderEmail);  // 발신자 이메일 설정
            helper.setTo(to);             // 수신자 이메일 설정
            helper.setSubject("[희멍의집] 임시 비밀번호 발급");  // 이메일 제목 설정

            // HTML 형식의 이메일 본문 내용 구성
            String content =
                    "<div style='margin:20px;'>" +
                            "<h2>임시 비밀번호 발급</h2>" +
                            "<p>안녕하세요, 희멍의집입니다.</p>" +
                            "<p>요청하신 임시 비밀번호가 발급되었습니다.</p>" +
                            "<div style='margin:40px;'>" +
                            "<h3>임시 비밀번호 : <span style='color:blue;'>" + tempPassword + "</span></h3>" +
                            "</div>" +
                            "<p>보안을 위해 로그인 후 반드시 비밀번호를 변경해주세요.</p>" +
                            "<br>" +
                            "<p>감사합니다.</p>" +
                            "</div>";

            // 이메일 본문 설정 (두 번째 파라미터 true는 HTML 사용 가능하도록 설정)
            helper.setText(content, true);

            // 이메일 발송
            emailSender.send(message);

        } catch (MessagingException e) {
            // 이메일 발송 실패 시 RuntimeException으로 예외 전환하여 throw
            throw new RuntimeException("이메일 발송에 실패했습니다.", e);
        }
    }
}