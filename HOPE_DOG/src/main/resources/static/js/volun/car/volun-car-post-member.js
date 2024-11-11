// 게시글수정 버튼
function modifyClick() {
  const carNo = document.querySelector('.carNo').textContent.trim(); // carNo를 HTML에서 가져옵니다.
  const carWriter = document.querySelector('.carWriter').textContent.trim(); // carNo를 HTML에서 가져옵니다.


  if (confirm('정말 수정하시겠습니까?')) {
    console.log('수정페이지로 이동합니다.');
    // centerMemberNo와 memberNo를 둘 다 URL에 포함
    location.href = `/car/carmodify?carNo=${carNo}&carWriter=${carWriter}`;
  } else {
    console.log('신청서페이지로 이동하지 않습니다.');
  }
}


//게시글 삭제
function deleteAlert() {
  const carNo = document.querySelector('.carNo').textContent.trim();

  if (confirm('정말 삭제하시겠습니까?')) {
      console.log('글이 삭제되었습니다.');
      console.log(carNo);

      location.href = `/car/carDelete?carNo=${carNo}`;
  }else {
      console.log('게시글이 삭제되지 않았습니다.');
  }


}

//게시글 신고버튼
function ReportAlert() {
  const reportContent = prompt('신고사유를 100글자 이내로 입력해주세요');
  const carNo = document.querySelector('.carNo').textContent.trim();
  //carNo인 요소에서 텍스트 내용 가져오고 carNo 변수에 저장

  location.href = `/car/carContentReport?carNo=${carNo}&reportContent=${encodeURIComponent(reportContent)}`;


}

//댓글 수정 버튼
function modifyCommentBtnClick(index) {
    const commentBox1 = document.getElementById(`car-comment-buttonBox-${index}`);
    const commentBox2 = document.getElementById(`car-modifyInput-${index}`);
    const commentBox3 = document.getElementById(`car-comment-${index}`);

    // 수정 버튼을 누르면 수정/삭제 div와 기존 댓글을 숨기고, 수정 입력창을 표시
    commentBox1.style.display = 'none';
    // 수정,삭제 버튼div
    commentBox3.style.display = 'none';
    //댓글 수정 입력창
    commentBox2.style.display = 'block';
    //기존 댓글 div
}


function editCommentBtnClick(index) {
    const commentBox1 = document.getElementById(`car-comment-buttonBox-${index}`); // 수정/삭제 버튼 div
    const commentBox2 = document.getElementById(`car-modifyInput-${index}`);       // 댓글 수정하는 div
    const commentBox3 = document.getElementById(`car-comment-${index}`);           // 기존 댓글

    // 수정한 댓글 값을 가져옴
    const modifiedComment = document.getElementById(`modifiedCommentInput-${index}`).value;

    // AJAX 요청을 통해 수정된 댓글을 서버로 전송
    const carNo = document.getElementById(`carNo-${index}`).value; // 게시글 번호
    const carCommentNo = document.getElementById(`carCommentNo-${index}`).value; // 댓글 번호

    fetch('/car/carCommentModi', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            carNo: carNo,
            carCommentNo: carCommentNo,
            carComment: modifiedComment
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // 서버에서 수정된 댓글을 받아서 화면에 갱신
                commentBox3.textContent = modifiedComment; // 기존 댓글을 수정된 댓글로 갱신
                commentBox1.style.display = 'block'; // 수정/삭제 div 표시
                commentBox3.style.display = 'block'; // 기존 댓글 표시
                commentBox2.style.display = 'none'; // 댓글 수정 입력창 숨기기
            } else {
                alert('댓글 수정에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('댓글 수정 요청에 문제가 발생했습니다.');
        });
}

// 댓글삭제버튼
function CommentDeleteClick(carCommentNo, carNo) {
    if (confirm('정말 삭제하시겠습니까?')) {
        // 삭제 요청을 위한 폼 생성
        const form = document.createElement('form');
        form.method = 'post'; // POST 방식
        form.action = `/car/carCommentDelete`; // URL 설정

        // carCommentNo와 carNo를 위한 input 요소 추가
        const carCommentNoInput = document.createElement('input');
        carCommentNoInput.type = 'hidden';
        carCommentNoInput.name = 'carCommentNo';
        carCommentNoInput.value = carCommentNo;
        form.appendChild(carCommentNoInput);

        const carNoInput = document.createElement('input');
        carNoInput.type = 'hidden';
        carNoInput.name = 'carNo';
        carNoInput.value = carNo;
        form.appendChild(carNoInput);

        // 폼 제출
        document.body.appendChild(form);
        form.submit(); // 폼 제출
    }
}

//댓글 신고 버튼
function CommentReportClick() {
    const reportComment = prompt('신고사유를 100글자 이내로 입력해주세요');
    const carNo = document.querySelector('.carNo').textContent.trim();
    const carCommentNo = document.querySelector('.carCommentNo').textContent.trim();
    console.log('carNo:', carNo);
    console.log('carCommentNo:', carCommentNo);
    console.log('reportComment:', reportComment);

    location.href = `/car/carCommentReport?carNo=${carNo}&reportComment=${encodeURIComponent(reportComment)}&carCommentNo=${carCommentNo}`;
}

// 댓글 미입력 방지
function validateCommentForm() {
    const commentInput = document.querySelector('.car-detail-commentregi');

    if (!commentInput.value.trim()) { // 입력 값이 비어 있거나 공백만 있을 때
        alert("댓글을 입력해 주세요.");
        return false; // 폼 제출 중단
    }
    return true; // 폼 제출 허용
}

