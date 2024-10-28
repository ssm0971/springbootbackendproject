document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('findIdForm');
    const sendVerificationBtn = document.getElementById('sendVerification');
    const verifyCodeBtn = document.getElementById('verifyCode');
    const findIdButton = document.getElementById('findIdButton');
    const verificationGroup = document.getElementById('verificationGroup');

    let isPhoneVerified = false;

    // Cool SMS 인증번호 발송
    sendVerificationBtn.addEventListener('click', function() {
        const phoneNumber = document.getElementById('userPhone').value.trim();
        if (phoneNumber === '') {
            alert('전화번호를 입력해주세요.');
            return;
        }

        // 전화번호 형식 검사
        const phonePattern = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (!phonePattern.test(phoneNumber)) {
            alert('올바른 전화번호 형식이 아닙니다.');
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
                    verificationGroup.style.display = 'block';
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
    verifyCodeBtn.addEventListener('click', function() {
        const phoneNumber = document.getElementById('userPhone').value.replace(/-/g, '');
        const verificationCode = document.getElementById('verificationCode').value;

        if (!verificationCode) {
            alert('인증번호를 입력해주세요.');
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
                    findIdButton.disabled = false;
                } else {
                    alert('인증번호가 일치하지 않습니다.');
                    isPhoneVerified = false;
                    findIdButton.disabled = true;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('인증 확인 중 오류가 발생했습니다.');
            });
    });

    // 아이디 찾기 버튼 클릭
    findIdButton.addEventListener('click', function() {
        if (!isPhoneVerified) {
            alert('전화번호 인증을 완료해주세요.');
            return;
        }

        const memberName = document.getElementById('userName').value;
        const memberPhoneNumber = document.getElementById('userPhone').value;

        // URL 파라미터로 변경
        fetch(`/member/find-id?memberName=${encodeURIComponent(memberName)}&memberPhoneNumber=${encodeURIComponent(memberPhoneNumber)}`, {
            method: 'POST'
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('아이디 찾기에 실패했습니다.');
            })
            .then(data => {
                window.location.href = `/member/find-idOk?memberName=${encodeURIComponent(memberName)}&memberId=${encodeURIComponent(data.memberId)}`;
            })
            .catch(error => {
                console.error('Error:', error);
                alert(error.message);
            });
    });
});