//상단헤더이동
{
    let volunPageBtn = document.getElementById('volunPage');
    volunPageBtn.addEventListener('click', function(){
        location.href='/volun/volun';
    });
}

{
    let carPageBtn = document.getElementById('carPage');
    carPageBtn.addEventListener('click', function(){
        location.href='/car/main';
    });
}


//게시글로 이동
document.querySelectorAll('.volun-post').forEach(function(element) {
    element.addEventListener('click', function() {
        const volunNo = element.querySelector('.volunNo').innerText;
        location.href = `/volun/volun/volundetail?volunNo=${volunNo}`; // 상세 페이지로 이동
    });
});

//전체게시글 / 모집중인 게시글 구분
function filterAdoption(status) {
    if (status === 'all') {
        location.href = '/volun/volun';
    } else if (status === 'keep') {
        location.href = '/volun/volunKeep';
    }
}