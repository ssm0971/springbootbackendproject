document.querySelectorAll('.note-note-content').forEach(function(element) {
    element.addEventListener('click', function() {
        const noteboxSendNo = element.querySelector('.noteboxNo').innerText; // noteboxSendNo 가져오기
        location.href = `/centerMypage/noteboxSendDetail?noteboxSendNo=${noteboxSendNo}`; // 상세 페이지로 이동
    });
});
