// 게시글수정 버튼
function modifyClick() {
  const reviewNo = document.querySelector('.reviewNo').textContent.trim(); // adoptNo를 HTML에서 가져옵니다.
  const memberNo = document.querySelector('.memberNo').textContent.trim(); // centerMemberNo를 HTML에서 가져옵니다.

  if (confirm('정말 수정하시겠습니까?')) {
    console.log('수정페이지로 이동합니다.');
    location.href = `/adopt/review/reviewmodify?reviewNo=${reviewNo}&memberNo=${memberNo}`;
  } else {
    console.log('신청서페이지로 이동하지 않습니다.');
  }
}

// 글 삭제 버튼
function deleteClick() {
  // adoptNo 가져오기
  const reviewNo = document.querySelector('.reviewNo').textContent.trim();

  if (confirm('정말 삭제하시겠습니까?')) {
    console.log('입양글이 삭제되었습니다.');
    console.log(reviewNo);

    // adoptNo를 URL에 포함시켜 이동
    location.href = `/adopt/review/reviewDelete?reviewNo=${reviewNo}`;
  } else {
    console.log('입양글이 삭제되지 않았습니다.');
  }
}

// 게시글 신고 버튼
function ContentReportClick() {
  const reportContent = prompt('신고사유를 100글자 이내로 입력해주세요');
  const reviewNo = document.querySelector('.reviewNo').textContent.trim();

  location.href = `/adopt/review/reviewContentReport?reviewNo=${reviewNo}&reportContent=${encodeURIComponent(reportContent)}`;
}

// URL에서 파라미터를 가져오는 함수
function getParameter(name) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(name);
}

// 댓글 수정 버튼
function modifyCommentBtnClick(index) {
  const commentBox1 = document.getElementById(`adopt-comment-buttonBox-${index}`);
  const commentBox2 = document.getElementById(`adopt-modifyInput-${index}`);
  const commentBox3 = document.getElementById(`adopt-comment-${index}`);

  // 수정 버튼을 누르면 수정/삭제 div와 기존 댓글을 숨기고, 수정 입력창을 표시
  commentBox1.style.display = 'none';
  commentBox3.style.display = 'none';
  commentBox2.style.display = 'block';
}

// 댓글등록버튼
function editCommentBtnClick(index) {
  const commentBox1 = document.getElementById(`adopt-comment-buttonBox-${index}`); // 수정/삭제버튼 div
  const commentBox2 = document.getElementById(`adopt-modifyInput-${index}`);       // 댓글수정하는 div
  const commentBox3 = document.getElementById(`adopt-comment-${index}`);           // 이미 입력된 댓글

  // 등록버튼 눌렀을시 (수정/삭제 div와 이미 있는 댓글 block, 댓글 입력창 none)
  commentBox1.style.display = 'block';
  commentBox3.style.display = 'block';
  commentBox2.style.display = 'none';
}


// 댓글삭제버튼
function CommentDeleteClick(reviewCommentNo, reviewNo) {
  if (confirm('정말 삭제하시겠습니까?')) {
    // 삭제 요청을 위한 폼 생성
    const form = document.createElement('form');
    form.method = 'post'; // POST 방식
    form.action = `/adopt/review/reviewCommentDelete`; // URL 설정

    // adoptCommentNo와 adoptNo를 위한 input 요소 추가
    const reviewCommentNoInput = document.createElement('input');
    reviewCommentNoInput.type = 'hidden';
    reviewCommentNoInput.name = 'reviewCommentNo';
    reviewCommentNoInput.value = reviewCommentNo;
    form.appendChild(reviewCommentNoInput);

    const reviewNoInput = document.createElement('input');
    reviewNoInput.type = 'hidden';
    reviewNoInput.name = 'reviewNo';
    reviewNoInput.value = reviewNo;
    form.appendChild(reviewNoInput);

    // 폼 제출
    document.body.appendChild(form);
    form.submit(); // 폼 제출
  }
}

// 댓글 신고 버튼
function CommentReportClick() {
  const reportComment = prompt('신고사유를 100글자 이내로 입력해주세요');
  const reviewNo = document.querySelector('.reviewNo').textContent.trim();
  const reviewCommentNo = document.querySelector('.reviewCommentNo').textContent.trim();

  console.log(reviewNo);
  console.log(reportComment);
  console.log(reviewCommentNo);

  location.href = `/adopt/review/reviewCommentReport?reviewNo=${reviewNo}&reportComment=${encodeURIComponent(reportComment)}&reviewCommentNo=${reviewCommentNo}`;
} // 아가다가 댓글 신고 만들었어 대박이지 헤헤


//상단헤드이동
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

// 댓글 미입력 방지
function validateCommentForm() {
  const commentInput = document.querySelector('.adopt-detail-commentregi');

  if (!commentInput.value.trim()) { // 입력 값이 비어 있거나 공백만 있을 때
    alert("댓글을 입력해 주세요.");
    return false; // 폼 제출 중단
  }
  return true; // 폼 제출 허용
}