// 게시글수정 버튼
function modifyClick() {
  const volunNo = document.querySelector('.volunNo').textContent.trim(); // adoptNo를 HTML에서 가져옵니다.
  const centerMemberNo = document.querySelector('.centerMemberNo').textContent.trim(); // centerMemberNo를 HTML에서 가져옵니다.

  if (confirm('정말 수정하시겠습니까?')) {
    console.log('수정페이지로 이동합니다.');
    location.href = `/volun/volun/volunmodify?volunNo=${volunNo}&centerMemberNo=${centerMemberNo}`;
  } else {
    console.log('신청서페이지로 이동하지 않습니다.');
  }
}

// 글 삭제 버튼
function deleteClick() {
  // adoptNo 가져오기
  const volunNo = document.querySelector('.volunNo').textContent.trim();

  if (confirm('정말 삭제하시겠습니까?')) {
    console.log('입양글이 삭제되었습니다.');
    console.log(volunNo);

    // adoptNo를 URL에 포함시켜 이동
    location.href = `/volun/volun/volunDelete?volunNo=${volunNo}`;
  } else {
    console.log('입양글이 삭제되지 않았습니다.');
  }
}

// 글 마감 버튼
function endClick() {
  // adoptNo 가져오기
  const volunNo = document.querySelector('.volunNo').textContent.trim();

  if (confirm('정말 마감하시겠습니까? 마감 시 취소할 수 없습니다.')) {
    console.log('입양글이 마감되었습니다.');
    console.log(volunNo);

    // adoptNo를 URL에 포함시켜 이동
    location.href = `/volun/volun/volunStatusEnd?volunNo=${volunNo}`;
  } else {
    console.log('입양글이 마감되지 않았습니다.');
  }
}

// 게시글 신고 버튼
function ContentReportClick() {
  const reportContent = prompt('신고사유를 100글자 이내로 입력해주세요');
  const volunNo = document.querySelector('.volunNo').textContent.trim();

  location.href = `/volun/volun/volunContentReport?volunNo=${volunNo}&reportContent=${encodeURIComponent(reportContent)}`;
}

// 신청서 이동 버튼
function requestClick() {
  const volunNo = document.querySelector('.volunNo').textContent.trim(); // adoptNo를 HTML에서 가져옵니다.
  const centerMemberNo = document.querySelector('.centerMemberNo').textContent.trim(); // centerMemberNo를 HTML에서 가져옵니다.

  if (confirm('정말 신청하시겠습니까?')) {
    console.log('신청서페이지로 이동합니다.');
    location.href = `/volun/volun/volunrequest?volunNo=${volunNo}&centerMemberNo=${centerMemberNo}`;
  } else {
    console.log('신청서페이지로 이동하지 않습니다.');
  }
}

// 수정된댓글등록버튼
function editCommentBtnClick(index) {
  const commentBox1 = document.getElementById(`volun-comment-buttonBox-${index}`); // 수정/삭제버튼 div
  const commentBox2 = document.getElementById(`volun-modifyInput-${index}`);       // 댓글수정하는 div
  const commentBox3 = document.getElementById(`volun-comment-${index}`);           // 이미 입력된 댓글

  // 등록버튼 눌렀을시 (수정/삭제 div와 이미 있는 댓글 block, 댓글 입력창 none)
  commentBox1.style.display = 'block';
  commentBox3.style.display = 'block';
  commentBox2.style.display = 'none';
}


// 댓글 수정 버튼
// 댓글을 th:each를 사용하여 여러개를 표시 그로 인해 같은 class/id를 가진 댓글이 여러개 생성되므로
// 댓글 수정/삭제/신고 시 가장 상단의 댓글에만 적용이 가능하여 index번호를 붙여 구분하여 사용
function modifyCommentBtnClick(index) {
  const commentBox1 = document.getElementById(`volun-comment-buttonBox-${index}`);
  const commentBox2 = document.getElementById(`volun-modifyInput-${index}`);
  const commentBox3 = document.getElementById(`volun-comment-${index}`);

  // 수정 버튼을 누르면 수정/삭제 div와 기존 댓글을 숨기고, 수정 입력창을 표시
  commentBox1.style.display = 'none';
  commentBox3.style.display = 'none';
  commentBox2.style.display = 'block';
}


//댓글삭제 딜리트매핑사용
function CommentDeleteClick(volunCommentNo, volunNo) {
  if (confirm('정말 삭제하시겠습니까?')) {
    // DELETE 요청 보내기
    fetch(`/volun/volun/volunCommentDelete?volunCommentNo=${volunCommentNo}&volunNo=${volunNo}`, {
      method: 'DELETE' //메소드타입을 delete로 사용하여 위 경로로 보냄(위 경로는 deleteMapping을 사용할 경로)
    })
        .then(response => { //response < 서버의 응답
          if (response.ok) {        //서버의 응답이 ok일때 = 정상적으로 딜리트매핑으로 이동후 댓글삭제가 진행됐을때 아래 페이지로 이동
            window.location.href = `/volun/volun/volundetail?volunNo=${volunNo}`;
          } else {
            alert('댓글 삭제에 실패했습니다.');
          }
        })
        .catch(error => {   //그외또다른오류가 발생했을시 오류 발생 알터창 출력
          console.error('Error:', error);
          alert('오류가 발생했습니다.');
        });
  }
}

// 댓글 신고 버튼
function CommentReportClick() {
  const reportComment = prompt('신고사유를 100글자 이내로 입력해주세요');
  const volunNo = document.querySelector('.volunNo').textContent.trim();
  const volunCommentNo = document.querySelector('.volunCommentNo').textContent.trim();

  location.href = `/volun/volun/volunCommentReport?volunNo=${volunNo}&reportComment=${encodeURIComponent(reportComment)}&volunCommentNo=${volunCommentNo}`;
} // 아가다가 댓글 신고 만들었어 대박이지 헤헤


//상단헤더이동
{
  let volunPageBtn = document.getElementById('volunPage');
  volunPageBtn.addEventListener('click', function(){
    location.href='/volun/volun';
  });
}

{
  let carPageBtn = document.getElementById('carPage');
  carPageBtn.addEventListener('click', function(){
    location.href='/car/main';
  });
}

// 댓글 미입력 방지
function validateCommentForm() {
  const commentInput = document.querySelector('.volun-detail-commentregi');

  if (!commentInput.value.trim()) { // 입력 값이 비어 있거나 공백만 있을 때
    alert("댓글을 입력해 주세요.");
    return false; // 폼 제출 중단
  }
  return true; // 폼 제출 허용
}