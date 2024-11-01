
// 글작성취소버튼
function cancleClick() {
  if (confirm('작성중인 글은 저장되지 않습니다. 정말로 취소하시겠습니까?')) {
    console.log('취소되었습니다.');
    window.location.href = '/dona/list';
  } else {
    console.log('취소하지 않았습니다.');
  }
}



// 썸머노트 크기 및 튜닝
$(function () {
    $("#donaContent").summernote({
        width: 780,                   // 가로값 설정
        minHeight: 500,              // 높이값 설정
        maxHeight: 500,
        placeholder: "내용을 입력해주세요", // 안내 문구 설정
        disableDrageAndDrop: true,   // 드래그
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
            ['table', ['table']],
            ['insert', ['link', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ],
        callbacks: {
            onBlur: function() {
                var contents = $('#donaContent').summernote('code'); // 현재 내용 가져오기
                var editorText = f_SkipTags_html(contents); // HTML 태그 제거 함수 호출
                editorText = editorText.replace(/\s/gi, ""); // 줄바꿈 제거
                editorText = editorText.replace(/&nbsp;/gi, ""); // 공백 제거

                // 수정된 내용을 다시 summernote에 설정 (필요에 따라)
                $('#donaContent').summernote('code', editorText);
            }
        }
    });
});