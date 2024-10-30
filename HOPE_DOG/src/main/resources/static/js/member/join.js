document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('signupForm');

    // form submit 이벤트 리스너 추가
    form.addEventListener('submit', function(e) {
        e.preventDefault();

        // 폼 데이터를 JSON으로 변환
        const formData = new FormData(this);
        const jsonData = {};
        formData.forEach((value, key) => jsonData[key] = value);

        // fetch 요청
        fetch('/member/join', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonData)
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/member/joinOk';
                } else {
                    throw new Error('회원가입에 실패했습니다.');
                }
            })
            .catch(error => {
                alert(error.message);
            });
    });

    const phoneVerifyBtn = document.getElementById('phoneVerify');
    const phoneVerifyConfirmBtn = document.getElementById('phoneVerifyConfirm');
    const addressSearchBtn = document.getElementById('addressSearch');
    const cancelBtn = document.getElementById('cancelBtn');
    const genderBtns = document.querySelectorAll('.gender-btn');
    const nicknameInput = document.getElementById('memberNickname');
    const nicknameCheckBtn = document.getElementById('nicknameCheck');
    const emailInput = document.getElementById('memberEmail');
    const emailCheckBtn = document.getElementById('emailCheck');

    let isPhoneVerified = false;
    let isNicknameAvailable = false;
    let isEmailAvailable = false;

    const errorFields = {
        memberId: 'memberId-error',
        memberPw: 'memberPw-error',
        memberPwConfirm: 'memberPwConfirm-error',
        memberName: 'memberName-error',
        memberNickname: 'memberNickname-error',
        memberEmail: 'memberEmail-error',
        memberPhoneNumber: 'memberPhoneNumber-error',
        memberGender: 'memberGender-error',
        memberZipcode: 'memberZipcode-error',
        memberAddress: 'memberAddress-error',
        memberDetailAddress: 'memberDetailAddress-error'
    };

    // 성별 선택
    genderBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            genderBtns.forEach(b => b.classList.remove('active'));
            this.classList.add('active');
            document.getElementById('memberGender').value = this.dataset.gender;
            hideError(document.getElementById('memberGender'));
        });
    });

    // 닉네임 중복 확인
    nicknameCheckBtn.addEventListener('click', function() {
        const nickname = nicknameInput.value.trim();
        if (nickname === '') {
            alert('닉네임을 입력해주세요.');  // alert로 변경
            showError(nicknameInput, '닉네임을 입력해주세요.');
            return;
        }
        // 서버로 /member/check-nickname API 요청을 보냄 이때, 사용자가 입력한 닉네임을 URL에 포함하여 서버에 전송
        // fetch 함수를 사용해서 서버응답을 기다림
        // fetch는 JavaScript에서 서버로 네트워크 요청을 보내고 응답을 받을 수 있도록 해주는 매서드

        fetch('/member/check-nickname?nickname=' + encodeURIComponent(nickname))
            .then(response => response.json())
            .then(data => {
                // 서버 응답이 JSON 형식으로 반환되면, 이를 data 변수에 저장.
                if (data.available) {
                    // data.available 값이 true일 경우:
                    // "사용 가능한 닉네임입니다."라는 메시지를 사용자에게 알리고, isNicknameAvailable 변수를 true로 설정하여 사용 가능한 상태로 표시함.
                    // hideError 함수를 호출해 이전에 표시된 오류 메시지를 제거
                    alert('사용 가능한 닉네임입니다.');
                    isNicknameAvailable = true;
                    hideError(nicknameInput);
                //     data.available 값이 false일 경우:
                    // "이미 사용 중인 닉네임입니다."라는 오류 메시지를 표시하고,
                    // isNicknameAvailable 변수를 false로 설정하여 중복된 상태로 표시합니다
                } else {
                    showError(nicknameInput, '이미 사용 중인 닉네임입니다.');
                    isNicknameAvailable = false;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('중복 확인 중 오류가 발생했습니다.');
            });
    });

    // 이메일 중복 확인
    emailCheckBtn.addEventListener('click', function() {
        const email = emailInput.value.trim();

        // 이메일 입력값 확인: 값이 비어 있으면 에러 메시지를 표시하고 함수를 종료합니다.
        if (email === '') {
            showError(emailInput, '이메일을 입력해주세요.');
            return;
        }

        // 서버로 /member/check-email API 요청을 보냄 이때, 사용자가 입력한 이메일을 URL에 포함하여 서버에 전송
        // fetch 함수를 사용해서 서버 응답을 기다림
        // fetch는 JavaScript에서 서버로 네트워크 요청을 보내고 응답을 받을 수 있도록 해주는 매서드

        fetch('/member/check-email?email=' + encodeURIComponent(email))
            .then(response => response.json())
            .then(data => {
                // 서버 응답이 JSON 형식으로 반환되면, 이를 data 변수에 저장
                if (data.available) {
                    // data.available 값이 true일 경우:
                    // "사용 가능한 이메일입니다."라는 메시지를 사용자에게 알리고,
                    // isEmailAvailable 변수를 true로 설정하여 사용 가능한 상태로 표시
                    // hideError 함수를 호출해 이전에 표시된 오류 메시지를 제거
                    alert('사용 가능한 이메일입니다.');
                    isEmailAvailable = true;
                    hideError(emailInput);

                } else {
                    // data.available 값이 false일 경우:
                    // "이미 사용 중인 이메일입니다."라는 오류 메시지를 표시하고,
                    // isEmailAvailable 변수를 false로 설정하여 중복된 상태로 표시
                    showError(emailInput, '이미 사용 중인 이메일입니다.');
                    isEmailAvailable = false;
                }
            })
            .catch(error => {
                // 오류 처리: 서버 요청 중 오류가 발생하면, 콘솔에 오류를 기록하고
                // 사용자에게 "중복 확인 중 오류가 발생했습니다."라는 메시지를 표시
                console.error('Error:', error);
                alert('중복 확인 중 오류가 발생했습니다.');
            });
    });

    // Cool SMS 인증 관련 코드
    phoneVerifyBtn.addEventListener('click', function() {
        const phoneNumber = document.getElementById('memberPhoneNumber').value.trim();

        // 휴대폰 번호 입력 확인: 값이 비어 있으면 에러 메시지를 표시하고 함수를 종료합니다.
        if (phoneNumber === '') {
            showError(document.getElementById('memberPhoneNumber'), '휴대폰 번호를 입력해주세요.');
            return;
        }

        // 휴대폰 번호 형식 검사: 올바른 형식인지 확인하기 위해 정규 표현식을 사용합니다.
        const phonePattern = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (!phonePattern.test(phoneNumber)) {
            showError(document.getElementById('memberPhoneNumber'), '올바른 전화번호 형식이 아닙니다.');
            return;
        }

        // 서버로 전송하기 위해 휴대폰 번호에서 '-'를 제거한 형태로 저장합니다.
        const cleanPhoneNumber = phoneNumber.replace(/-/g, '');

        // 서버로 /api/sms/send API 요청을 보냅니다. 이때, 클린된 휴대폰 번호를 JSON으로 전송합니다.
        // fetch는 서버에 네트워크 요청을 보내고 응답을 받을 수 있게 하는 JavaScript 메서드입니다.
        fetch('/api/sms/send', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ phoneNumber: cleanPhoneNumber })
        })
            .then(response => response.json())
            .then(data => {
                // 서버 응답이 JSON 형식으로 반환되면 이를 data 변수에 저장
                if (data.success) {
                    // data.success 값이 true일 경우:
                    // "인증번호가 발송되었습니다."라는 메시지를 사용자에게 알림
                    // 인증번호 입력 필드의 비활성화 속성을 제거하고, 타이머를 시작합니다.
                    alert('인증번호가 발송되었습니다.');
                    document.getElementById('phoneVerifyCode').removeAttribute('disabled');
                    startTimer();
                } else {
                    // data.success 값이 false일 경우:
                    // 서버에서 반환한 오류 메시지를 표시하거나 기본 오류 메시지를 표시
                    alert(data.message || '인증번호 발송에 실패했습니다.');
                }
            })
            .catch(error => {
                // 오류 처리: 서버 요청 중 오류가 발생하면, 콘솔에 오류를 기록하고
                // 사용자에게 "인증번호 발송 중 오류가 발생했습니다."라는 메시지를 표시
                console.error('Error:', error);
                alert('인증번호 발송 중 오류가 발생했습니다.');
            });
    });

    // 인증번호 확인
    phoneVerifyConfirmBtn.addEventListener('click', function() {
        // 입력한 인증번호와 전화번호를 가져옵니다. '-'가 포함된 전화번호에서 '-'를 제거한 뒤 전송할 전화번호를 준비합니다.
        const verificationCode = document.getElementById('phoneVerifyCode').value;
        const phoneNumber = document.getElementById('memberPhoneNumber').value.replace(/-/g, '');

        // 인증번호 입력 확인: 값이 비어 있으면 에러 메시지를 표시하고 함수를 종료합니다.
        if (!verificationCode) {
            showError(document.getElementById('phoneVerifyCode'), '인증번호를 입력해주세요.');
            return;
        }

        // 서버로 /api/sms/verify API 요청을 보냅니다. 이때, 휴대폰 번호와 인증번호를 JSON 형식으로 전송합니다.
        fetch('/api/sms/verify', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ phoneNumber: phoneNumber, code: verificationCode })
        })
            .then(response => response.json())
            .then(data => {
                // 서버 응답이 JSON 형식으로 반환되면 이를 data 변수에 저장
                if (data.success) {
                    // data.success 값이 true일 경우:
                    // "휴대폰 인증이 완료되었습니다."라는 메시지를 사용자에게 알리고,
                    // isPhoneVerified 변수를 true로 설정하여 인증 완료 상태로 표시
                    alert('휴대폰 인증이 완료되었습니다.');
                    isPhoneVerified = true;

                    // 오류 메시지 숨기기 및 타이머 종료
                    hideError(document.getElementById('phoneVerifyCode'));
                    stopTimer();

                    // 인증 완료 후, 인증번호 입력 필드와 인증 버튼을 비활성화하여 수정 불가능하도록 설정
                    document.getElementById('phoneVerifyCode').setAttribute('disabled', 'true');
                    document.getElementById('phoneVerify').setAttribute('disabled', 'true');
                    document.getElementById('phoneVerifyConfirm').setAttribute('disabled', 'true');
                } else {
                    // data.success 값이 false일 경우:
                    // "인증번호가 일치하지 않습니다."라는 오류 메시지를 표시하고,
                    // isPhoneVerified 변수를 false로 설정하여 인증 실패 상태로 표시
                    showError(document.getElementById('phoneVerifyCode'), '인증번호가 일치하지 않습니다.');
                    isPhoneVerified = false;
                }
            })
            .catch(error => {
                // 오류 처리: 서버 요청 중 오류가 발생하면, 콘솔에 오류를 기록하고
                // 사용자에게 "인증 확인 중 오류가 발생했습니다."라는 메시지를 표시
                console.error('Error:', error);
                alert('인증 확인 중 오류가 발생했습니다.');
            });
    });

    // 3분 타이머 구현
    let timerInterval; // 타이머 간격을 저장할 변수
    const TIMER_DURATION = 180; // 타이머의 기본 시간(3분)을 초 단위로 설정

    function startTimer() {
        // 남은 시간을 타이머 기본 시간으로 초기화
        let timeLeft = TIMER_DURATION;

        // 타이머 표시 요소 생성 후, 인증번호 입력 필드의 부모 요소에 추가
        const timerDisplay = document.createElement('span');
        timerDisplay.id = 'timer';
        document.getElementById('phoneVerifyCode').parentElement.appendChild(timerDisplay);

        // 1초마다 남은 시간을 업데이트하는 타이머 시작
        timerInterval = setInterval(() => {
            // 남은 시간(분과 초)을 계산하고, 초 부분을 두 자리로 맞추어 표시
            const minutes = Math.floor(timeLeft / 60);
            const seconds = timeLeft % 60;
            timerDisplay.textContent = ` ${minutes}:${seconds.toString().padStart(2, '0')}`;

            // 시간이 만료되면 타이머를 중지하고 사용자에게 알림
            if (timeLeft === 0) {
                stopTimer();
                alert('인증시간이 만료되었습니다. 다시 시도해주세요.');

                // 만료 시, 인증번호 입력 필드를 비우고 비활성화하여 입력을 막음
                document.getElementById('phoneVerifyCode').value = '';
                document.getElementById('phoneVerifyCode').setAttribute('disabled', 'true');
            }

            // 남은 시간 감소
            timeLeft--;
        }, 1000);
    }

    function stopTimer() {
        // 타이머 중지 및 화면에서 타이머 표시 요소 제거
        clearInterval(timerInterval);
        const timerDisplay = document.getElementById('timer');
        if (timerDisplay) timerDisplay.remove();
    }

    // 전화번호 입력 시 하이픈 자동 추가
    document.getElementById('memberPhoneNumber').addEventListener('input', function(e) {
        // 입력된 값에서 숫자만 추출
        let number = e.target.value.replace(/[^0-9]/g, '');
        let result = '';

        // 하이픈 추가 로직
        if (number.length > 3) {
            result = number.substring(0, 3) + '-';
            if (number.length > 7) {
                result += number.substring(3, 7) + '-' + number.substring(7, 11);
            } else {
                result += number.substring(3);
            }
        } else {
            result = number; // 3자리 이하일 경우 하이픈 없이 표시
        }

        // 결과를 입력 필드에 반영
        e.target.value = result;
    });

    // 주소 검색
    addressSearchBtn.addEventListener('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 주소 검색 결과를 입력 필드에 자동으로 채움
                document.getElementById('memberZipcode').value = data.zonecode; // 우편번호
                document.getElementById('memberAddress').value = data.address; // 주소
                document.getElementById('memberDetailAddress').focus(); // 상세 주소 입력 필드에 포커스
                hideError(document.getElementById('memberZipcode')); // 에러 메시지 숨김
                hideError(document.getElementById('memberAddress')); // 에러 메시지 숨김
            }
        }).open(); // 주소 검색 팝업 열기
    });

    // 취소 버튼
    cancelBtn.addEventListener('click', function() {
        // 회원가입 취소 확인 후, 특정 페이지로 이동
        if (confirm('회원가입을 취소하시겠습니까?')) {
            window.location.href = '/member/join-select'; // 취소 후 이동할 페이지
        }
    });

    // 에러 표시 함수
    function showError(inputElement, message) {
        // 입력 요소의 이름에 따라 에러 ID 결정
        const errorId = inputElement.name ? errorFields[inputElement.name] : `${inputElement.id}-error`;
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.textContent = message; // 에러 메시지 설정
            errorElement.style.display = 'block'; // 에러 메시지 표시
            inputElement.classList.add('error'); // 입력 요소에 에러 클래스 추가
        }
    }

    // 에러 숨김 함수
    function hideError(inputElement) {
        // 입력 요소의 이름에 따라 에러 ID 결정
        const errorId = inputElement.name ? errorFields[inputElement.name] : `${inputElement.id}-error`;
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.style.display = 'none'; // 에러 메시지 숨김
            errorElement.textContent = ''; // 에러 메시지 내용 삭제
            inputElement.classList.remove('error'); // 입력 요소에서 에러 클래스 제거
        }
    }

});