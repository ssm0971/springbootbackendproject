function checkBreedInput() {
  const inputField = document.getElementById('breedInput');
  const breadError = document.getElementById('breadError');
  const breadError2 = document.getElementById('breadError2');

  if (inputField.value.trim() === '') {
      breadError.style.display = 'block'; // 메시지 표시
      breadError2.style.display = 'block'; // 메시지 표시
  } else {
      breadError.style.display = 'none'; // 메시지 숨김
      breadError2.style.display = 'none'; // 메시지 숨김
  }
}

function checkWeightInput() {
  const inputField = document.getElementById('weightInput');
  const weightError = document.getElementById('weightError');
  const weightError2 = document.getElementById('weightError2');

  if (inputField.value.trim() === '') {
      weightError.style.display = 'block'; // 메시지 표시
      weightError2.style.display = 'block'; // 메시지 표시
  } else {
      weightError.style.display = 'none'; // 메시지 숨김
      weightError2.style.display = 'none'; // 메시지 숨김
  }
}

function checkIntroduceInput() {
  const inputField = document.getElementById('introduceInput');
  const introduceError = document.getElementById('introduceError');
  const introduceError2 = document.getElementById('introduceError2');

  if (inputField.value.trim() === '') {
      introduceError.style.display = 'block'; // 메시지 표시
      introduceError2.style.display = 'block'; // 메시지 표시
  } else {
      introduceError.style.display = 'none'; // 메시지 숨김
      introduceError2.style.display = 'none'; // 메시지 숨김
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
    console.log('입양 글수정이 취소되었습니다.');
    window.location.href = '../../html/adopt/adopt-adoptdetail.html'
  } else {
    console.log('입양 글수정이 취소되지 않았습니다.');
  }
}