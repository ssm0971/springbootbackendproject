// 글작성취소버튼
function cancleClick() {
    if (confirm('작성중인 글은 저장되지 않습니다. 정말로 취소하시겠습니까?')) {
        console.log('취소되었습니다.');
        location.href = '/adopt/review';
    } else {
        console.log('취소하지 않았습니다.');
    }
}

// 썸머노트 크기 및 튜닝
$(function () {
    const maxLength = 1000;

    $("#contents").summernote({
        width: 780,
        minHeight: 500,
        maxHeight: 500,
        placeholder: "내용을 입력해주세요",
        disableDrageAndDrop: true,
        focus: true,
        lang: 'ko-KR',
        toolbar: [
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
        }
    });
});




//헤더 이동 버튼
{
    let adoptPageBtn = document.getElementById('adoptPage');
    adoptPageBtn.addEventListener('click', function () {
        location.href = '/adopt/adopt';
    });
}

{
    let protectPageBtn = document.getElementById('protectPage');
    protectPageBtn.addEventListener('click', function () {
        location.href = '/adopt/protect';
    });
}

{
    let reviewPageBtn = document.getElementById('reviewPage');
    reviewPageBtn.addEventListener('click', function () {
        location.href = '/adopt/review';
    });
}


//날짜변환
function submitForm() {
    const startDate = document.querySelector('input[name="adoptPeriodstart"]').value;
    const endDate = document.querySelector('input[name="adoptPeriodend"]').value;

    // 문자열을 Date 객체로 변환
    const startDateObj = new Date(startDate);
    const endDateObj = new Date(endDate);

    // 필요한 경우 Date 객체를 처리하거나 서버에 전송
    console.log(startDateObj, endDateObj);

    // 폼 제출 (필요한 경우)
    document.getElementById('adoptForm').submit();
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
