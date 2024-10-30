package com.example.hope_dog.controller.member;

import com.example.hope_dog.dto.member.FindPwRequestDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


   // 회원가입 페이지 이동
    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

   // 회원가입
    @PostMapping("/join")
    @ResponseBody
//    이 어노테이션은 메서드의 반환 값을 JSON 형식으로 클라이언트에게 직접 전달함.
    public ResponseEntity<?> join(@RequestBody MemberDTO memberDTO, HttpSession session) {
//  ResponseEntity는는 Spring MVC에서 HTTP 요청에 대한 응답을 제어하는 데 사용하는 클래스이다.
//  이 클래스는 Response Body, Header, Status Code를 포함할 수 있어, 세밀한 응답 관리를 가능하게 한다.
//  ResponseEntity는 @RestController와 함께 사용되어 JSON 또는 XML과 같은 RESTful 서비스를 제공하는데 이상적이다.
//  ResponseEntity<?>는 Spring에서 HTTP 응답을 보다 유연하게 처리하기 위해 사용되는 타입입니다.
// 여기서 <?> 부분은 응답의 바디에 특정 데이터 타입을 지정하지 않았다는 의미입니다
        log.info("memberDTO = {}", memberDTO);
        memberService.join(memberDTO);
        // 세션에 닉네임 저장
        session.setAttribute("memberNickname", memberDTO.getMemberNickname());
        return ResponseEntity.ok().build();
//   ResponseEntity.ok().build()는 상태 코드 200 OK를 반환하겠다는 의미입니다
//   ok() 메서드는 HTTP 200 Status Code와 함께 Response를 생성한다. 이는 가장 일반적으로 사용되는 메서드 중 하나입니다.
    }

  // 회원가입 완료
    @GetMapping("/joinOk")
    public String joinOk(HttpSession session, Model model) {
        // 세션에서 닉네임 가져오기
        String memberNickname = (String) session.getAttribute("memberNickname");
        // Model에 닉네임 추가
        model.addAttribute("memberNickname", memberNickname);
        // 세션에서 닉네임 제거 (더 이상 필요하지 않으므로)
        session.removeAttribute("memberNickname");
        return "member/joinOk";
    }



    // 로그인 메서드 수정
    @PostMapping("/login")
    public String login(
            @RequestParam("memberId") String memberId,
            @RequestParam("memberPw") String memberPw,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            MemberSessionDTO loginInfo = memberService.login(memberId, memberPw);

            session.setAttribute("memberNo", loginInfo.getMemberNo());
            session.setAttribute("memberId", loginInfo.getMemberId());
            session.setAttribute("memberName", loginInfo.getMemberName());
            session.setAttribute("memberNickname", loginInfo.getMemberNickname());
            session.setAttribute("memberEmail", loginInfo.getMemberEmail());
            session.setAttribute("memberLoginStatus", loginInfo.getMemberLoginStatus());
            session.setAttribute("memberTwoFactorEnabled", loginInfo.getMemberTwoFactorEnabled());

            return "redirect:/main/main";
        } catch (IllegalArgumentException | IllegalStateException e) {
//      IllegalArgumentException 또는 IllegalStateException 예외가 발생할 경우 이 블록이 실행됩니다.
            log.error("로그인 실패: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
//            redirectAttributes를 사용하여 loginError라는 이름으로 에러 메시지를 추가합니다.
//          addFlashAttribute()는 리다이렉트 후에도 이 속성을 일회성으로 뷰에서 사용할 수 있게 해줍니다.
//          로그인 페이지에서 이 메시지를 통해 에러를 사용자에게 알릴 수 있습니다.
            return "redirect:/member/login";
        }
    }

    // 예외 처리기 수정
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
//    이 어노테이션은 Spring에서 컨트롤러 클래스의 특정 예외를 별도로 처리할 수 있게 해줍니다.
//이 메서드는 컨트롤러 내에서 발생한 IllegalArgumentException 또는 IllegalStateException을 전역적으로 처리할 수 있습니다.
    public ResponseEntity<String> handleException(Exception e) {
        //      예외를 HTTP 응답으로 반환하기 위해 ResponseEntity<String> 타입으로 메서드를 선언했습니다.
//      발생한 예외 메시지를 포함한 ResponseEntity 객체를 반환하여 클라이언트가 예외 정보를 받을 수 있습니다.
        return ResponseEntity.badRequest().body(e.getMessage());
//        badRequest() 메서드는 HTTP 상태 코드 400 (Bad Request)을 생성하고, 예외의 메시지를 응답 본문에 포함하여 반환합니다.
//          클라이언트는 이 응답을 통해 어떤 오류가 발생했는지 메시지를 통해 알 수 있게 됩니다.
    }

    // 예외처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {

        return ResponseEntity.badRequest().body(e.getMessage());
