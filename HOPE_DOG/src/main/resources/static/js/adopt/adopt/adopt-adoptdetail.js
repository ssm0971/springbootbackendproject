// 글수정버튼
function modifyClick() {
  if (confirm('정말 수정하시겠습니까?')) {
    console.log('입양글이 수정되었습니다.');
    window.location.href = '../../html/adopt/adopt-adoptmodify.html'
  } else {
    console.log('입양글이 수정되지 않았습니다.');
  }
}

// 글 삭제 버튼
function deleteClick() {
  // adoptNo 가져오기
  const adoptNo = document.querySelector('.adoptNo').textContent.trim();

  if (confirm('정말 삭제하시겠습니까?')) {
    console.log('입양글이 삭제되었습니다.');
    console.log(adoptNo);

    // adoptNo를 URL에 포함시켜 이동
    location.href = `/adopt/adopt/adoptDelete?adoptNo=${adoptNo}`;
  } else {
    console.log('입양글이 삭제되지 않았습니다.');
  }
}


// 글 마감 버튼
function endClick() {
  // adoptNo 가져오기
  const adoptNo = document.querySelector('.adoptNo').textContent.trim();

  if (confirm('정말 마감하시겠습니까? 마감 시 취소할 수 없습니다.')) {
    console.log('입양글이 마감되었습니다.');
    console.log(adoptNo);

    // adoptNo를 URL에 포함시켜 이동
    location.href = `/adopt/adopt/adoptStatusEnd?adoptNo=${adoptNo}`;
  } else {
    console.log('입양글이 마감되지 않았습니다.');
  }
}

// 게시글 신고 버튼
function ContentReportClick() {
  const reportContent = prompt('신고사유를 100글자 이내로 입력해주세요');
  const adoptNo = document.querySelector('.adoptNo').textContent.trim();

  location.href = `/adopt/adopt/adoptReport?adoptNo=${adoptNo}&reportContent=${encodeURIComponent(reportContent)}`;
}

// URL에서 파라미터를 가져오는 함수
function getParameter(name) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(name);
}

// 신청서 이동 버튼
function requestClick() {
  const adoptNo = document.querySelector('.adoptNo').textContent.trim(); // adoptNo를 HTML에서 가져옵니다.
  const centerMemberNo = document.querySelector('.centerMemberNo').textContent.trim(); // centerMemberNo를 HTML에서 가져옵니다.

  if (confirm('정말 신청하시겠습니까?')) {
    console.log('신청서페이지로 이동합니다.');
    location.href = `/adopt/adopt/adoptrequest?adoptNo=${adoptNo}&centerMemberNo=${centerMemberNo}`;
  } else {
    console.log('신청서페이지로 이동하지 않습니다.');
  }
}

// 댓글수정버튼
function modifyCommentBtnClcik() {
  const commentBox1 = document.getElementById('adopt-comment-buttonBox'); //수정/삭제버튼 div
  const commentBox2 = document.getElementById('adopt-modifyInput');       //댓글수정하는div
  const commentBox3 = document.getElementById('adopt-comment');           //이미입력된댓글

  // 수정버튼 눌렀을시(수정/삭제 div와 이미 있는 댓글 none , 댓글입력창 block)
  commentBox1.style.display = 'none';
  commentBox3.style.display = 'none';
  commentBox2.style.display = 'block';
}

// 댓글등록버튼
function editCommentBtnClcik() {
  const commentBox1 = document.getElementById('adopt-comment-buttonBox'); //수정/삭제버튼 div
  const commentBox2 = document.getElementById('adopt-modifyInput');       //댓글수정하는div
  const commentBox3 = document.getElementById('adopt-comment');           //이미입력된댓글

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

