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
          ['insert', ['picture']], //그림
          ['fontSize', ['fontsize']], //글자크기
          ['fontName', ['fontname']], //글꼴선택
          ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']], //글자 스타일
          ['color', ['forecolor', 'backcolor']], //글자 및 배경색 선택
          ['para', ['ul', 'ol', 'paragraph']], //목록 및 단락 정리
          ['height', ['height']], //높이조절
          ['table', ['table']],//표 삽입
          ['insert', ['link', 'video']], //링크, 동영상 
          ['view', ['fullscreen', 'codeview', 'help']] //전체화면 , 코드 ,도움말
      ]
  });
});

// 게시글 등록 confirm 창
function registration() {
  const subject = document.getElementById('subject').value.trim();
  const contents = $('#contents').summernote('code'); // Summernote의 내용을 가져옴

  if (subject === '' || contents === '<p><br></p>') { // 빈 내용 체크
    alert('작성된 글이 없습니다.');
    return false; // 입력 내용이 없을 경우
  }

  // 내용이 있을 때 확인 창
  if (confirm('게시글을 등록하시겠습니까?')) {
    alert('등록이 완료되었습니다.');
    location.href = '../../html/volun/volun-car-main-member.html'; // 카풀 메인으로 이동
  } else {
    // 확인 창에서 취소했을 때 아무 동작도 하지 않음
    return false; // 입력 내용이 그대로 유지됨
  }

  return true; // 내용이 있을 경우
}

// 취소
function Cancel() {
  const subject = document.getElementById('subject').value.trim();
  const contents = $('#contents').summernote('code'); // Summernote의 내용을 가져옴

  if (subject === '' && contents === '<p><br></p>') { // 빈 내용 체크
    // 아무 내용도 입력되지 않은 경우
    location.href = '../../html/volun/volun-car-main-member.html'; // 바로 이동
  } else {
    // 내용이 입력된 경우 알림창
    const confirmResult = confirm('작성된 내용이 저장되지 않습니다. 취소하시겠습니까?');
    if (confirmResult) {
      location.href = '../../html/volun/volun-car-main-member.html'; // 확인 시 이동
    }
    // 아니요 버튼을 누르면 페이지 그대로 유지
  }
}

