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
        const adoptNo = element.querySelector('.adoptNo').innerText;
        location.href = `/adopt/adopt/adoptdetail?adoptNo=${adoptNo}`; // 상세 페이지로 이동
    });
});

//전체게시글 / 모집중인 게시글 구분
function filterAdoption(status) {
    if (status === 'all') {
        location.href = '/adopt/adopt';
    } else if (status === 'keep') {
        location.href = '/adopt/adoptKeep';
    }
}
