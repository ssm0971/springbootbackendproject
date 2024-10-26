document.addEventListener('DOMContentLoaded', function() {
  const deleteButtons = document.querySelectorAll('.delete-button');

  deleteButtons.forEach(button => {
      button.addEventListener('click', function() {
          const noteStatusDiv = this.closest('.notebox-list-contents').querySelector('.note-status');
          const isRead = noteStatusDiv.getAttribute('data-status') === 'true';

          let message;
          if (!isRead) {
              message = '아직 읽지 않은 쪽지입니다. 정말 삭제하시겠습니까?';
          } else {
              message = '삭제하시겠습니까?';
          }

          if (confirm(message)) {
              // 삭제 로직을 여기에 추가하세요.
              alert('삭제되었습니다.'); // 예시로 알림을 추가
          } else {
              alert('삭제가 취소되었습니다.');
          }
      });
  });
});

