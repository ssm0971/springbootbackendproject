const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

document.getElementById("emailCheckButton").addEventListener("click", function () {
    const emailInput = document.getElementById("email").value;
    const currentEmail = document.getElementById("currentEmail").value;

    // 이메일 형식 유효성 검사
    if (!emailPattern.test(emailInput)) {
        document.getElementById("emailCheckResult").innerText = "유효한 이메일 형식을 입력하세요.";
        return;
    }

    // 중복 확인 요청
    fetch(`/centerMypage/check-email?newEmail=${emailInput}&currentEmail=${currentEmail}`, {
        method: "GET",
    })
        .then(response => response.json())
        .then(data => {
            if (data.available) {
                alert("사용 가능한 이메일입니다.");
                emailCheckButton.style.backgroundColor = "#9B805D"; // 버튼 색상 변경
            } else {
                alert("이미 사용 중인 이메일입니다.");
                emailCheckButton.style.backgroundColor = ""; // 기본 색상으로 복원
            }
        })
        .catch(error => {
            console.error("중복 확인 중 오류 발생:", error);
            alert("중복 확인 중 오류가 발생했습니다.");
        });
});


document.addEventListener('DOMContentLoaded', function () {
    const emailCheckBtn = document.getElementById('emailCheckBtn');
    const emailInput = document.getElementById('center-email');
    const errorMessageDiv = document.getElementById('error-message');
    let isEmailAvailable = false;

    // 오류 메시지 표시 함수
    function showError(input, message) {
        errorMessageDiv.textContent = message;
        errorMessageDiv.style.display = 'block';
        input.classList.add('error'); // CSS 클래스 추가로 스타일링 가능
    }

    // 오류 메시지 숨기기 함수
    function hideError(input) {
        errorMessageDiv.textContent = '';
        errorMessageDiv.style.display = 'none';
        input.classList.remove('error'); // CSS 클래스 제거
    }

    // 이메일 중복 체크 버튼 클릭 이벤트
    emailCheckBtn.addEventListener('click', function() {
        const email = emailInput.value.trim();

        // 이메일 입력값 확인
        if (email === '') {
            showError(emailInput, '이메일을 입력해주세요.');
            return;
        }

        // 이메일 형식 검사
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            showError(emailInput, '올바른 이메일 형식이 아닙니다.');
            return;
        }

        // 서버에 중복 확인 요청
        fetch('/centerMypage/check-email?email=' + encodeURIComponent(email))
            .then(response => response.json())
            .then(data => {
                if (data.available) {
                    alert('사용 가능한 이메일입니다.');
                    isEmailAvailable = true;
                    hideError(emailInput);
                } else {
                    showError(emailInput, '이미 사용 중인 이메일입니다.');
                    isEmailAvailable = false;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('중복 확인 중 오류가 발생했습니다.');
            });
    });
});

// 주소 검색
document.addEventListener('DOMContentLoaded', function () {
    const addressSearchBtn = document.getElementById('addressSearch');

    addressSearchBtn.addEventListener('click', function () {
        new daum.Postcode({
            oncomplete: function (data) {
                document.getElementById('zipcode').value = data.zonecode; // 우편번호 입력
                document.getElementById('address').value = data.address; // 주소 입력
                document.getElementById('detailAddress').focus(); // 상세주소 입력란에 포커스
            }
        }).open();
    });
});


