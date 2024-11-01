document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('findIdForm');
    const sendVerificationBtn = document.getElementById('sendVerification');
    const verifyCodeBtn = document.getElementById('verifyCode');
    const findIdButton = document.getElementById('findIdButton');
    const verificationGroup = document.getElementById('verificationGroup');

    let isPhoneVerified = false;
    findIdButton.disabled = true; // 초기에 아이디 찾기 버튼 비활성화

    // 전화번호 입력 필드에 자동 하이픈 추가
    const userPhone = document.getElementById('userPhone');
    userPhone.addEventListener('input', function(e) {
        const value = e.target.value.replace(/[^0-9]/g, '');
        let result = '';

        if (value.length > 3) {
            result += value.substr(0, 3);
            if (value.length > 7) {
                result += '-' + value.substr(3, 4) + '-' + value.substr(7);
            } else if (value.length > 3) {
                result += '-' + value.substr(3);
            }
        } else {
            result = value;
        }
        e.target.value = result;
    });

    // 인증번호 발송
    sendVerificationBtn.addEventListener('click', function() {
        const phoneNumber = userPhone.value.trim();
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
        const phoneNumber = userPhone.value.replace(/-/g, '');
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

        const centerMemberName = document.getElementById('userName').value;
        const centerMemberPhoneNumber = userPhone.value;

        // URL 파라미터 이름을 컨트롤러와 일치하게 수정
        fetch(`/center/center-findId?centerMemberName=${encodeURIComponent(centerMemberName)}&centerMemberPhoneNumber=${encodeURIComponent(centerMemberPhoneNumber)}`, {
            method: 'POST'
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        throw new Error(data.error || '아이디 찾기에 실패했습니다.');
                    });
                }
                return response.json();
            })
            .then(data => {
                // 성공시 리다이렉트 (파라미터 이름 수정)
                window.location.href = `/center/center-findIdOk?centerMemberName=${encodeURIComponent(centerMemberName)}&centerMemberId=${encodeURIComponent(data.centerMemberId)}`;
            })
            .catch(error => {
                console.error('Error:', error);
                alert(error.message);
            });
    });
});