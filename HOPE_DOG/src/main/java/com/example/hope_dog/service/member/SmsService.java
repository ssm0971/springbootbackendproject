package com.example.hope_dog.service.member;

import com.example.hope_dog.domain.VerificationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {

    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    @Value("${coolsms.from.number}")
    private String fromNumber;

    // 인증번호 저장소
    private final ConcurrentHashMap<String, VerificationInfo> verificationStore = new ConcurrentHashMap<>();

    public String sendVerificationMessage(String to) throws CoolsmsException {
        Message coolsms = new Message(apiKey, apiSecret);

        String verificationCode = generateVerificationCode();
        String message = String.format("[희멍의집] 인증번호 [%s]를 입력해주세요.", verificationCode);

        // Cool SMS 메시지 발송
        HashMap<String, String> params = new HashMap<>();
        params.put("to", to);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", message);

        coolsms.send(params);

        // 인증번호 저장 (3분 후 만료)
        verificationStore.put(to, new VerificationInfo(verificationCode, System.currentTimeMillis() + 180000));

        return verificationCode;
    }

    public boolean verifyCode(String phoneNumber, String code) {
        VerificationInfo info = verificationStore.get(phoneNumber);

        if (info == null) {
            return false;
        }

        // 만료 시간 체크
        if (System.currentTimeMillis() > info.getExpirationTime()) {
            verificationStore.remove(phoneNumber);
            return false;
        }

        // 인증번호 일치 여부 체크
        boolean isValid = info.getCode().equals(code);

        if (isValid) {
            verificationStore.remove(phoneNumber);
        }

        return isValid;
    }
//  랜덤으로 인증번호 생성 코드
    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}