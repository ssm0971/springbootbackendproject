package com.example.hope_dog.controller.member;

import com.example.hope_dog.service.member.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * SMS 인증 관련 요청을 처리하는 REST 컨트롤러
 * 문자 메시지 발송 및 인증번호 검증 기능을 제공
 *
 * @RestController를 사용하여 모든 메서드의 반환값을 자동으로 JSON으로 변환
 * ResponseEntity를 사용하여 HTTP 응답의 상태 코드, 헤더, 본문을 세밀하게 제어
 */
@Slf4j  // Lombok을 사용한 로깅 설정, 'log' 변수를 자동으로 생성
@RestController  // @Controller + @ResponseBody의 조합, REST API용 컨트롤러임을 명시
@RequiredArgsConstructor  // final 필드에 대한 생성자를 자동으로 생성하여 의존성 주입을 깔끔하게 처리
@RequestMapping("/api/sms")  // 공통 URL 경로 설정, 모든 엔드포인트는 /api/sms로 시작
public class SmsController {

    // 생성자 주입 방식으로 SmsService 의존성 주입
    // final을 사용하여 불변성 보장 및 필수 의존성임을 명시
    private final SmsService smsService;

    /**
     * 인증번호 SMS 발송을 처리하는 엔드포인트
     *
     * @param request Map을 사용하여 유연한 요청 처리
     *                - Map 사용 이유:
     *                  1. 단순한 요청 데이터 구조에 적합
     *                  2. 클라이언트의 요청 형식 유연성 제공
     *                  3. 별도의 DTO 클래스 생성 없이 간단히 처리 가능
     *
     * @return ResponseEntity 사용 이유:
     *         1. HTTP 상태 코드를 명시적으로 제어 가능
     *         2. 응답 헤더와 본문을 세밀하게 제어 가능
     *         3. RESTful API의 표준적인 응답 형식 제공
     *         4. 클라이언트에게 더 자세한 응답 정보 제공 가능
     *
     * 요청 형식:
     * POST /api/sms/send
     * Content-Type: application/json
     * {
     *     "phoneNumber": "01012345678"
     * }
     *
     * 응답 형식:
     * HTTP/1.1 200 OK
     * Content-Type: application/json
     * {
     *     "success": true/false,
     *     "message": "처리 결과 메시지"
     * }
     */
    @PostMapping("/send")  // POST 방식의 /api/sms/send 엔드포인트 정의
    public ResponseEntity<Map<String, Object>> sendSms(@RequestBody Map<String, String> request) {
        try {
            // Map에서 전화번호 추출
            String phoneNumber = request.get("phoneNumber");

            // SMS 서비스를 통한 인증번호 발송
            // verificationCode는 추후 검증을 위해 어딘가에 저장됨
            String verificationCode = smsService.sendVerificationMessage(phoneNumber);

            // 성공 응답 생성
            // Map.of()를 사용하여 불변 Map 생성 - 간단하고 안전한 응답 데이터 구조
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "인증번호가 발송되었습니다."
            ));
        } catch (Exception e) {
            // 오류 로깅
            log.error("SMS 발송 실패: ", e);

            // 실패 응답 생성
            // HTTP 상태 코드는 200을 유지하면서 응답 본문에 실패 정보 포함
            return ResponseEntity.ok(Map.of(
                    "success", false,
                    "message", "인증번호 발송에 실패했습니다."
            ));
        }
    }

    /**
     * 인증번호 검증을 처리하는 엔드포인트
     *
     * @param request Map 형태의 요청 본문
     *                - phoneNumber: 인증을 요청한 전화번호
     *                - code: 사용자가 입력한 인증번호
     *
     * Map 사용 이유:
     * 1. 간단한 키-값 쌍의 데이터 구조
     * 2. 유연한 요청 처리 가능
     * 3. JSON과 자연스러운 매핑
     *
     * ResponseEntity 사용 이유:
     * 1. 표준화된 응답 형식 제공
     * 2. 상태 코드와 응답 본문을 포함한 완전한 HTTP 응답 제어
     *
     * 요청 형식:
     * POST /api/sms/verify
     * Content-Type: application/json
     * {
     *     "phoneNumber": "01012345678",
     *     "code": "123456"
     * }
     *
     * 응답 형식:
     * HTTP/1.1 200 OK
     * Content-Type: application/json
     * {
     *     "success": true/false,
     *     "message": "처리 결과 메시지"
     * }
     */
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifySms(@RequestBody Map<String, String> request) {
        // 요청에서 필요한 데이터 추출
        String phoneNumber = request.get("phoneNumber");
        String code = request.get("code");

        // 서비스 계층에서 인증번호 검증
        boolean isValid = smsService.verifyCode(phoneNumber, code);

        // 검증 결과에 따른 응답 생성
        // Map.of()로 불변 Map 생성, 응답의 일관성 유지
        return ResponseEntity.ok(Map.of(
                "success", isValid,
                "message", isValid ? "인증이 완료되었습니다." : "인증번호가 일치하지 않습니다."
        ));
    }
}