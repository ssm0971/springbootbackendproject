// 썸머노트 크기 및 튜닝
$(function () {
    $("#contents").summernote({
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
            // onBlur: function() {
            //     var contents = $('#contents').summernote('code'); // 현재 내용 가져오기
            //     var editorText = f_SkipTags_html(contents); // HTML 태그 제거 함수 호출
            //     editorText = editorText.replace(/\s/gi, ""); // 줄바꿈 제거
            //     editorText = editorText.replace(/&nbsp;/gi, ""); // 공백 제거
            //
            //     // 수정된 내용을 다시 summernote에 설정 (필요에 따라)
            //     $('#contents').summernote('code', editorText);
            // }
        }
    });
});

// 글작성취소버튼
function Cancel() {
    if (confirm('작성중인 글은 저장되지 않습니다. 정말로 취소하시겠습니까?')) {
        console.log('취소되었습니다.');
        location.href = '/commu/main';
    } else {
        console.log('취소하지 않았습니다.');
    }
}

// 폼 제출 시 제목이나 내용이 비었을 경우 경고창 표시
function validatePostForm() {
    var title = document.getElementById("subject").value;
    var content = document.getElementById("contents").value;

    // 제목 또는 내용이 비어 있을 경우 경고창을 띄우고, 폼 제출을 막음
    if (title.trim() === "" || content.trim() === "") {
        alert("제목과 내용을 모두 입력해주세요.");
        return false; // 폼 제출을 막음
    }

    return true; // 폼 제출 허용
}










