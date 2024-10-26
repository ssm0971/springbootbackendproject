document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('center-login-form');
    const idInput = document.getElementById('centerId');
    const pwInput = document.getElementById('centerPw');
    const idError = document.getElementById('id-error');
    const pwError = document.getElementById('pw-error');

    function showError(field, errorElement, message) {
        field.classList.add('error');
        errorElement.textContent = message;
        errorElement.style.display = 'block';
    }

    function hideError(field, errorElement) {
        field.classList.remove('error');
        errorElement.style.display = 'none';
    }

    // 입력값 검증
    idInput.addEventListener('blur', function() {
        if (this.value.trim() === '') {
            showError(this, idError, '아이디를 입력해주세요.');
        } else {
            hideError(this, idError);
        }
    });

    pwInput.addEventListener('blur', function() {
        if (this.value.trim() === '') {
            showError(this, pwError, '비밀번호를 입력해주세요.');
        } else {
            hideError(this, pwError);
        }
    });

    // 입력 시작할 때 에러 상태 제거
    idInput.addEventListener('input', function() {
        hideError(this, idError);
    });

    pwInput.addEventListener('input', function() {
        hideError(this, pwError);
    });
});