// 댓글수정버튼

//댓글 수정 버튼 눌렀을 때
function modifyCommentBtnClcik() {
  const commentBox1 = document.getElementById('review-comment-buttonBox'); //수정/삭제버튼 div
  const commentBox2 = document.getElementById('review-modifyInput');       //댓글수정하는div
  const commentBox3 = document.getElementById('review-comment');           //이미입력된댓글

  // 수정버튼 눌렀을시(수정/삭제 div와 이미 있는 댓글 none , 댓글입력창 block)
  commentBox1.style.display = 'none'; //수정,삭제 버튼 숨김
  commentBox3.style.display = 'none'; //현재 댓글 숨김
  commentBox2.style.display = 'block'; // 수정 입력란 보임
}

// 댓글등록버튼 눌렀을 때
function editCommentBtnClcik() {
  const commentBox1 = document.getElementById('review-comment-buttonBox'); //수정/삭제버튼 div
  const commentBox2 = document.getElementById('review-modifyInput');       //댓글수정하는div
  const commentBox3 = document.getElementById('review-comment');           //이미입력된댓글

  // 등록버튼 눌렀을 때(수정,삭제 버튼과 현재 댓글 보이게 하고 , 수정 입력란 숨김)
  commentBox1.style.display = 'block'; //수정,삭제 버튼 보이게
  commentBox3.style.display = 'block'; //현재 댓글 보이게
  commentBox2.style.display = 'none'; //수정 입력란 숨김
}

//댓글 삭제 confirm창
function CommentDeleteClick() {
  const userConfirmed = confirm("댓글을 삭제하시겠습니까?");
  
  // 사용자가 '확인'을 클릭한 경우
  if (userConfirmed) {
    alert("댓글이 삭제되었습니다."); // 삭제 완료 알림

  } else {
    alert("삭제가 취소되었습니다."); // 취소 버튼 눌렀을 때
  }
}

//게시글 

// 글 수정, 삭제,신고 confirm창
function modifyAlert() {
  const userConfirmed = confirm("수정하시겠습니까?");
  if (userConfirmed) {
      window.location.href ='../../html/commu/commu-post-rewrite.html'; // 확인 눌렀을 때 수정페이지로 이동
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

//댓글신고
function CommentReportClick() {
  const commentReport = prompt('신고사유를 100글자 이내로 입력해주세요');
  
  if (commentReport !== null) { // 사용자가 확인을 눌렀을 때
    console.log('댓글이 신고되었습니다');
    alert('댓글이 신고되었습니다'); // 수정된 부분
  } else {
    console.log('댓글 신고가 취소되었습니다.');
    alert('댓글 신고가 취소되었습니다.'); // 수정된 부분
  }
}


//댓글 
// 댓글 등록 알터창
function Comment() {
  const comment = document.getElementById('commentInput').value.trim();
  
  if (comment === '') {
    alert('입력한 내용이 없습니다.');
    return false; // 입력한 내용이 없을 경우
  }
  return true; // 입력한 내용이 있을 경우
}