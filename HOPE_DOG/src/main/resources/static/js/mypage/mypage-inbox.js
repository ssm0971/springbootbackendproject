
// 글작성삭제버튼
function deleteClick() {
  if (confirm('정말로 삭제하시겠습니까?')) {
    console.log('삭제되었습니다.');
    window.location.href = '../../html/mypage/mypage-inbox.html'; 
  } else {
    console.log('삭제하지 않았습니다.');
  }
}


document.querySelector('#member-outbox').addEventListener('click', function() {
  location.href = '/mypage/noteboxS'; // 목록 페이지로 이동
});