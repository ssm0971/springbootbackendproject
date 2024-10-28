//게시글로 이동
document.querySelectorAll('.notice-ul-all').forEach(function(element) {
    element.addEventListener('click', function() {
        const noticeNo = element.querySelector('.noticeNo').innerText;
        location.href = `/notice/view?noticeNo=${noticeNo}`; // 상세 페이지로 이동
    });
});