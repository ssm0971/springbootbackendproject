document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('.login-form');
    const memberIdInput = document.getElementById('memberId');
    const memberPwInput = document.getElementById('memberPw');
    const usernameError = document.getElementById('username-error');
    const passwordError = document.getElementById('password-error');

    function validateField(field, errorElement, emptyMessage) {
        if (field.value.trim() === '') {
            showError(field, errorElement, emptyMessage);
            return false;
        } else {
            hideError(field, errorElement);
            return true;
        }
    }

    function showError(field, errorElement, message) {
        field.classList.add('error');
        errorElement.textContent = message;
        errorElement.style.display = 'block';
    }

    function hideError(field, errorElement) {
        field.classList.remove('error');
        errorElement.style.display = 'none';
    }

    memberIdInput.addEventListener('blur', function() {
        validateField(this, usernameError, '아이디를 입력해주세요.');
    });

    memberPwInput.addEventListener('blur', function() {
        validateField(this, passwordError, '비밀번호를 입력해주세요.');
    });

    loginForm.addEventListener('submit', function(e) {
        let isIdValid = validateField(memberIdInput, usernameError, '아이디를 입력해주세요.');
        let isPwValid = validateField(memberPwInput, passwordError, '비밀번호를 입력해주세요.');

        if (!isIdValid || !isPwValid) {
            e.preventDefault();
        }
    });

    // 입력 시작할 때 에러 상태 제거
    memberIdInput.addEventListener('input', function() {
        hideError(this, usernameError);
    });

    memberPwInput.addEventListener('input', function() {
        hideError(this, passwordError);
    });
});