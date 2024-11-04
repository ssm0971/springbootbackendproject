
// 글작성취소버튼
function cancleClick() {
  if (confirm('작성중인 쪽지는 저장되지 않습니다. 정말로 취소하시겠습니까?')) {
    console.log('취소되었습니다.');
    location.href = '/mypage/noteboxReceiveList';
  } else {
    console.log('취소하지 않았습니다.');
  }
}