//        badRequest() 메서드는 HTTP 상태 코드 400 (Bad Request)을 생성하고, 예외의 메시지를 응답 본문에 포함하여 반환합니다.
//          클라이언트는 이 응답을 통해 어떤 오류가 발생했는지 메시지를 통해 알 수 있게 됩니다.
    }

 //로그인 선택
    @GetMapping("/login-select")
    public String loginSelect() {
        log.info("로그인 선택 페이지 요청");
        return "member/login-select";
    }


  // 회원가입 선택
    @GetMapping("/join-select")
    public String joinSelect() {
        log.info("회원가입 선택 페이지 요청");
        return "member/join-select";
    }


 //로그아웃 
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        log.info("로그아웃");
        session.invalidate();

        return new RedirectView("/main/main");
    }





    private static final String SERVICE_TERMS = " 여러분을 환영합니다. 서비스 및 제품(이하 '서비스')을 이용해 주셔서 감사합니다. 본 약관은 다양한 서비스의 이용과 관련하여 서비스를 제공하는 주식회사(이하 '회사')와 이를 이용하는 서비스\n" +
            "회원(이하 '회원') 또는 비회원과의 관계를 설명하며, 아울러 여러분의 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.";
    private static final String PRIVACY_TERMS = "제1조(회원 가입을 위한 정보)\n" +
            "                회사는 이용자의 회사 서비스에 대한 회원가입을 위하여 다음과 같은 정보를 수집합니다.\n" +
            "                1. 필수 수집 정보: 이메일 주소, 비밀번호, 이름, 닉네임, 생년월일 및 휴대폰 번호\n" +
            "\n" +
            "                제2조(본인 인증을 위한 정보)\n" +
            "                회사는 이용자의 본인인증을 위하여 다음과 같은 정보를 수집합니다.\n" +
            "                1. 필수 수집 정보 : 휴대폰 번호, 이메일 주소, 이름, 생년월일 및 성별\n" +
            "                제3조(서비스 이용 및 부정 이용 확인을 위한 정보)\n" +
            "                회사는 이용자의 서비스 이용에 따른 통계.분석 및 부정이용의 확인.분석을 위하여 다음과 같은\n" +
            "                정보를 수집합니다. (부정이용이란 회원탈퇴 후 재가입, 상품구매 후 구매취소 등을 반복적으로\n" +
            "                행하는 등 회사가 제공하는 할인쿠폰, 이벤트 혜택 등의 경제상 이익을 불.편법적으로 수취하는\n" +
            "                행위, 이용약관 등에서 금지하고 있는 행위, 명의도용 등의 불.편법행위 등을 말합니다.)\n" +
            "                1. 필수 수집 정보: 서비스 이용기록, 쿠키, 접속지 정보 및 기기정보\n" +
            "                2. 이용자가 회사가 발송한 이메일을 수신받아 개인정보를 입력하는 방식\n" +
            "                3. 이용자가 고객센터의 상담, 게시판에서의 활동 등 회사의 서비스를 이용하는 과정에서 이용자가\n" +
            "                입력하는 방식\n" +
            "                4. 신규 서비스 개발을 위한 경우\n" +
            "                5. 이벤트 및 행사 안내 등 마케팅을 위한 경우\n" +
            "                6. 인구통계학적 분석, 서비스 방문 및 이용기록의 분석을 위한 경우\n" +
            "                7. 개인정보 및 관심에 기반한 이용자간 관계의 형성을 위한 경우\n" +
            "\n" +
            "                제4조(광고성 정보의 전송 조치)\n" +
            "                1.회사는 전자적 전송매채를 이용하여 영리목적의 광고성 정보를 전송하는 경우 이용자의 명시적인\n" +
            "                사전동의를 받습니다.다만,다음 각호 어느 하나에 해당하는 경우에는 사전 동의를 받지 않습니다.\n" +
            "                가. 회사가 재화 등의 거래관계를 통하여 수신자로부터 직접 연락처를 수집한 경우, 거래가 종료된\n" +
            "                날로부터 6개월 이내에 회사가 처리하고 수신자와 거래한 것과 동종의 재화 등에 대한 영리목적의 광고성 정보를 전송하ㄴ려는 경우\n" +
            "                나. [방문판매 등에 관한 법률] 에 따른 전화권유판매자가 육성으로 수신자에게 개인정보의 수집출처를 고지하고 전화권유를 하는 경우\n" +
            "                2.회사는 전항에도 불구하고 수신자가 수신거부의사를 표시하거나 사전 동의를 철회한 경우에는 영리목적의 광고성 정보를\n" +
            "                전송하지 않으며 수신거부 및 수신동의 철회에 대한 처리결과를 알립니다.\n" +
            "                3.회사는 오후 9시부터 그다음 날 오전 8시까지의 시간에 전자적 전송매채를 이용하여 영리목적의 광고성 정보를\n" +
            "                전송하는 경우에는 제1항에도 불구하고 그 수신자로부터 별도의 사전 동의를 받습니다.\n" +
            "                4.회사는 전자적 전송매채를 이용하여 영리목적의 광고성 정보를 전송하는 경우 다음의 사항 등을 광고성 정보에 구체적으로 밝힙니다.\n" +
            "                가. 회사명 및 연락처\n" +
            "                나. 수신 거부 또는 수신 동의의 철회 의사표시에 관한 사항의 표시\n" +
            "                5.회사는 전자적 전송매채를 이용하여 영리목적의 광고성 정보를 전송하는 경우 다음 각 호의 어느 하나에 해당하는 조치를 하지 않습니다.\n" +
            "                가. 광고성 정보 수신자의 수신거부 또는 수신동의의 철회를 회피.방해하는 조치\n" +
            "                나. 숫자.부호 또는 문자를 조합하여 전화번호. 전자우편주소 등 수신자의 연락처를 자동으로 만들어내는 조치\n" +
            "                다. 영리목적의 광고성 정보를 전송할 목적으로 전화번호 또는 전자우편주소를 자동으로 등록하는 조치\n" +
            "                라. 광고성 정보 전송자의 신원이나 광고 전송 출처를 감추기 위한 각종 조치\n" +
            "                마. 영리목적의 광고성 정보를 전송할 목적으로 수신자를 기망하여 회신을 유도하는 각종 조치\n" +
            "\n" +
            "                제5조(아동의 개인정보보호)\n" +
            "                1. 회사는 만 14미만 아동의 개인정보를 위하여 만 14이상의 이용자에 한하여 회원가입을 허용합니다.\n" +
            "                2. 제1항에도 불구하고 회사는 이용자가 만 14미만의 아동일 경우에는, 그 아동의 법정대리인으로부터 그 아동의\n" +
            "                개인정보 수집, 이용, 제공 동의를 그 아동의 법정 대리인으로부터 받습니다.\n" +
            "                3. 제2항의 경우 회사는 그 법정대리인의 이름,생년월일,성별,중복강비확인정보,휴대폰번호등을 추가로 수집합니다.\n" +
            "\n" +
            "                제6조(이용자의 의무)\n" +
            "                1. 이용자는 자신의 개인정보를 최신의 상태로 유지해야 하며, 이용자의 부정확한 정보 입력으로 발생하는 문제의 책임은 이용자 자신에게 있습니다.\n" +
            "                2. 타인의 개인정보를 도용한 회원가입의 경우 이용자 자격을 상실하거나 관련 개인정보보호 법령에 의해 처벌받을 수 있습니다.\n" +
            "                3. 이용자는 전자우편주소,비밀번호 등에 대한 보안을 유지할 책임이 있으며 제3자에게 이를 양도하거나 대여할 수 없습니다.\n" +
            "\n" +
            "                제7조(회사의 개인정보 관리)\n" +
            "                회사는 이용자의 개인정보를 처리함에 있어 개인정보가 분실,도난,유출,변조,훼손 등이 되지 아니하도록 아니하도록\n" +
            "                안전성을 확보하기 위하여 필요한 기술적.관리적 보호대책을 강구하고 있습니다.\n" +
            "\n" +
            "                제8조(삭제된 정보의 처리)\n" +
            "                회사는 이용자 혹은 법정 대리인의 요청에 의해 해지 또는 삭제된 개인정보는 회사가 수집하는 \"개인정보의 보유 및 이용기간\"\n" +
            "                에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리하고 있습니다.\n" +
            "\n" +
            "                제9조(비밀번호의 암호화)\n" +
            "                이용자의 비밀번호는 일방향 암호화하여 저장 및 관리되고 있으며, 개인정보의 확인, 변경은 비밀번호를 알고 있는 본인에 의해서만 가능합니다.\n" +
            "\n" +
            "                제10조(해킹 등에 대비한 대책)\n" +
            "                1. 회사는 해킹, 컴퓨터 바이러스 등 정보통신망 침입에 의해 이용자의 개인정보가 유출 되거나 훼손되는 것을 막기 위해 최선을 다하고 있습니다.\n" +
            "                2. 회사는 최신 백신프로그램을 이용하여 이용자들의 개인정보나 자료가 유출 또는 손상되지 않도록 방지하고 있습니다.\n" +
            "                3. 회사는 만일의 사태에 대비하여 침입차단 시스템을 이용하여 보안에 최선을 다하고 있습니다.\n" +
            "                4. 회사는 민감한 개인정보(를 수집 및 보유하고 있는 경우) 를 암호화 통신 등을 통하여 네트워크상에서 개인정보를 안전하게 전송할 수 있도록 하고 있습니다.\n" +
            "\n" +
            "                제11조(개인정보 처리 최소화 및 교육)\n" +
            "                회사는 개인정보 관련 처리 담당자를 최소한으로 제한하며, 개인정보 처리자에 대한 교육등 관리적 조치를 통해\n" +
            "                법령 및 내부방침 등의 준수를 강조하고 있습니다.\n" +
            "\n" +
            "                제12조(개인정보 유출 등에 대한 조치)\n" +
            "                회사는 개인정보 분실.도난.유출(이하 \"유출 등\"이라 한다) 사실을 안 때에는 지체없이 다음 각 호의 모든 사항을 해당\n" +
            "                이용자에게 알리고 방송통신위원회 또는 한국인터넷흥원에 신고합니다.\n" +
            "                1. 유출 등이 된 개인정보 항목\n" +
            "                2. 유출 등이 발생한 시점\n" +
            "                3. 이용자가 취할 수 있는 조치\n" +
            "                4. 정보통신서비스 제공자 등의 대응 조치\n" +
            "                5. 이용자가 상담 등을 접수할 수 있는 부서 및 연락처\n" +
            "\n" +
            "                제13조(개인정보 유출 등에 대한 조치의 예외)\n" +
            "                회사는 전조에도 불구하고 이용자의 연락처를 알 수 없는 등 정당한 사유가 있는 경우에는 회사의 홈페이지에\n" +
            "                30일 이상 게시하는 방법으로 전조의 통지를 갈음하는 조치를 취할 수 있습니다.\n" +
            "\n" +
            "                제14조(회사의 개인정보 보호 책임자 지정)\n" +
            "                1.회사는 이용자의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 관려 부서 및 개인정보 보호 책인자를 지정하고 있습니다.\n" +
            "                가. 개인정보 보호 책임자\n" +
            "                1. 성명:이기철\n" +
            "                2. 직책:CTO\n" +
            "                3. 전화번호 : 010-4728-9077\n" +
            "                4. 이메일 : gichuk515@naver.com\n" +
            "\n" +
            "                제15조(권익침해에 대한 구제방법)\n" +
            "                1. 정보주체는 개인정보침해로 인한 구제를 받기 위하여 개인정보분쟁조정위원회,한국인터넷진흥원 개인정보침해신고센터 등에 분쟁해결이나상담 등을\n" +
            "                신청할 수 있습니다. 이 밖에 기타 개인정보침해의 신고, 상담에 대하여 아래의 기관에 문의하시기 바랍니다.\n" +
            "                가. 개인정보분쟁조정위원회 : (국번없이) 1833-6972(www.kopico.go.kr)\n" +
            "                나. 개인정보침해신고센터 : (국번없이) 118 (privacy.kisa.or.kr)\n" +
            "                다. 대검찰정 : (국번없이) 1301 (www.spo.go.kr)\n" +
            "                라. 검찰청 : (국번없이) 182 (ecrm.cyber.go.kr)\n" +
            "\n" +
            "                2. 회사는 정보주체의 개인정보자기결정권을 보장하고, 개인정보침해로 인한 상담 및 피해 구제를 위해 노력하고 있으며,\n" +
            "                신고나 상담이 필요한 경우 제1항의 담당부서로 연락해주시기 바랍니다.\n" +
            "\n" +
            "                3. 개인정보 보호법 제35조(개인정보의 열람), 제36조(개인정보 정정.삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에 대하여\n" +
            "                공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익의 침해를 받은 자는 행정심판법이 정하는\n" +
            "                바에따라 행정심판을 청구할 수 있습니다.\n" +
            "                가. 중앙행정심판위원회 : (국번없이) 110 (www.simpan.go.kr)";



   //약관동의 페이지 
    @GetMapping("/terms")
    public String terms(Model model) {
//        반환 타입이 String이므로, 특정 뷰 이름을 반환하여 사용자가 해당 페이지로 이동할 수 있게 합니다.
//        Model 객체를 사용하여 뷰로 데이터를 전달합니다.
        Map<String, String> terms = Map.of(
//      Map.of() 메서드를 사용해 약관 데이터를 담은 맵을 생성합니다. 여기서 serviceTerms와 privacyTerms는 각각 서비스 약관과 개인정보 보호 약관을 의미합니다.
//      SERVICE_TERMS와 PRIVACY_TERMS는 클래스 내의 상수로 정의된 약관의 내용을 참조한다고 가정됩니다.
                "serviceTerms", SERVICE_TERMS,
                "privacyTerms", PRIVACY_TERMS
        );
        model.addAttribute("terms", terms);
        return "member/terms";
    }

    //약관동의 후 회원가입 페이지 이동
    @GetMapping("/member/join")
    public String joinForm(@RequestParam(required = false, defaultValue = "false") Boolean emailAgreed) {
        log.info("이메일 수신 동의 여부: {}", emailAgreed);
        return "member/join";
    }


    //닉네임 중복체크
    @GetMapping("/check-nickname")
