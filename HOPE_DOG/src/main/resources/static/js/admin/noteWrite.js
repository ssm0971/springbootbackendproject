$(document).ready(function() {
    $('#noteForm').on('submit', function(event) {
        event.preventDefault(); // 기본 폼 제출 방지

        // 입력값 가져오기
        const title = $('#note-board-title').val();
        const receiver = $('#note-board-receiver').val();
        const content = $('#note-board-content').val();

        // AJAX 요청 보내기
        $.ajax({
            type: 'POST',
            url: '/admin/noteboxWrite',
            data: {
                title: title,
                receiver: receiver,
                content: content
            },
            success: function(response) {
                alert('쪽지가 성공적으로 전송되었습니다.');
                // 필요 시 추가 로직 (예: 페이지 리다이렉트 등)
            },
            error: function(xhr, status, error) {
                alert('쪽지 전송에 실패했습니다: ' + error);
            }
        });
    });
});