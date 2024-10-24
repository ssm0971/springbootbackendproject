// 글작성도중취소버튼
function modifyCancle() {
  if (confirm('정말 취소하시겠습니까? 수정중이던 글은 저장되지 않습니다')) {
    console.log('후기 글수정이 취소되었습니다.');
    window.location.href = '../../html/adopt/adopt-reviewdetail.html'
  } else {
    console.log('후기 글수정이 취소되지 않았습니다.');
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