//    URL로 GET 요청이 들어올 때 이 메서드를 실행하게 합니다.
//    주로 닉네임 입력 후 중복 확인 버튼을 눌렀을 때 호출되는 API입니다.
    public ResponseEntity<Map<String, Boolean>> checkNickname(
//            ResponseEntity는 Spring MVC에서 HTTP 응답을 다루기 위해 사용하는 클래스로
//            이 클래스를 사용하면 응답을 더 유연하고 상세하게 제어할 수 있습니다.
//            ResponseEntity<Map<String, Boolean>>의 제네릭 타입은 Map<String, Boolean>로,
//            이 메서드가 반환할 응답 본문이 Map<String, Boolean> 형태의 JSON 데이터임을 나타냅니다.
            @RequestParam("nickname") String nickname
    ) {
        boolean isAvailable = memberService.checkNickname(nickname);
//        memberService.checkNickname(nickname) 메서드를 호출하여 닉네임이 이미 사용 중인지 확인합니다
        Map<String, Boolean> response = Map.of("available", isAvailable);
//        Map.of("available", isAvailable); 코드를 통해 결과 값을 Map<String, Boolean>으로 생성합니다.
        return ResponseEntity.ok(response);
//        ResponseEntity.ok(response)를 통해 상태 코드 200 (OK)과 함께 응답 본문을 설정하여 클라이언트에게 반환합니다.
    }

    //이메일 중복 체크
    @GetMapping("/check-email")
    @ResponseBody
