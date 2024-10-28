document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('center-signupForm');
    const phoneVerifyBtn = document.getElementById('center-phoneVerify');
    const phoneVerifyConfirmBtn = document.getElementById('center-phoneVerifyConfirm');
    const addressSearchBtn = document.getElementById('center-addressSearch');
    const cancelBtn = document.getElementById('center-cancelBtn');
    const businessFileUploadBtn = document.getElementById('center-businessFileUpload');
    const businessFileInput = document.getElementById('center-businessFile');
    const useridInput = document.getElementById('center-userid');
    const passwordInput = document.getElementById('center-password');
    const passwordConfirmInput = document.getElementById('center-passwordConfirm');
    const emailCheckBtn = document.getElementById('center-emailCheck');

    let phoneVerificationCode = '';
    let isEmailAvailable = false;  // 이메일 중복 확인 상태
    let isPhoneVerified = false;   // 휴대폰 인증 상태 추가

    // 정규표현식 패턴
    const useridPattern = /^[a-zA-Z][a-zA-Z0-9]{5,19}$/;
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;

    // 파일 업로드 버튼 이벤트
    businessFileUploadBtn.addEventListener('click', function() {
        businessFileInput.click();
    });

    // 파일 선택 시 이벤트
    businessFileInput.addEventListener('change', function() {
        if (this.files.length > 0) {
            const fileName = this.files[0].name;
            const fileSize = this.files[0].size;
            const maxSize = 5 * 1024 * 1024; // 5MB

            if (fileSize > maxSize) {
                showError(this, '파일 크기는 5MB를 초과할 수 없습니다.');
            } else {
                hideError(this);
                alert(`파일 "${fileName}"이(가) 선택되었습니다.`);
            }
        }
    });

    // 이메일 중복 체크
    emailCheckBtn.addEventListener('click', function() {
        const email = document.getElementById('center-email').value.trim();
        if (email === '') {
            showError(document.getElementById('center-email'), '이메일을 입력해주세요.');
            return;
        }

        // 이메일 형식 검사
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            showError(document.getElementById('center-email'), '올바른 이메일 형식이 아닙니다.');
            return;
        }

        // 서버에 중복 확인 요청
        fetch('/center/check-email?email=' + encodeURIComponent(email))
            .then(response => response.json())
            .then(data => {
                if (data.available) {
                    alert('사용 가능한 이메일입니다.');
                    isEmailAvailable = true;
                    hideError(document.getElementById('center-email'));
                } else {
                    showError(document.getElementById('center-email'), '이미 사용 중인 이메일입니다.');
                    isEmailAvailable = false;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('중복 확인 중 오류가 발생했습니다.');
            });
    });

    // 이메일 입력 필드 변경 시 중복 확인 상태 초기화
    document.getElementById('center-email').addEventListener('input', function() {
        isEmailAvailable = false;
        hideError(this);
    });

    // Cool SMS 인증 관련 코드
    phoneVerifyBtn.addEventListener('click', function() {
        const phoneNumber = document.getElementById('center-phone').value.trim();
        if (phoneNumber === '') {
            showError(document.getElementById('center-phone'), '휴대폰 번호를 입력해주세요.');
            return;
        }

        // 전화번호 형식 검사
        const phonePattern = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (!phonePattern.test(phoneNumber)) {
            showError(document.getElementById('center-phone'), '올바른 전화번호 형식이 아닙니다.');
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
                    document.getElementById('center-phoneVerifyCode').removeAttribute('disabled');
                    startTimer();
                    isPhoneVerified = false;  // 새로운 인증번호 발송 시 인증 상태 초기화
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
        const verificationCode = document.getElementById('center-phoneVerifyCode').value;
        const phoneNumber = document.getElementById('center-phone').value.replace(/-/g, '');

        if (!verificationCode) {
            showError(document.getElementById('center-phoneVerifyCode'), '인증번호를 입력해주세요.');
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
                    hideError(document.getElementById('center-phoneVerifyCode'));
                    stopTimer();
                    document.getElementById('center-phoneVerifyCode').setAttribute('disabled', 'true');
                    document.getElementById('center-phoneVerify').setAttribute('disabled', 'true');
                    document.getElementById('center-phoneVerifyConfirm').setAttribute('disabled', 'true');
                } else {
                    showError(document.getElementById('center-phoneVerifyCode'), '인증번호가 일치하지 않습니다.');
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
    const TIMER_DURATION = 180;

    function startTimer() {
        let timeLeft = TIMER_DURATION;
        const timerDisplay = document.createElement('span');
        timerDisplay.id = 'timer';
        document.getElementById('center-phoneVerifyCode').parentElement.appendChild(timerDisplay);

        timerInterval = setInterval(() => {
            const minutes = Math.floor(timeLeft / 60);
            const seconds = timeLeft % 60;
            timerDisplay.textContent = ` ${minutes}:${seconds.toString().padStart(2, '0')}`;

            if (timeLeft === 0) {
                stopTimer();
                alert('인증시간이 만료되었습니다. 다시 시도해주세요.');
                document.getElementById('center-phoneVerifyCode').value = '';
                document.getElementById('center-phoneVerifyCode').setAttribute('disabled', 'true');
                isPhoneVerified = false;
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
    document.getElementById('center-phone').addEventListener('input', function(e) {
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
        document.getElementById('center-phoneVerifyCode').value = '';
        document.getElementById('center-phoneVerifyCode').removeAttribute('disabled');
        isPhoneVerified = false;
    });

    // 주소 검색
    addressSearchBtn.addEventListener('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById('center-zipcode').value = data.zonecode;
                document.getElementById('center-address').value = data.address;
                document.getElementById('center-detailAddress').focus();
                hideError(document.getElementById('center-zipcode'));
                hideError(document.getElementById('center-address'));
            }
        }).open();
    });

    // 취소 버튼
    cancelBtn.addEventListener('click', function() {
        if (confirm('센터 회원가입을 취소하시겠습니까?')) {
            window.location.href = '/main/main';
        }
    });

    // 폼 제출
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        console.log('Form submission attempted');
        console.log('isPhoneVerified:', isPhoneVerified);

        if (validateForm()) {
            console.log('Form validation passed');
            const formData = new FormData(form);

            fetch('/center/center-join', {
                method: 'POST',
                body: formData
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Form submission successful');
                        alert('센터 회원가입이 완료되었습니다.');
                        window.location.href = '/center/center-joinOk';
                    } else {
                        throw new Error('회원가입 처리 중 오류가 발생했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Form submission error:', error);
                    alert(error.message);
                });
        } else {
            console.log('Form validation failed');
        }
    });

    function validateForm() {
        let isValid = true;
        requiredInputs.forEach(input => {
            if (input.value.trim() === '') {
                validateField(input);
                isValid = false;
            }
        });

        if (!isEmailAvailable) {
            showError(document.getElementById('center-email'), '이메일 중복 확인이 필요합니다.');
            isValid = false;
        }

        if (!isPhoneVerified) {
            showError(document.getElementById('center-phoneVerifyCode'), '연락처 인증이 완료되지 않았습니다.');
            isValid = false;
        }

        if (!useridPattern.test(useridInput.value)) {
            showError(useridInput, '아이디 형식이 올바르지 않습니다.');
            isValid = false;
        }

        if (!passwordPattern.test(passwordInput.value)) {
            showError(passwordInput, '비밀번호 형식이 올바르지 않습니다.');
            isValid = false;
        }

        if (passwordInput.value !== passwordConfirmInput.value) {
            showError(passwordConfirmInput, '비밀번호가 일치하지 않습니다.');
            isValid = false;
        }

        if (businessFileInput.files.length === 0) {
            showError(businessFileInput, '사업자등록증 파일을 첨부해주세요.');
            isValid = false;
        }

        return isValid;
    }

    // 아이디 유효성 검사
    useridInput.addEventListener('input', validateUserid);

    function validateUserid() {
        if (!useridPattern.test(useridInput.value)) {
            showError(useridInput, '아이디는 영문자로 시작하는 6~20자의 영문자 또는 숫자여야 합니다.');
        } else {
            hideError(useridInput);
        }
    }

    // 비밀번호 유효성 검사
    passwordInput.addEventListener('input', validatePassword);
    passwordConfirmInput.addEventListener('input', validatePassword);

    function validatePassword() {
        if (!passwordPattern.test(passwordInput.value)) {
            showError(passwordInput, '비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다.');
        } else {
            hideError(passwordInput);
        }

        if (passwordConfirmInput.value && passwordInput.value !== passwordConfirmInput.value) {
            showError(passwordConfirmInput, '비밀번호가 일치하지 않습니다.');
        } else if (passwordConfirmInput.value) {
            hideError(passwordConfirmInput);
        }
    }

    function validateField(field) {
        if (field.value.trim() === '') {
            let labelText = '입력값';
            const label = field.previousElementSibling;
            if (label && label.tagName === 'LABEL') {
                labelText = label.textContent.replace('*', '').trim();
            } else if (field.id) {
                switch(field.id) {
                    case 'center-phoneVerifyCode':
                        labelText = '인증번호';
                        break;
                    case 'center-phone':
                        labelText = '연락처';
                        break;
                    default:
                        labelText = field.id.replace('center-', '').replace(/([A-Z])/g, ' $1').toLowerCase();
                }
            }
            showError(field, `${labelText}을(를) 입력해주세요.`);
        } else {
            hideError(field);
        }
    }

    function showError(field, message) {
        field.classList.add('error');
        const errorElement = document.getElementById(`${field.id}-error`);
        if (errorElement) {
            errorElement.textContent = message;
            errorElement.style.display = 'block';
        }
    }

    function hideError(field) {
        field.classList.remove('error');
        const errorElement = document.getElementById(`${field.id}-error`);
        if (errorElement) {
            errorElement.style.display = 'none';
        }
    }

    // 필드 유효성 검사
    const requiredInputs = document.querySelectorAll('input[required]');
    requiredInputs.forEach(input => {
        input.addEventListener('blur', function() {
            if (!this.disabled) {
                validateField(this);
            }
        });
        input.addEventListener('input', function() {
            if (this.value.trim() !== '') {
                hideError(this);
            }
        });
    });
});