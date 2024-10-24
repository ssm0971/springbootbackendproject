document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('signupForm');
    const phoneVerifyBtn = document.getElementById('phoneVerify');
    const phoneVerifyConfirmBtn = document.getElementById('phoneVerifyConfirm');
    const addressSearchBtn = document.getElementById('addressSearch');
    const cancelBtn = document.getElementById('cancelBtn');
    const genderBtns = document.querySelectorAll('.gender-btn');
    const nicknameInput = document.getElementById('memberNickname');
    const nicknameCheckBtn = document.getElementById('nicknameCheck');
    const emailInput = document.getElementById('memberEmail');
    const emailCheckBtn = document.getElementById('emailCheck');
    const memberIdInput = document.getElementById('memberId');
    const memberPwInput = document.getElementById('memberPw');
    const passwordConfirmInput = document.getElementById('passwordConfirm');
    const submitBtn = document.getElementById('submitBtn');

    let isPhoneVerified = false;
    let isNicknameAvailable = false;
    let isEmailAvailable = false;

    // 정규표현식 패턴
    const memberIdPattern = /^[a-zA-Z][a-zA-Z0-9]{5,19}$/;
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;

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
    document.getElementById('nicknameCheck').addEventListener('click', function() {
        const nickname = document.getElementById('memberNickname').value.trim();
        if (nickname === '') {
            showError(document.getElementById('memberNickname'), '닉네임을 입력해주세요.');
            return;
        }

        fetch('/member/check-nickname?nickname=' + encodeURIComponent(nickname))
            .then(response => response.json())
            .then(data => {
                if (data.available) {
                    alert('사용 가능한 닉네임입니다.');
                    isNicknameAvailable = true;
                    hideError(document.getElementById('memberNickname'));
                } else {
                    showError(document.getElementById('memberNickname'), '이미 사용 중인 닉네임입니다.');
                    isNicknameAvailable = false;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('중복 확인 중 오류가 발생했습니다.');
            });
    });

    // 이메일 중복 확인
    document.getElementById('emailCheck').addEventListener('click', function() {
        const email = document.getElementById('memberEmail').value.trim();
        if (email === '') {
            showError(document.getElementById('memberEmail'), '이메일을 입력해주세요.');
            return;
        }

        fetch('/member/check-email?email=' + encodeURIComponent(email))
            .then(response => response.json())
            .then(data => {
                if (data.available) {
                    alert('사용 가능한 이메일입니다.');
                    isEmailAvailable = true;
                    hideError(document.getElementById('memberEmail'));
                } else {
                    showError(document.getElementById('memberEmail'), '이미 사용 중인 이메일입니다.');
                    isEmailAvailable = false;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('중복 확인 중 오류가 발생했습니다.');
            });
    });

    // Cool SMS 인증 관련 코드
    phoneVerifyBtn.addEventListener('click', function() {
        const phoneNumber = document.getElementById('memberPhoneNumber').value.trim();
        if (phoneNumber === '') {
            showError(document.getElementById('memberPhoneNumber'), '휴대폰 번호를 입력해주세요.');
            return;
        }

        // 전화번호 형식 검사 (010-1234-5678 or 01012345678)
        const phonePattern = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (!phonePattern.test(phoneNumber)) {
            showError(document.getElementById('memberPhoneNumber'), '올바른 전화번호 형식이 아닙니다.');
            return;
        }

        // 하이픈 제거
        const cleanPhoneNumber = phoneNumber.replace(/-/g, '');

        // Cool SMS API 호출
        fetch('/api/sms/send', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ phoneNumber: cleanPhoneNumber })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('인증번호가 발송되었습니다.');
                    // 인증번호 입력 필드 활성화
                    document.getElementById('phoneVerifyCode').removeAttribute('disabled');
                    // 3분 타이머 시작
                    startTimer();
                } else {
                    alert(data.message || '인증번호 발송에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('인증번호 발송 중 오류가 발생했습니다.');
            });
    });

