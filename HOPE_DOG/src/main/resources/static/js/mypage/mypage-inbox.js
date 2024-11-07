
// 글작성삭제버튼
// function deleteClick() {
//   if (confirm('정말로 삭제하시겠습니까?')) {
//     console.log('삭제되었습니다.');
//     window.location.href = '../../html/mypage/mypage-inbox.html';
//   } else {
//     console.log('삭제하지 않았습니다.');
//   }
// }



document.querySelectorAll('.member-notebox-contentsbox').forEach(function(element) {
  element.addEventListener('click', function() {
    const noteboxReceiveNo = element.querySelector('.noteboxReceiveNo').innerText; // noteboxSendNo 가져오기
    location.href = `/mypage/noteboxReceiveDetail?noteboxReceiveNo=${noteboxReceiveNo}`; // 상세 페이지로 이동
  });
});

//   페이지네이션
$(function() {
  const items = $('.member-notebox-contentsbox');  // 게시글 항목 선택

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

// 삭제버튼 클릭 시 삭제 완료 알림창을 띄우는 함수
function showDeleteAlert() {
  alert('삭제가 완료되었습니다');
}