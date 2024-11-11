function checkLocalInput() {
  const inputField = document.getElementById('localInput');
  const breadError = document.getElementById('localError');
  const breadError2 = document.getElementById('localError2');

  if (inputField.value.trim() === '') {
    localError.style.display = 'block'; // 메시지 표시
    localError2.style.display = 'block'; // 메시지 표시
  } else {
    localError.style.display = 'none'; // 메시지 숨김
    localError2.style.display = 'none'; // 메시지 숨김
  }
}

function checkTimeInput() {
  const inputField = document.getElementById('timeInput');
  const timeError = document.getElementById('timeError');
  const timeError2 = document.getElementById('timeError2');

  if (inputField.value.trim() === '') {
    timeError.style.display = 'block'; // 메시지 표시
    timeError2.style.display = 'block'; // 메시지 표시
  } else {
    timeError.style.display = 'none'; // 메시지 숨김
    timeError2.style.display = 'none'; // 메시지 숨김
  }
}

function checkPeopleInput() {
  const inputField = document.getElementById('peopleInput');
  const peopleError = document.getElementById('peopleError');
  const peopleError2 = document.getElementById('peopleError2');

  if (inputField.value.trim() === '') {
    peopleError.style.display = 'block'; // 메시지 표시
    peopleError2.style.display = 'block'; // 메시지 표시
  } else {
    peopleError.style.display = 'none'; // 메시지 숨김
    peopleError2.style.display = 'none'; // 메시지 숨김
  }
}

// 썸머노트 크기 및 튜닝
$(function () {
  $("#contents").summernote({
      width: 780,                   // 가로값 설정
      minHeight: 500,                  // 높이값 설정
      maxHeight: 500,
      placeholder: "내용을 입력해주세요", // 안내 문구 설정
      disableDrageAndDrop: true,      //드래그
      focus: true,                  // 초기화 후 커서가 편집 가능한 영역에 포커스를 맞춤
      lang: 'ko-KR',                // 한글 설정, 기본값은 'en-US'
      toolbar: [
          // [groupName, [list of button]],
          ['insert', ['picture']],
          ['fontSize', ['fontsize']],
          ['fontName', ['fontname']],
          ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
          ['color', ['forecolor', 'backcolor']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['height', ['height']],
          ['table', ['table']],
          ['insert', ['link', 'video']],
          ['view', ['fullscreen', 'codeview', 'help']]
      ]
  });
});

// 글작성도중취소버튼
function modifyCancle() {
  if (confirm('정말 취소하시겠습니까? 수정중이던 글은 저장되지 않습니다')) {
    console.log('봉사모집 글수정이 취소되었습니다.');
    window.location.href = '../../html/volun/volun-volundetail.html'
  } else {
    console.log('봉사모집 글수정이 취소되지 않았습니다.');
  }
}

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