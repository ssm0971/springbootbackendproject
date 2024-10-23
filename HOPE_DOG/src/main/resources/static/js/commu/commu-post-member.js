// 댓글등록 알터창
function Comment() {
  const comment = document.getElementById('commentInput').value.trim();
  
  if (comment === '') {
    alert('입력한 내용이 없습니다.');
    return false; // 입력 내용이 없을 경우
  }
  return true; // 입력 내용이 있을 경우 
}

//게시글 

// 수정, 삭제,신고 confirm창
function modifyAlert() {
  const userConfirmed = confirm("수정하시겠습니까?");
  if (userConfirmed) {
      window.location.href ='../../html/commu/commu-post-rewrite.html'; // 수정페이지로 이동
  }
}

function deleteAlert() {
  const userConfirmed = confirm("삭제하시겠습니까?");
  if (userConfirmed) {
      alert("삭제가 완료되었습니다."); // 확인 눌렀을 때 알림 창 표시
      window.location.href = "../../html/commu/commu-main-member.html"; // 커뮤 메인으로 이동
  }
}

function endAlert() {
  const contentReport = prompt('신고사유를 100글자 이내로 입력해주세요');
  
  if (contentReport !== null) { // 사용자가 확인을 누르면
    console.log('게시글이 신고되었습니다');
    alert('게시글이 신고되었습니다'); // 알림창 추가
    
  } else {
    console.log('게시글 신고가 취소되었습니다.');
    alert('게시글 신고가 취소되었습니다.'); // 알림창 추가
  }
}