// 인증번호 확인
    phoneVerifyConfirmBtn.addEventListener('click', function() {
        const verificationCode = document.getElementById('phoneVerifyCode').value;
        const phoneNumber = document.getElementById('memberPhoneNumber').value.replace(/-/g, '');

        if (!verificationCode) {
            showError(document.getElementById('phoneVerifyCode'), '인증번호를 입력해주세요.');
            return;
        }

        fetch('/api/sms/verify', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                phoneNumber: phoneNumber,
                code: verificationCode
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('휴대폰 인증이 완료되었습니다.');
                    isPhoneVerified = true;
                    hideError(document.getElementById('phoneVerifyCode'));
                    // 타이머 중지
                    stopTimer();
                    // 인증 완료 후 입력 필드 비활성화
                    document.getElementById('phoneVerifyCode').setAttribute('disabled', 'true');
                    document.getElementById('phoneVerify').setAttribute('disabled', 'true');
                    document.getElementById('phoneVerifyConfirm').setAttribute('disabled', 'true');
                } else {
                    showError(document.getElementById('phoneVerifyCode'), '인증번호가 일치하지 않습니다.');
                    isPhoneVerified = false;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('인증 확인 중 오류가 발생했습니다.');
            });
    });

// 3분 타이머 구현
    let timerInterval;
    const TIMER_DURATION = 180; // 3분

    function startTimer() {
        let timeLeft = TIMER_DURATION;
        const timerDisplay = document.createElement('span');
        timerDisplay.id = 'timer';
        document.getElementById('phoneVerifyCode').parentElement.appendChild(timerDisplay);

        timerInterval = setInterval(() => {
            const minutes = Math.floor(timeLeft / 60);
            const seconds = timeLeft % 60;
            timerDisplay.textContent = ` ${minutes}:${seconds.toString().padStart(2, '0')}`;

            if (timeLeft === 0) {
                stopTimer();
                alert('인증시간이 만료되었습니다. 다시 시도해주세요.');
                document.getElementById('phoneVerifyCode').value = '';
                document.getElementById('phoneVerifyCode').setAttribute('disabled', 'true');
            }
            timeLeft--;
        }, 1000);
    }

    function stopTimer() {
        clearInterval(timerInterval);
        const timerDisplay = document.getElementById('timer');
        if (timerDisplay) {
            timerDisplay.remove();
        }
    }

// 전화번호 입력 시 하이픈 자동 추가
    document.getElementById('memberPhoneNumber').addEventListener('input', function(e) {
        let number = e.target.value.replace(/[^0-9]/g, '');
        let result = '';

        if (number.length > 3) {
            result = number.substring(0, 3) + '-';
            if (number.length > 7) {
                result += number.substring(3, 7) + '-' + number.substring(7, 11);
            } else {
                result += number.substring(3);
            }
        } else {
            result = number;
        }

        e.target.value = result;
    });

// 인증번호 재발송 시 기존 타이머 초기화
    phoneVerifyBtn.addEventListener('click', function() {
        stopTimer();
        document.getElementById('phoneVerifyCode').value = '';
        document.getElementById('phoneVerifyCode').removeAttribute('disabled');
    });

    // 주소 검색
    addressSearchBtn.addEventListener('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById('memberZipcode').value = data.zonecode;
                document.getElementById('memberAddress').value = data.address;
                document.getElementById('memberDetailAddress').focus();
                hideError(document.getElementById('memberZipcode'));
                hideError(document.getElementById('memberAddress'));
            }
        }).open();
    });

    // 취소 버튼
    cancelBtn.addEventListener('click', function() {
        if (confirm('회원가입을 취소하시겠습니까?')) {
            window.location.href = '/member/join-select';
        }
    });




// 폼 제출 이벤트 처리
    form.addEventListener('submit', function(e) {
        e.preventDefault();

        if (validateForm()) {
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());

            // 추가 필드 설정
            data.memberTwoFactorEnabled = 'N';
            data.memberStatus = '1';
            data.memberLoginStatus = 'HOPEDOG';

            fetch('/member/join', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('회원가입에 실패했습니다.');
                    }
                    alert('회원가입이 완료되었습니다.');
                    window.location.href = '/member/joinOk';
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert(error.message);
                });
        }
    });

// 개발 모드에서 디버깅을 위한 콘솔 로그
    form.addEventListener('submit', () => {
        console.log('Form submitted');
    });

    // 에러 메시지를 표시할 요소들의 ID를 현재 HTML 구조에 맞게 매핑
    const errorFields = {
        memberId: 'userid-error',
        memberPw: 'password-error',
        passwordConfirm: 'passwordConfirm-error',
        memberName: 'name-error',
        memberNickname: 'nickname-error',
        memberEmail: 'email-error',
        memberPhoneNumber: 'phone-error',
        memberZipcode: 'zipcode-error',
        memberAddress: 'address-error',
        memberDetailAddress: 'detailAddress-error',
        memberGender: 'gender-error'
    };

