// // 게시글 항목 클릭 시 상세 페이지로 이동
// document.querySelectorAll('.member-profile-cumuboard-contentbox').forEach(function(element) {
//     element.addEventListener('click', function() {
//         // commuNo, carNo, reviewNo 값을 가져옴
//         const commuNo = element.querySelector('.commuNo').innerText;
//         const carNo = element.querySelector('.carNo').innerText;
//         const reviewNo = element.querySelector('.reviewNo').innerText;
//
//         // commuNo가 존재하면 커뮤니티 상세 페이지로 이동
//         if (commuNo) {
//             window.location.href = '/commu/post/' + commuNo; // 커뮤니티 상세 페이지로 이동
//         }
//         // carNo가 존재하면 카풀 상세 페이지로 이동
//         else if (carNo) {
//             window.location.href = '/car/post/' + carNo; // 카풀 상세 페이지로 이동
//         }
//         // reviewNo가 존재하면 리뷰 상세 페이지로 이동
//         else if (reviewNo) {
//             window.location.href = '/review/reviewdetail?reviewNo=' + reviewNo; // 리뷰 상세 페이지로 이동
//         }
//     });
// });

// 게시글 항목 클릭 시 상세 페이지로 이동
document.querySelectorAll('.member-profile-cumuboard-contentbox').forEach(function(element) {
    element.addEventListener('click', function() {
        // commuNo, carNo, reviewNo 값을 가져옴
        const commuNo = element.querySelector('.commuNo').innerText;
        const carNo = element.querySelector('.carNo').innerText;
        const reviewNo = element.querySelector('.reviewNo').innerText;

        // 게시글의 type 값을 data-type 속성에서 가져옴
        const type = element.querySelector('li').getAttribute('data-type');

        // type에 따라 이동할 페이지를 결정
        if (type === 'COMMU') {
            if (commuNo) {
                window.location.href = '/commu/post/' + commuNo; // 커뮤니티 상세 페이지로 이동
            }
        } else if (type === 'CAR') {
            if (carNo) {
                window.location.href = '/car/post/' + carNo; // 카풀 상세 페이지로 이동
            }
        } else if (type === 'REVIEW') {
            if (reviewNo) {
                window.location.href = '/adopt/review/reviewdetail?reviewNo=' + reviewNo; // 리뷰 상세 페이지로 이동
            }
        }
    });
});


//   페이지네이션
$(function() {
    const items = $('.member-profile-cumuboard-contentbox');  // 게시글 항목 선택

    // 게시글 수가 10개 이하인 경우 페이지네이션 처리
    if (items.length <= 10) {
        items.show(); // 모든 항목 표시
        return; // 페이지네이션 초기화 중지
    }

    // 처음 10개 항목만 보이게 하고 나머지는 숨김
    items.hide().slice(0, 10).show(); // 첫 10개 항목만 표시

    // 페이지네이션 설정
    const container = $('#pagination');
    const pageSize = 10; // 한 페이지에 보여줄 항목 수 (첫 페이지 10개)

    container.pagination({
        dataSource: items.toArray(), // 게시글 항목을 배열로 변환
        pageSize: pageSize,
        callback: function(data, pagination) {
            items.hide(); // 기존에 보여진 항목 숨김
            $.each(data, function(index, item) {
                $(item).show(); // 현재 페이지에 해당하는 항목만 표시
            });
        }
    });

    // 처음 로드 시 첫 번째 페이지의 항목만 보여주기
    container.pagination('goToPage', 1);
});