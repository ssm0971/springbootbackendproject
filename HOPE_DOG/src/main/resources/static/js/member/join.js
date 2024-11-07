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
        memberName: 'name-error',           // <span id="name-error">
        memberNickname: 'nickname-error',   // <span id="nickname-error">
        memberGender: 'gender-error',       // <span id="gender-error">
        memberId: 'userid-error',           // <span id="userid-error">
        memberPw: 'password-error',         // <span id="password-error">
        passwordConfirm: 'passwordConfirm-error', // <span id="passwordConfirm-error">
        memberPhoneNumber: 'phone-error',   // <span id="phone-error">
        phoneVerifyCode: 'phoneVerifyCode-error', // <span id="phoneVerifyCode-error">
        memberEmail: 'email-error',         // <span id="email-error">
        memberZipcode: 'zipcode-error',     // <span id="zipcode-error">
        memberAddress: 'address-error',     // <span id="address-error">
        memberDetailAddress: 'detailAddress-error' // <span id="detailAddress-error">
    };

    // 필드 한글 이름 매핑
    const fieldNames = {
        memberName: '이름',
        memberNickname: '닉네임',
        memberGender: '성별',
        memberId: '아이디',
        memberPw: '비밀번호',
        passwordConfirm: '비밀번호 확인',
        memberPhoneNumber: '연락처',
        phoneVerifyCode: '인증번호',
        memberEmail: '이메일',
        memberZipcode: '우편번호',
        memberAddress: '주소',
        memberDetailAddress: '상세주소'
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
        let errorId;
        // input 요소의 name 속성이 있는 경우
        if (inputElement.name && errorFields[inputElement.name]) {
            errorId = errorFields[inputElement.name];
        }
        // 특수한 경우 (비밀번호 확인, 인증번호)
        else if (inputElement.id === 'passwordConfirm' || inputElement.id === 'phoneVerifyCode') {
            errorId = `${inputElement.id}-error`;
        }
        // 그 외의 경우
        else {
            errorId = `${inputElement.id}-error`;
        }

        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.textContent = message;
            errorElement.style.display = 'block';
            inputElement.classList.add('error');
            console.log('Showing error for:', errorId, message); // 디버깅용
        } else {
            console.log('Error element not found:', errorId); // 디버깅용
        }
    }

    // 에러 숨김 함수
    function hideError(inputElement) {
        let errorId;
        if (inputElement.name && errorFields[inputElement.name]) {
            errorId = errorFields[inputElement.name];
        }
        else if (inputElement.id === 'passwordConfirm' || inputElement.id === 'phoneVerifyCode') {
            errorId = `${inputElement.id}-error`;
        }
        else {
            errorId = `${inputElement.id}-error`;
        }

        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.style.display = 'none';
            errorElement.textContent = '';
            inputElement.classList.remove('error');
            console.log('Hiding error for:', errorId); // 디버깅용
        } else {
            console.log('Error element not found:', errorId); // 디버깅용
        }
    }


    // 유효성 검사 규칙 추가
    const validationRules = {
        memberId: {
            pattern: /^[a-zA-Z0-9]{4,20}$/,
            message: '아이디는 4~20자의 영문자 또는 숫자여야 합니다.'
        },
        memberPw: {
            pattern: /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/,
            message: '비밀번호는 8자 이상의 영문자, 숫자, 특수문자를 포함해야 합니다.'
        },
        memberName: {
            pattern: /^[가-힣]{2,10}$/,
            message: '이름은 2~10자의 한글만 입력 가능합니다.'
        },
        memberNickname: {
            pattern: /^[a-zA-Z0-9가-힣]{2,10}$/,
            message: '닉네임은 2~10자의 한글, 영문, 숫자만 가능합니다.'
        },
        memberEmail: {
            pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
            message: '올바른 이메일 형식이 아닙니다.'
        }
    };

    // validateInput 함수 수정
    function validateInput(inputElement) {
        const value = inputElement.value.trim();
        const rule = validationRules[inputElement.name];
        // 필드의 한글 이름 가져오기
        const fieldName = fieldNames[inputElement.name] || fieldNames[inputElement.id] || inputElement.name;

        if (inputElement.required && value === '') {
            showError(inputElement, `${fieldName}을(를) 입력해주세요.`);
            return false;
        }

        if (rule && rule.pattern && !rule.pattern.test(value)) {
            showError(inputElement, rule.message);
            return false;
        }

        hideError(inputElement);
        return true;
    }

    // 폼 제출 시 전체 유효성 검사
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        let isValid = true;

        // 필수 필드 검사
        const requiredFields = form.querySelectorAll('[required]');
        requiredFields.forEach(field => {
            if (!validateInput(field)) {
                isValid = false;
            }
        });

        // 비밀번호 확인 검사
        const pwInput = document.getElementById('memberPw');
        const pwConfirmInput = document.getElementById('passwordConfirm');
        if (pwInput.value !== pwConfirmInput.value) {
            showError(pwConfirmInput, '비밀번호가 일치하지 않습니다.');
            isValid = false;
        }

        // 성별 선택 검사
        if (!document.getElementById('memberGender').value) {
            showError(document.getElementById('memberGender'), '성별을 선택해주세요.');
            isValid = false;
        }

        // 휴대폰 인증 검사
        if (!isPhoneVerified) {
            showError(document.getElementById('memberPhoneNumber'), '휴대폰 인증이 필요합니다.');
            isValid = false;
        }

        // 필수 인증 검사
        if (!isNicknameAvailable) {
            showError(nicknameInput, '닉네임 중복 확인이 필요합니다.');
            isValid = false;
        }

        if (!isEmailAvailable) {
            showError(emailInput, '이메일 중복 확인이 필요합니다.');
            isValid = false;
        }

        if (!isValid) {
            return false;
        }
    });

    // 실시간 입력값 검사 이벤트 리스너 추가
    const inputs = form.querySelectorAll('input[required]');
    inputs.forEach(input => {
        input.addEventListener('input', function() {
            validateInput(this);
        });

        input.addEventListener('blur', function() {
            validateInput(this);
        });
    });

    // 비밀번호 확인 실시간 검사
    const pwConfirmInput = document.getElementById('passwordConfirm');
    pwConfirmInput.addEventListener('input', function() {
        if (this.value !== document.getElementById('memberPw').value) {
            showError(this, '비밀번호가 일치하지 않습니다.');
        } else {
            hideError(this);
        }
    });

    // 입력값 변경 시 중복 확인 상태 초기화
    nicknameInput.addEventListener('input', function() {
        isNicknameAvailable = false;
    });

    emailInput.addEventListener('input', function() {
        isEmailAvailable = false;
    });

    // 페이지 로드 시 초기화
    document.getElementById('phoneVerifyCode').setAttribute('disabled', 'true');
});
