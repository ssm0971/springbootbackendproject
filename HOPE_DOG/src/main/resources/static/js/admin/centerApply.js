$(document).ready(function () {
    // 삭제 버튼 클릭 시 선택된 회원 삭제
    $(".approveButton").click(function () {
        const itemList = [];

        // 체크된 회원들의 memberNo 수집
        $(".checkbox:checked").each(function () {
            itemList.push($(this).data("center-member-no"));
        });

        if (itemList.length === 0) {
            alert("회원을 선택하세요.");
            return;
        }

        // Ajax 요청을 통해 서버로 memberNo 전송
        $.ajax({
            url: '/admin/approveCenterMember',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(itemList),  // 배열만 전송
            success: function (response) {
                alert(response.message || "선택된 회원이 승인되었습니다.");
                location.reload(); // 페이지 새로고침으로 삭제 반영
            },
            error: function (xhr, status, error) {
                alert("회원 승인 중 오류가 발생했습니다.");
            }
        });
    });

    // 삭제 버튼 클릭 시 선택된 회원 삭제
    $(".approveOneButton").click(function () {
        const itemList = [];

        itemList.push($(this).data("center-member-no"));
        console.log("Center Member No:", $(this).data("center-member-no"));  // 로그로 값 확인
        // Ajax 요청을 통해 서버로 memberNo 전송
        $.ajax({
            url: '/admin/approveCenterMember',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(itemList),  // 배열만 전송
            success: function (response) {
                alert(response.message || "선택된 회원이 승인되었습니다.");
                location.href='/admin/centerApplyList'; // 페이지 새로고침으로 삭제 반영
            },
            error: function (xhr, status, error) {
                alert("회원 승인 중 오류가 발생했습니다.");
            }
        });
    });

    // 삭제 버튼 클릭 시 선택된 회원 삭제
    $(".refuseButton").click(function () {
        const itemList = [];

        // 체크된 회원들의 memberNo 수집
        $(".checkbox:checked").each(function () {
            itemList.push($(this).data("center-member-no"));
        });

        if (itemList.length === 0) {
            alert("회원을 선택하세요.");
            return;
        }

        // Ajax 요청을 통해 서버로 memberNo 전송
        $.ajax({
            url: '/admin/refuseCenterMember',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(itemList),  // 배열만 전송
            success: function (response) {
                alert(response.message || "선택된 회원이 거절되었습니다.");
                location.reload(); // 페이지 새로고침으로 삭제 반영
            },
            error: function (xhr, status, error) {
                alert("회원 승인 중 오류가 발생했습니다.");
            }
        });
    });

    // 삭제 버튼 클릭 시 선택된 회원 삭제
    $(".refuseOneButton").click(function () {
        const itemList = [];

        itemList.push($(this).data("center-member-no"));

        // Ajax 요청을 통해 서버로 memberNo 전송
        $.ajax({
            url: '/admin/refuseCenterMember',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(itemList),  // 배열만 전송
            success: function (response) {
                alert(response.message || "선택된 회원이 거절되었습니다.");
                location.href='/admin/centerApplyList'; // 페이지 새로고침으로 삭제 반영
            },
            error: function (xhr, status, error) {
                alert("회원 승인 중 오류가 발생했습니다.");
            }
        });
    });
});
