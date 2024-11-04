$(document).ready(function () {
    // 게시글 삭제 버튼 클릭 이벤트
    $(".post-delete-button").click(function () {
        const item ={
            postNo: $(this).data("post-no"),
            postType: $(this).data("post-type")
        }
        if (confirm("정말로 이 게시글을 삭제하시겠습니까?")) {
            $.ajax({
                url: '/admin/deletePostDetail', // 게시글 삭제 URL
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(item),
                success: function (response) {
                    alert(response.message || "게시글이 삭제되었습니다.");
                    location.href = '/admin/postList'; // 삭제 후 목록 페이지로 이동
                },
                error: function () {
                    alert("게시글 삭제 중 오류가 발생했습니다.");
                }
            });
        }
    });

    // 댓글 삭제 버튼 클릭 이벤트
    $(".post-comment-delete-button").click(function () {
        const item={
            commentNo: $(this).data("comment-no"),
            commentType: $(this).data("comment-type"),
        }
        if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
            $.ajax({
                url: '/admin/deleteCommentDetail', // 댓글 삭제 URL
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(item),
                success: function (response) {
                    alert(response.message || "댓글이 삭제되었습니다.");
                    location.reload(); // 댓글 삭제 후 페이지 새로고침
                },
                error: function (xhr) {
                    alert("댓글 삭제 중 오류가 발생했습니다: " + xhr.responseText);
                }
            });
        }
    });
});