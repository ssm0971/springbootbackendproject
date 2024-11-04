
// URL에서 파라미터를 가져오는 함수
function getParameter(name) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(name);
}

// 글 삭제 버튼
function deleteClick() {
  // donaNo 가져오기
  const donaNo = document.querySelector('.donaNo').textContent.trim();

  console.log('donaNo:', donaNo); // donaNo 값을 콘솔에 출력하여 확인

  if (!donaNo) {
    console.error('donaNo가 유효하지 않습니다.');
  }

  if (confirm('정말 삭제하시겠습니까?')) {
    console.log('글이 삭제되었습니다.');

    // donaNo URL에 포함시켜 이동
    location.href = `/dona/delete?donaNo=${donaNo}`;
  } else {
    console.log('글이 삭제되지 않았습니다.');
  }
}



  // 버튼 처리
  let $modifyBtn = document.querySelector('.btn-modify');

  // 수정 버튼 처리
  $modifyBtn?.addEventListener('click', function (){
    let donaNo = this.dataset.id; //클릭된 요소의 data-id 속성값 가져와 변수에 저장
    //this : eventhandler 내에서 이벤트가 발생한 요소
    //dataset : 요소의 모든 data-* 속성을 포함
    //id : data-id 속성의 값 가져오기
    console.log(donaNo);
    location.href = `/dona/modify?donaNo=${donaNo}`;
  });


