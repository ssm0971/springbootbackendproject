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
document.querySelectorAll('.volun-banner3-list').forEach(function(element) {
    element.addEventListener('click', function() {
        const volunNo = element.querySelector('.volunNo').innerText;
        location.href = `/volun/volun/volundetail?volunNo=${volunNo}`; // 상세 페이지로 이동
    });
});