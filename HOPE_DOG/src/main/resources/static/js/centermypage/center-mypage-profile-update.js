document.addEventListener('DOMContentLoaded', function () {
    const emailSendButton = document.getElementById('emailSendButton');
    const phoneSendButton = document.getElementById('phoneSendButton');
    const passwordInput = document.getElementById('password');
    const passwordCheckInput = document.getElementById('passwordcheck');
    const passwordMismatchMessage = document.getElementById('passwordMismatchMessage');
    const passwordStrengthMessage = document.getElementById('passwordStrengthMessage');

    // 이메일 인증전송 버튼 클릭 이벤트
    emailSendButton.addEventListener('click', function () {
        const email = document.getElementById('email').value;

        // 이메일 형식 정규 표현식
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        if (email) {
            if (emailPattern.test(email)) {
                alert(`${email}로 인증번호가 전송되었습니다.`);
            } else {
                alert('이메일 형식으로 입력해주세요.');
            }
        } else {
            alert('이메일을 입력해주세요.');
        }
    });

    // 전화번호 인증전송 버튼 클릭 이벤트
    phoneSendButton.addEventListener('click', function () {
        const phoneNumber = document.getElementById('phonenumber').value;

        // 전화번호 정규 표현식 (010XXXXXXXX 형식)
        const phonePattern = /^010\d{8}$/;

        if (phoneNumber) {
            if (phonePattern.test(phoneNumber)) {
                alert(`${phoneNumber}로 인증번호가 전송되었습니다.`);
            } else {
                alert('전화번호는 01000000000 형식으로 숫자만 입력해주세요.');
            }
        } else {
            alert('전화번호를 입력해주세요.');
        }
    });

    // 비밀번호 입력 이벤트
    passwordInput.addEventListener('input', function () {
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

        if (!passwordPattern.test(passwordInput.value)) {
            passwordStrengthMessage.style.display = 'block';
        } else {
            passwordStrengthMessage.style.display = 'none';
        }
    });

    // 비밀번호 확인 입력 이벤트
    passwordCheckInput.addEventListener('input', function () {
        if (passwordInput.value !== passwordCheckInput.value) {
            passwordMismatchMessage.style.display = 'block';
        } else {
            passwordMismatchMessage.style.display = 'none';
        }
    });
});