// 각 필드별 유효성 검사 함수
    function validateField(field, errorElementId) {
        const errorElement = document.getElementById(errorElementId);

        if (!errorElement) {
            console.warn(`Error element not found for ${errorElementId}`);
            return true;
        }

        const value = field.value.trim();
        let isValid = true;
        let errorMessage = '';

        switch (field.name) {
            case 'memberName':
                if (!value) {
                    errorMessage = '이름을 입력해주세요.';
                    isValid = false;
                } else if (value.length < 2) {
                    errorMessage = '이름은 2자 이상이어야 합니다.';
                    isValid = false;
                }
                break;

            case 'memberId':
                if (!value) {
                    errorMessage = '아이디를 입력해주세요.';
                    isValid = false;
                } else if (!/^[a-zA-Z0-9]{4,12}$/.test(value)) {
                    errorMessage = '아이디는 4~12자의 영문자와 숫자만 사용 가능합니다.';
                    isValid = false;
                }
                break;

            case 'memberPw':
                if (!value) {
                    errorMessage = '비밀번호를 입력해주세요.';
                    isValid = false;
                } else if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(value)) {
                    errorMessage = '비밀번호는 8자 이상의 영문자와 숫자 조합이어야 합니다.';
                    isValid = false;
                }
                break;

            case 'memberEmail':
                if (!value) {
                    errorMessage = '이메일을 입력해주세요.';
                    isValid = false;
                } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)) {
                    errorMessage = '올바른 이메일 형식이 아닙니다.';
                    isValid = false;
                }
                break;

            case 'memberPhoneNumber':
                if (!value) {
                    errorMessage = '연락처를 입력해주세요.';
                    isValid = false;
                } else if (!/^\d{10,11}$/.test(value.replace(/-/g, ''))) {
                    errorMessage = '올바른 연락처 형식이 아닙니다.';
                    isValid = false;
                }
                break;
        }

        // 비밀번호 확인 필드 특별 처리
        if (field.id === 'passwordConfirm') {
            const password = document.getElementById('memberPw').value;
            if (value !== password) {
                errorMessage = '비밀번호가 일치하지 않습니다.';
                isValid = false;
            }
        }

        errorElement.textContent = errorMessage;
        errorElement.style.display = isValid ? 'none' : 'block';

        return isValid;
    }

// 전체 폼 유효성 검사
    function validateForm() {
        let isValid = true;

        // 각 필드별로 유효성 검사 수행
        for (const [fieldName, errorId] of Object.entries(errorFields)) {
            const field = document.querySelector(`[name="${fieldName}"]`) ||
                document.getElementById(fieldName);
            if (field && !validateField(field, errorId)) {
                isValid = false;
            }
        }

        // 비밀번호 확인 필드 검증
        const passwordConfirmField = document.getElementById('passwordConfirm');
        if (passwordConfirmField && !validateField(passwordConfirmField, 'passwordConfirm-error')) {
            isValid = false;
        }

        // 성별 선택 확인
        const genderField = document.getElementById('memberGender');
        if (!genderField.value) {
            const genderError = document.getElementById('gender-error');
            genderError.textContent = '성별을 선택해주세요.';
            genderError.style.display = 'block';
            isValid = false;
        }

        return isValid;
    }

// 실시간 유효성 검사를 위한 이벤트 리스너 등록
    document.addEventListener('DOMContentLoaded', () => {
        // 일반 입력 필드에 대한 이벤트 리스너
        for (const [fieldName, errorId] of Object.entries(errorFields)) {
            const field = document.querySelector(`[name="${fieldName}"]`) ||
                document.getElementById(fieldName);
            if (field) {
                field.addEventListener('input', () => validateField(field, errorId));
                field.addEventListener('blur', () => validateField(field, errorId));
            }
        }

        // 비밀번호 확인 필드 이벤트 리스너
        const passwordConfirmField = document.getElementById('passwordConfirm');
        if (passwordConfirmField) {
            passwordConfirmField.addEventListener('input', () =>
                validateField(passwordConfirmField, 'passwordConfirm-error'));
            passwordConfirmField.addEventListener('blur', () =>
                validateField(passwordConfirmField, 'passwordConfirm-error'));
        }
    });

    // 에러 표시 함수
    function showError(inputElement, message) {
        const errorId = inputElement.name ? errorFields[inputElement.name] : `${inputElement.id}-error`;
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.textContent = message;
            errorElement.style.display = 'block';
            inputElement.classList.add('error');
        }
    }

    // 에러 숨김 함수
    function hideError(inputElement) {
        const errorId = inputElement.name ? errorFields[inputElement.name] : `${inputElement.id}-error`;
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.style.display = 'none';
            errorElement.textContent = '';
            inputElement.classList.remove('error');
        }
    }
});