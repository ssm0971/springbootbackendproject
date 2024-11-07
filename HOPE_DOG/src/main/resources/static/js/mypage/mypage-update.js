document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('signupForm');
    let isPhoneVerified = false;
    let timerInterval;
    const TIMER_DURATION = 180; // 3분

    // 각 입력 필드 검증 함수들
    function checkNicknameInput() {
        const inputField = document.getElementById('nickname');
        const nameError = document.getElementById('nicknameError');

        if (inputField.value.trim() === '') {
            nameError.style.display = 'block';
        } else {
            nameError.style.display = 'none';
        }
    }

    function checkEmailInput() {
        const inputField = document.getElementById('email');
        const emailError = document.getElementById('emailError');

        if (inputField.value.trim() === '') {
            emailError.style.display = 'block';
        } else {
            emailError.style.display = 'none';
        }
    }

    function checkPhoneInput() {
        const inputField = document.getElementById('phonenumber');
        const phoneError = document.getElementById('phoneError');

        if (inputField.value.trim() === '') {
            phoneError.style.display = 'block';
        } else {
            phoneError.style.display = 'none';
        }
    }

    function checkPnumberInput() {
        const inputField = document.getElementById('phonecheckInput');
        const phone2Error = document.getElementById('phonecheckError');

        if (inputField.value.trim() === '') {
            phone2Error.style.display = 'block';
        } else {
            phone2Error.style.display = 'none';
        }
    }

    function checkAddressInput() {
        const inputField = document.getElementById('addressInput');
        const addressError = document.getElementById('addressError');

        if (inputField.value.trim() === '') {
            addressError.style.display = 'block';
        } else {
            addressError.style.display = 'none';
        }
    }

    function checkAddInput() {
        const inputField = document.getElementById('addInput');
        const addError = document.getElementById('addError');

        if (inputField.value.trim() === '') {
            addError.style.display = 'block';
        } else {
            addError.style.display = 'none';
        }
    }

    function checkPasswordInput() {
        const inputField = document.getElementById('passwordInput');
        const passwordError = document.getElementById('passwordError');

        if (inputField.value.trim() === '') {
            passwordError.style.display = 'block';
        } else {
            passwordError.style.display = 'none';
        }
    }

    function checkPcheckInput() {
        const inputField = document.getElementById('passwordcheck');
        const passwordcheckError = document.getElementById('passwordcheckError');

        if (inputField.value.trim() === '') {
            passwordcheckError.style.display = 'block';
        } else {
            passwordcheckError.style.display = 'none';
        }
    }

    // 전화번호 입력 시 하이픈 자동 추가
    document.getElementById('phonenumber').addEventListener('input', function(e) {
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

    // Cool SMS 인증번호 발송
    window.sendVerification = function(e) {
        if(e) e.preventDefault();
        const phoneNumber = document.getElementById('phonenumber').value.trim();

        if (phoneNumber === '') {
            document.getElementById('phoneError').textContent = '연락처를 입력해주세요.';
            document.getElementById('phoneError').style.display = 'block';
            return;
        }

        const phonePattern = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (!phonePattern.test(phoneNumber)) {
            document.getElementById('phoneError').textContent = '올바른 전화번호 형식이 아닙니다.';
            document.getElementById('phoneError').style.display = 'block';
            return;
        }

        const cleanPhoneNumber = phoneNumber.replace(/-/g, '');

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
                    document.getElementById('phonecheckInput').removeAttribute('disabled');
                    startTimer();
                    isPhoneVerified = false;
                } else {
                    alert(data.message || '인증번호 발송에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('인증번호 발송 중 오류가 발생했습니다.');
            });
    };

    // 인증번호 확인
    window.confirmVerification = function(e) {
        if(e) e.preventDefault();
        const verificationCode = document.getElementById('phonecheckInput').value;
        const phoneNumber = document.getElementById('phonenumber').value.replace(/-/g, '');

        if (!verificationCode) {
            document.getElementById('phonecheckError').textContent = '인증번호를 입력해주세요.';
            document.getElementById('phonecheckError').style.display = 'block';
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
                    document.getElementById('phonecheckError').style.display = 'none';
                    stopTimer();

                    // 인증 완료 후 입력 필드와 버튼 비활성화
                    document.getElementById('phonecheckInput').setAttribute('disabled', 'true');
                    document.getElementById('phonenumber').setAttribute('disabled', 'true');

                    // 인증된 전화번호를 hidden input에 저장
                    const verifiedPhoneInput = document.createElement('input');
                    verifiedPhoneInput.type = 'hidden';
                    verifiedPhoneInput.name = 'verifiedPhone';
                    verifiedPhoneInput.value = phoneNumber;
                    form.appendChild(verifiedPhoneInput);
                } else {
                    document.getElementById('phonecheckError').textContent = '인증번호가 일치하지 않습니다.';
                    document.getElementById('phonecheckError').style.display = 'block';
                    isPhoneVerified = false;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('인증 확인 중 오류가 발생했습니다.');
            });
    };

    // 3분 타이머 구현
    function startTimer() {
        let timeLeft = TIMER_DURATION;
        const timerDisplay = document.createElement('span');
        timerDisplay.id = 'timer';
        document.getElementById('phonecheckInput').parentElement.appendChild(timerDisplay);

        timerInterval = setInterval(() => {
            const minutes = Math.floor(timeLeft / 60);
            const seconds = timeLeft % 60;
            timerDisplay.textContent = ` ${minutes}:${seconds.toString().padStart(2, '0')}`;

            if (timeLeft === 0) {
                stopTimer();
                alert('인증시간이 만료되었습니다. 다시 시도해주세요.');
                document.getElementById('phonecheckInput').value = '';
                document.getElementById('phonecheckInput').setAttribute('disabled', 'true');
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

    // 주소 검색
    window.searchAddress = function() {
        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById('address').value = data.zonecode;
                document.getElementById('addressInput').value = data.address;
                document.getElementById('addInput').focus();
            }
        }).open();
    };

    // 이메일 중복 확인
    window.checkEmail = function() {
        const email = document.getElementById('email').value.trim();
        const currentEmail = document.getElementById('currentEmail').value.trim();

        if (email === currentEmail) {
            return;
        }

        if (email === '') {
            document.getElementById('emailError').style.display = 'block';
            return;
        }

        fetch('/member/check-email?email=' + encodeURIComponent(email))
            .then(response => response.json())
            .then(data => {
                if (data.available) {
                    alert('사용 가능한 이메일입니다.');
                } else {
                    document.getElementById('emailError').textContent = '이미 사용 중인 이메일입니다.';
                    document.getElementById('emailError').style.display = 'block';
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('중복 확인 중 오류가 발생했습니다.');
            });
    };

    // 닉네임 중복 확인
    window.checkNickname = function() {
        const nickname = document.getElementById('nickname').value.trim();

        if (nickname === '') {
            document.getElementById('nicknameError').style.display = 'block';
            return;
        }

        fetch('/member/check-nickname?nickname=' + encodeURIComponent(nickname))
            .then(response => response.json())
            .then(data => {
                if (data.available) {
                    alert('사용 가능한 닉네임입니다.');
                } else {
                    document.getElementById('nicknameError').textContent = '이미 사용 중인 닉네임입니다.';
                    document.getElementById('nicknameError').style.display = 'block';
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('중복 확인 중 오류가 발생했습니다.');
            });
    };

    // 전체 입력 검증
    window.validateInputs = function(e) {
        if(e) e.preventDefault();
        let isValid = true;

        const requiredFields = [
            { id: 'nickname', errorId: 'nicknameError', message: '닉네임을 입력해주세요.' },
            { id: 'email', errorId: 'emailError', message: '이메일을 입력해주세요.' },
            { id: 'phonenumber', errorId: 'phoneError', message: '연락처를 입력해주세요.' },
            { id: 'address', errorId: 'addressError', message: '우편번호를 입력해주세요.' },
            { id: 'addressInput', errorId: 'addressError', message: '주소를 입력해주세요.' },
            { id: 'addInput', errorId: 'addError', message: '상세주소를 입력해주세요.' },
            { id: 'password', errorId: 'passwordError', message: '현재 비밀번호를 입력해주세요.' }
        ];

        // 필수 필드 검증
        requiredFields.forEach(field => {
            const input = document.getElementById(field.id);
            const error = document.getElementById(field.errorId);

            if (!input.value.trim()) {
                error.textContent = field.message;
                error.style.display = 'block';
                isValid = false;
            } else {
                error.style.display = 'none';
            }
        });

        // 휴대폰 인증 확인
        if (!isPhoneVerified) {
            document.getElementById('phonecheckError').textContent = '휴대폰 인증이 필요합니다.';
            document.getElementById('phonecheckError').style.display = 'block';
            isValid = false;
        }

        // 새 비밀번호 확인
        const newPassword = document.getElementById('passwordInput').value;
        const passwordCheck = document.getElementById('passwordcheck').value;

        if (newPassword || passwordCheck) {
            if (newPassword !== passwordCheck) {
                document.getElementById('passwordcheckError').textContent = '새 비밀번호가 일치하지 않습니다.';
                document.getElementById('passwordcheckError').style.display = 'block';
                isValid = false;
            }
        }

        if (!isValid) {
            alert('모든 필수 정보를 입력하고 휴대폰 인증을 완료해주세요.');
            return false;
        }

        // 모든 검증을 통과하면 폼 제출
        form.submit();
    };

    // 초기화 시 인증번호 입력 필드 비활성화
    const phonecheckInput = document.getElementById('phonecheckInput');
    if (phonecheckInput) {
        phonecheckInput.setAttribute('disabled', 'true');
    }

    // 각 입력 필드에 blur 이벤트 리스너 추가
    document.getElementById('nickname').addEventListener('blur', checkNicknameInput);
    document.getElementById('email').addEventListener('blur', checkEmailInput);
    document.getElementById('phonenumber').addEventListener('blur', checkPhoneInput);
    document.getElementById('phonecheckInput').addEventListener('blur', checkPnumberInput);
    document.getElementById('addressInput').addEventListener('blur', checkAddressInput);
    document.getElementById('addInput').addEventListener('blur', checkAddInput);
    document.getElementById('passwordInput').addEventListener('blur', checkPasswordInput);
    document.getElementById('passwordcheck').addEventListener('blur', checkPcheckInput);
});