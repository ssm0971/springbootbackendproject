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

// 회원 탈퇴 확인 함수
function confirmWithdrawal() {
  alert("회원 탈퇴가 완료되었습니다.");
  window.location.href = '../../html/main/main.html'
  closeModal();
}