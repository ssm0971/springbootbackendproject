// 모달을 보여주는 함수
function showModal() {
  const agreementInput = document.getElementById('agreement').value.trim();

  if (agreementInput === "동의합니다") {
    document.getElementById('modalOverlay').style.display = 'block';
    document.getElementById('modal').style.display = 'block';
  } else {
    alert("정확히 '동의합니다'라고 입력해야합니다."); // 경고 메시지
  }
}

// 모달을 닫는 함수
function closeModal() {
  document.getElementById('modalOverlay').style.display = 'none';
  document.getElementById('modal').style.display = 'none';
}

// 탈퇴 확인 후 서버 요청
function confirmWithdrawal() {
  const agreement = document.getElementById('agreement').value.trim();
  if (agreement !== "동의합니다") {
    alert("동의 문구를 정확히 입력해주세요.");
    return;
  }

  // AJAX 요청을 통해 탈퇴 요청을 서버로 전송
  fetch('/mypage/withdrawalOK', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({}) // 필요한 데이터가 있다면 여기에 추가
  })
      .then(response => {
        if (response.ok) {
          alert("회원 탈퇴가 완료되었습니다.\n이용해주셔서 감사합니다.");
          // 잠시 후 메인 페이지로 리다이렉트
          setTimeout(() => {
            window.location.href = '/main/main'; // 페이지 이동
          }, 2000); // 2초 뒤에 이동
        } else {
          alert("탈퇴 처리 중 오류가 발생했습니다.");
        }
      })
      .catch(error => {
        console.error("Error:", error);
        alert("네트워크 오류가 발생했습니다.");
      });

  closeModal(); // 모달 닫기
}