document.addEventListener("DOMContentLoaded", function() {
    const currentPasswordInput = document.getElementById("password");
    const newPasswordInput = document.getElementById("newpassword");
    const confirmNewPasswordInput = document.getElementById("confirmNewPassword");
    const updateButton = document.getElementById("updateButton");
    const newPasswordStrengthMessage = document.getElementById("newPasswordStrengthMessage");
    const passwordMatchMessage = document.getElementById("passwordMatchMessage");
    const currentPasswordMessage = document.getElementById("currentPasswordMessage");

    // 비밀번호 조건을 위한 정규 표현식
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    // 모든 입력란 검증 함수
    function validateInputs() {
        const isCurrentPasswordFilled = currentPasswordInput.value.trim() !== "";
        const isNewPasswordValid = newPasswordInput.value.trim() === "" || passwordPattern.test(newPasswordInput.value);
        const isConfirmPasswordValid = confirmNewPasswordInput.value.trim() === "" || newPasswordInput.value === confirmNewPasswordInput.value;

        updateButton.disabled = !(isCurrentPasswordFilled && isNewPasswordValid && isConfirmPasswordValid);
    }

    // 현재 비밀번호 입력 시 비어 있는지 확인
    currentPasswordInput.addEventListener("input", function() {
        if (currentPasswordInput.value.trim() === "") {
            updateButton.disabled = true; // 현재 비밀번호가 비어있으면 버튼 비활성화
            currentPasswordMessage.style.display = "block"; // 경고 메시지 표시
        } else {
            currentPasswordMessage.style.display = "none"; // 경고 메시지 숨김
        }
        validateInputs(); // 입력 검증
    });

    // 새 비밀번호 입력 시 조건 체크
    newPasswordInput.addEventListener("input", function() {
        if (!passwordPattern.test(newPasswordInput.value)) {
            newPasswordStrengthMessage.style.display = "block";
        } else {
            newPasswordStrengthMessage.style.display = "none";
        }
        validateInputs(); // 입력 검증
    });

    // 새 비밀번호 확인 입력 시 일치 여부 체크
    confirmNewPasswordInput.addEventListener("input", function() {
        if (newPasswordInput.value !== confirmNewPasswordInput.value) {
            passwordMatchMessage.style.display = "block";
        } else {
            passwordMatchMessage.style.display = "none";
        }
        validateInputs(); // 입력 검증
    });

    // 비활성화된 버튼 클릭 시 알림 및 포커스 이동
    document.querySelector('.center-member-profile-button').addEventListener("click", function() {
        if (updateButton.disabled) {
            alert("정보를 수정하려면 현재 비밀번호를 입력해야 합니다.");
            currentPasswordInput.focus(); // 현재 비밀번호 입력란으로 포커스 이동
        }
    });

    // 페이지 로드 시 버튼 비활성화 상태 설정
    updateButton.disabled = true; // 초기 상태에서 버튼 비활성화
});

// document.getElementById("updateButton").addEventListener("click", function () {
//     const formData = new FormData(document.getElementById("profileForm")); // 프로필 폼 데이터
//
//     // AJAX 요청
//     fetch("/updateProfileOk", {
//         method: "POST",
//         body: formData,
//     })
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error("Network response was not ok");
//             }
//             return response.json(); // JSON 응답 반환
//         })
//         .then(data => {
//             // JSON 객체에서 메시지를 추출하여 모달에 표시
//             const messageString = data.message; // 필요한 메시지만 추출
//             document.getElementById("modalMessage").textContent = messageString; // 모달 메시지 설정
//             document.getElementById("customModal").style.display = "flex"; // 모달 표시
//
//             // 모달 확인 버튼 클릭 시 페이지 이동
//             document.getElementById("modalConfirmButton").onclick = function() {
//                 // 페이지 이동 (성공 시)
//                 if (data.message.includes("성공적으로")) {
//                     window.location.href = "/centerMypage/centerProfile"; // 성공 시 이동할 페이지
//                 }
//                 document.getElementById("customModal").style.display = "none"; // 모달 닫기
//             };
//         })
//         .catch(error => {
//             console.error("프로필 업데이트 중 오류 발생:", error);
//             alert("프로필 업데이트 중 오류가 발생했습니다."); // 오류 메시지 표시
//         });
// });



// document.getElementById("updateButton").addEventListener("click", function () {
//     const formData = new FormData(document.getElementById("profileForm")); // 프로필 폼 데이터
//
//     // AJAX 요청
//     fetch("/updateProfileOk", {
//         method: "POST",
//         body: formData,
//     })
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error("Network response was not ok");
//             }
//             return response.json(); // JSON 응답 반환
//         })
//         .then(data => {
//             // JSON 객체에서 메시지를 추출하여 alert 창으로 표시
//             const messageString = data.message; // 필요한 메시지만 추출
//             alert(messageString); // alert 창으로 표시
//
//             // 페이지 이동 (성공 시)
//             if (data.message.includes("성공적으로")) {
//                 window.location.href = "/centerMypage/centerProfile"; // 성공 시 이동할 페이지
//             }
//         })
//         .catch(error => {
//             console.error("프로필 업데이트 중 오류 발생:", error);
//             alert("프로필 업데이트 중 오류가 발생했습니다."); // 오류 메시지 표시
//         });
// });



