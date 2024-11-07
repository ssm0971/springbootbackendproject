{
    let adoptPageBtn = document.getElementById('adoptPage');
    adoptPageBtn.addEventListener('click', function(){
        location.href='/adopt/adopt';
    });
}

{
    let protectPageBtn = document.getElementById('protectPage');
    protectPageBtn.addEventListener('click', function(){
        location.href='/adopt/protect';
    });
}

{
    let reviewPageBtn = document.getElementById('reviewPage');
    reviewPageBtn.addEventListener('click', function(){
        location.href='/adopt/review';
    });
}

//게시글로 이동
document.querySelectorAll('.adopt-post').forEach(function(element) {
    element.addEventListener('click', function() {
        const reviewNo = element.querySelector('.adoptNo').innerText;
        location.href = `/adopt/review/reviewdetail?reviewNo=${reviewNo}`; // 상세 페이지로 이동
    });
});