// @ResponseBody: 반환값을 HTTP 응답 본문에 직접 작성
// Spring의 비동기 처리를 위해 사용되며, 클라이언트-서버 간 JSON 통신을 가능하게 함
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam(name = "email") String email) {
        // ResponseEntity<Map<String, Boolean>>:
        // - ResponseEntity: HTTP 응답을 세밀하게 제어할 수 있는 클래스
        // - Map<String, Boolean>: JSON 형태의 응답 데이터 타입 지정
        //   예: {"available": true} 또는 {"available": false}

        // memberService를 통해 이메일 중복 여부 확인
        // true: 사용 가능한 이메일
        // false: 이미 사용 중인 이메일
        boolean available = memberService.checkEmail(email);

        // ResponseEntity.ok(): HTTP 상태 코드 200(OK)으로 응답
        // Map.of(): 불변 Map을 생성하는 팩토리 메서드
        // "available" 키와 중복 체크 결과값을 JSON 형태로 반환
        return ResponseEntity.ok(Map.of("available", available));
    }



    //아이디 찾기 페이지 이동
    @GetMapping("/find-id")
    public String findIdForm() {
        return "member/find-id";
    }


   // 아이디 찾기
   @PostMapping("/find-id")
   @ResponseBody
// @ResponseBody: HTTP 응답 본문에 직접 데이터를 작성하기 위한 어노테이션
// Spring이 반환값을 자동으로 JSON으로 변환하여 클라이언트에게 전송
   public ResponseEntity<Map<String, String>> findId(@RequestParam("memberName") String memberName,
                                                     @RequestParam("memberPhoneNumber") String memberPhoneNumber) {
       // @RequestParam: HTTP 요청 파라미터를 메서드 파라미터로 바인딩
       // ResponseEntity: HTTP 응답을 상세하게 제어할 수 있는 클래스
       try {
           // memberService를 통해 회원 아이디 조회
           String memberId = memberService.findMemberId(memberName, memberPhoneNumber);
           // 성공 시 200 OK 상태코드와 함께 찾은 아이디를 JSON 형태로 반환
           // Map.of()를 사용하여 간단한 key-value 쌍의 Map 생성
           return ResponseEntity.ok(Map.of("memberId", memberId));
       } catch (IllegalArgumentException e) {
           // 실패 시 400 Bad Request 상태코드와 함께 에러 메시지 반환
           return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
       }
   }

   // 아이디 찾기 완료
   // 일반적인 웹 페이지 반환을 위한 컨트롤러 메서드
   public String findIdResult(@RequestParam(name = "memberName") String memberName,
                              @RequestParam(name = "memberId") String memberId,
                              Model model) {
       // Model: 뷰에 전달할 데이터를 저장하는 객체
       // 뷰에서 사용할 데이터를 model에 추가
       model.addAttribute("memberName", memberName);
       model.addAttribute("memberId", memberId);
       // member/find-idOk 뷰 템플릿을 반환
       // ViewResolver가 실제 뷰 페이지를 찾아 렌더링
       return "member/find-idOk";
   }

    // 비밀번호 찾기 페이지 이동
    @GetMapping("/find-pw")
    public String findPwForm() {
        return "member/find-pw";
    }

    // 비밀번호 찾기(재설정) API 엔드포인트
    @PostMapping("/find-pw")
    @ResponseBody
