$(document).ready(function () {

    $(".deleteButton").click(function () {
        const itemList = [];
        
        $(".checkbox:checked").each(function () {
            itemList.push($(this).data("notebox-receive-no"));
        });

        if (itemList.length === 0) {
            alert("삭제할 쪽지를 선택하세요.");
            return;
        }
        
        $.ajax({
            url: '/admin/deleteNoteIn',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(itemList),  // 배열만 전송
            success: function (response) {
                alert(response.message || "선택한 쪽지가 삭제되었습니다.");
                location.reload(); // 페이지 새로고침으로 삭제 반영
            },
            error: function (xhr, status, error) {
                alert("쪽지 삭제 중 오류가 발생했습니다.");
            }
        });
    });

    // 삭제 버튼 클릭 시 선택된 회원 삭제
    $(".deleteOneButton").click(function () {
        const itemList = [];

        itemList.push($(this).data("notebox-receive-no"));

        // Ajax 요청을 통해 서버로 memberNo 전송
        $.ajax({
            url: '/admin/deleteNoteIn',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(itemList),  // 배열만 전송
            success: function (response) {
                alert(response.message || "선택된 회원이 삭제되었습니다.");
                location.reload(); // 페이지 새로고침으로 삭제 반영
            },
            error: function (xhr, status, error) {
                alert("회원 삭제 중 오류가 발생했습니다.");
            }
        });
    });
});
