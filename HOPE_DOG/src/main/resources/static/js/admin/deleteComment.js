$(document).ready(function () {
    // 삭제 버튼 클릭 시 선택된 게시글 삭제
    $(".deleteButton").click(function () {
        const itemList = [];

        // 체크된 게시글들의 postNo와 postType 수집
        $(".checkbox:checked").each(function () {
            itemList.push({
                commentType: $(this).data("comment-type"),
                commentNo: $(this).data("comment-no")
            });
        });

        if (itemList.length === 0) {
            alert("삭제할 게시글을 선택하세요.");
            return;
        }

        // Ajax 요청을 통해 서버로 post 정보 전송
        $.ajax({
            url: '/admin/deleteComment',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(itemList),  // 객체 배열 전송
            success: function (response) {
                alert(response.message || "선택된 댓글이 삭제되었습니다.");
                location.reload(); // 페이지 새로고침으로 삭제 반영
            },
            error: function (xhr, status, error) {
                alert("댓글 삭제 중 오류가 발생했습니다.");
            }
        });
    });
});
