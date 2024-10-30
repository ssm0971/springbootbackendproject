document.querySelectorAll('.note-note-content').forEach(function(element) {
    element.addEventListener('click', function() {
        const noteboxReceiveNo = element.querySelector('.noteboxNo').innerText; // noteboxSendNo 가져오기
        console.log(noteboxReceiveNo)
        location.href = `/centerMypage/noteboxReceiveDetail?noteboxReceiveNo=${noteboxReceiveNo}`; // 상세 페이지로 이동
    });
});
