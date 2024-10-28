function filter(category) {
    // AJAX 요청을 통해 서버에 카테고리 필터링 요청
    fetch(`/car/filter?cate=${encodeURIComponent(category)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // HTML 응답으로 처리
        })
        .then(html => {
            // 게시글 목록을 업데이트
            const container = document.querySelector('.volun-catalog-all');
            container.innerHTML = ''; // 기존 내용을 비움
            container.innerHTML = html; // 새로운 HTML 삽입

            // URL 변경
            history.pushState(null, '', `/car/filter?cate=${encodeURIComponent(category)}`);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}
