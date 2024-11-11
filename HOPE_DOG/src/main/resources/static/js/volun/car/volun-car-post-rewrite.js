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



// 취소
function Cancel() {
    if (confirm('작성중인 글은 저장되지 않습니다. 정말로 취소하시겠습니까?')) {
        console.log('취소되었습니다.');
        location.href = '/car/main';
    } else {
        console.log('취소하지 않았습니다.');
    }
}
