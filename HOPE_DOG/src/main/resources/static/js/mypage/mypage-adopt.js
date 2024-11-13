// 게시글로 이동
document.querySelectorAll('.mypage-adopt-list').forEach(function(element) {
    element.addEventListener('click', function() {
        const adoptRequestNo = element.querySelector('.adoptRequestNo').innerText;
        window.location.href = '/mypage/updateAdoptRequest?adoptRequestNo=' + adoptRequestNo; // 상세 페이지로 이동
    });

});

document.addEventListener("DOMContentLoaded", function() {
    const items = $('.mypage-adopt-list ul'); // 게시글 항목들을 li로 선택

    // 게시글 수가 10개 이하인 경우 페이지네이션 처리
    if (items.length <= 10) {
        items.show(); // 모든 항목 표시
        return; // 페이지네이션 초기화 중지
    }

    // 처음 10개 항목만 보이게 하고 나머지는 숨김
    items.hide().slice(0, 10).show(); // 첫 10개 항목만 표시

    // 페이지네이션 설정
    const container = $('#pagination');
    const pageSize = 10; // 한 페이지에 보여줄 항목 수

    container.pagination({
        dataSource: items.toArray(), // 게시글 항목을 배열로 변환
        pageSize: pageSize,
        callback: function (data, pagination) {
            items.hide(); // 기존에 보여진 항목 숨김
            $.each(data, function (index, item) {
                $(item).show(); // 현재 페이지에 해당하는 항목만 표시
            });
        }
    });

    // 페이지네이션 플러그인이 초기화된 후에 첫 번째 페이지로 이동
    container.pagination('goToPage', 1);
});