// @ResponseBody: HTTP 응답 본문에 직접 데이터를 작성하기 위한 어노테이션
// ResponseEntity<?>: 다양한 타입의 응답을 처리할 수 있도록 와일드카드 사용
    public ResponseEntity<?> findPw(@RequestBody FindPwRequestDTO requestDTO) {
        // @RequestBody: HTTP 요청 본문의 JSON 데이터를 FindPwRequestDTO 객체로 변환
        // FindPwRequestDTO: 클라이언트로부터 받은 이름, 아이디, 이메일 정보를 담는 DTO 클래스
        log.info("Received request DTO: {}", requestDTO);
        try {
            // memberService를 통해 비밀번호 재설정 로직 실행
            // DTO에서 필요한 정보를 추출하여 서비스 메서드 호출
            memberService.resetPassword(
                    requestDTO.getMemberName(),
                    requestDTO.getMemberId(),
                    requestDTO.getMemberEmail()
            );

            // 비밀번호 재설정 성공 시 처리
            // HashMap을 생성하여 응답 데이터 구성
            Map<String, String> response = new HashMap<>();
            // 사용자 이름을 응답 데이터에 포함
            response.put("memberName", requestDTO.getMemberName());
            // 200 OK 상태코드와 함께 응답 데이터 반환
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // 유효하지 않은 사용자 정보 등으로 인한 예외 발생 시
            // 400 Bad Request 상태코드와 함께 에러 메시지 반환
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 비밀번호 찾기 완료 페이지 표시
    @GetMapping("/find-pwOk")
// 비밀번호 재설정 완료 후 보여줄 결과 페이지를 위한 컨트롤러 메서드
    public String findPwOk(@RequestParam("memberName") String memberName, Model model) {
        // @RequestParam: HTTP 요청 파라미터 'memberName'을 메서드 파라미터로 바인딩
        // Model: 뷰에 전달할 데이터를 저장하는 객체

        // 뷰에서 사용할 사용자 이름을 model에 추가
        model.addAttribute("memberName", memberName);

        // member/find-pwOk 뷰 템플릿을 반환
        // ViewResolver가 실제 뷰 페이지를 찾아 렌더링
        return "member/find-pwOk";
    }
}