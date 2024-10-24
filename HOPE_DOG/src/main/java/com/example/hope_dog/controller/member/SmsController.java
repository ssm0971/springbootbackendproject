package com.example.hope_dog.controller.member;

import com.example.hope_dog.service.member.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sms")
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> sendSms(@RequestBody Map<String, String> request) {
        try {
            String phoneNumber = request.get("phoneNumber");
            String verificationCode = smsService.sendVerificationMessage(phoneNumber);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "인증번호가 발송되었습니다."
            ));
        } catch (Exception e) {
            log.error("SMS 발송 실패: ", e);
            return ResponseEntity.ok(Map.of(
                    "success", false,
                    "message", "인증번호 발송에 실패했습니다."
            ));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifySms(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        String code = request.get("code");

        boolean isValid = smsService.verifyCode(phoneNumber, code);

        return ResponseEntity.ok(Map.of(
                "success", isValid,
                "message", isValid ? "인증이 완료되었습니다." : "인증번호가 일치하지 않습니다."
        ));
    }
}