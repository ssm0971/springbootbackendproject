

function goDelete() {
  if (confirm('게시글을 삭제하시겠습니까?')) {
    var donaNo = document.querySelector('input[name="donaNo"]').value;

    // Form을 통해 POST 요청으로 서버에 삭제를 요청
    var form = document.createElement('form');
    form.method = 'post';
    form.action = '/dona/delete/' + donaNo;

    document.body.appendChild(form);
    form.submit();
  }
}

{
  // 버튼 처리
  let $modifyBtn = document.querySelector('.btn-modify');
  let $removeBtn = document.querySelector('.btn-remove');
  let $backBtn = document.querySelector('.btn-back');

  // 수정 버튼 처리
  $modifyBtn?.addEventListener('click', function (){
    let donaNo = this.dataset.id; //클릭된 요소의 data-id 속성값 가져와 변수에 저장
    //this : eventhandler 내에서 이벤트가 발생한 요소
    //dataset : 요소의 모든 data-* 속성을 포함
    //id : data-id 속성의 값 가져오기
    console.log(donaNo);
    location.href = `/dona/modify?donaNo=${donaNo}`;
  });

  // 삭제 버튼 처리
  $removeBtn?.addEventListener('click', function (){
    let donaNo = this.dataset.id;
    console.log(donaNo);
    location.href=`/dona/delete?donaNo=${donaNo}`;
  });

  //뒤로가기 버튼 처리
  $backBtn?.addEventListener('click', function (){
    window.history.back();
  });
}

let boardId = document.querySelector('#donaNo').value;

//목록으로 이동
document.querySelector('.dona-admain-btuall-complaint').addEventListener('click', function() {
  location.href = '/dona/list'; // 목록 페이지로 이동
});
