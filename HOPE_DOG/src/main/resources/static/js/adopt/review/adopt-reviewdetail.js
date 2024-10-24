// 글수정버튼
function modifyClick() {
  if (confirm('정말 수정하시겠습니까?')) {
    console.log('입양글이 수정되었습니다.');
    window.location.href = '../../html/adopt/adopt-reviewmodify.html'
  } else {
    console.log('입양글이 수정되지 않았습니다.');
  }
}

// 글삭제버튼
function deleteClick() {
  if (confirm('정말 삭제하시겠습니까?')) {
    console.log('입양글이 삭제되었습니다.');
    window.location.href = '../../html/adopt/adopt-review.html'
  } else {
    console.log('입양글이 삭제되지 않았습니다.');
  }
}

// 댓글수정버튼
function modifyCommentBtnClcik() {
  const commentBox1 = document.getElementById('review-comment-buttonBox'); //수정/삭제버튼 div
  const commentBox2 = document.getElementById('review-modifyInput');       //댓글수정하는div
  const commentBox3 = document.getElementById('review-comment');           //이미입력된댓글

  // 수정버튼 눌렀을시(수정/삭제 div와 이미 있는 댓글 none , 댓글입력창 block)
  commentBox1.style.display = 'none';
  commentBox3.style.display = 'none';
  commentBox2.style.display = 'block';
}

// 댓글등록버튼
function editCommentBtnClcik() {
  const commentBox1 = document.getElementById('review-comment-buttonBox'); //수정/삭제버튼 div
  const commentBox2 = document.getElementById('review-modifyInput');       //댓글수정하는div
  const commentBox3 = document.getElementById('review-comment');           //이미입력된댓글

  // 등록버튼 눌렀을시(수정/삭제 div와 이미 있는 댓글 block , 댓글입력창 none)
  commentBox1.style.display = 'block';
  commentBox3.style.display = 'block';
  commentBox2.style.display = 'none';
}


// 댓글삭제버튼
function CommentDeleteClick() {
  if (confirm('정말 삭제하시겠습니까?')) {
    console.log('입양댓글이 삭제되었습니다.');
  } else {
    console.log('입양댓글이 삭제되지 않았습니다.');
  }
}

// 게시글 신고 버튼
function ContentReportClick() {
  const contentReport = prompt('신고사유를 100글자 이내로 입력해주세요');
  if (result) {
    console.log('게시글이 신고되었습니다')
    // 이후에 값 넘기기
  } else {
    console.log('게시글신고가 취소되었습니다.')
  }

}

// 댓글 신고 버튼
function CommentReportClick() {
  const commentReport = prompt('신고사유를 100글자 이내로 입력해주세요');
  if (result) {
    console.log('댓글이 신고되었습니다')
    // 이후에 값 넘기기
  } else {
    console.log('댓글신고가 취소되었습니다.')
  }

}


{
  let adoptPageBtn = document.getElementById('adoptPage');
  adoptPageBtn.addEventListener('click', function(){
    location.href='/adopt/adopt';
  });
}

{
  let protectPageBtn = document.getElementById('protectPage');
  protectPageBtn.addEventListener('click', function(){
    location.href='/adopt/protect';
  });
}

{
  let reviewPageBtn = document.getElementById('reviewPage');
  reviewPageBtn.addEventListener('click', function(){
    location.href='/adopt/review';
  });
}