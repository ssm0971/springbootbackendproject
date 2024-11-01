
//게시글로 이동
// document.querySelectorAll('.dona-ul-all').forEach(function(element) {
//     element.addEventListener('click', function() {
//         const donaNo = element.querySelector('.donaNo').innerText;
//         location.href = `/dona/view?donaNo=${donaNo}`; // 상세 페이지로 이동
//     });
// });


document.querySelector('.dona-admain-btu').addEventListener('click', function() {
    location.href = '/dona/write'; // 목록 페이지로 이동
});

