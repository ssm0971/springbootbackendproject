package com.example.hope_dog.service.member;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendTempPassword(String to, String tempPassword) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail);
            helper.setTo(to);
            helper.setSubject("[희멍의집] 임시 비밀번호 발급");

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

            helper.setText(content, true);
            emailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("이메일 발송에 실패했습니다.", e);
        }
    }
}
