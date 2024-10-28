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

    let isPhoneVerified = false;
    let isNicknameAvailable = false;
    let isEmailAvailable = false;

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
        if (phoneNumber === '') {
            showError(document.getElementById('memberPhoneNumber'), '휴대폰 번호를 입력해주세요.');
            return;
        }

        const phonePattern = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (!phonePattern.test(phoneNumber)) {
            showError(document.getElementById('memberPhoneNumber'), '올바른 전화번호 형식이 아닙니다.');
            return;
        }

        const cleanPhoneNumber = phoneNumber.replace(/-/g, '');

        fetch('/api/sms/send', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ phoneNumber: cleanPhoneNumber })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('인증번호가 발송되었습니다.');
                    document.getElementById('phoneVerifyCode').removeAttribute('disabled');
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
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ phoneNumber: phoneNumber, code: verificationCode })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('휴대폰 인증이 완료되었습니다.');
                    isPhoneVerified = true;
                    hideError(document.getElementById('phoneVerifyCode'));
                    stopTimer();
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
        if (timerDisplay) timerDisplay.remove();
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