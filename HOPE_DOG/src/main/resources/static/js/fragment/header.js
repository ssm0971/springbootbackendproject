document.addEventListener('DOMContentLoaded', function() {
    console.log("Script loaded"); // 스크립트 로드 확인

    // 알림 종 클릭 이벤트
    document.querySelectorAll('.notification-bell').forEach(bell => {
        console.log("Bell found"); // 종 요소 확인

        bell.addEventListener('click', function(event) {
            console.log("Bell clicked"); // 클릭 이벤트 확인

            event.preventDefault();
            event.stopPropagation();

            const container = this.closest('.notification-container');
            if (!container) {
                console.log("No container found");
                return;
            }

            // 드롭다운 토글
            const dropdown = container.querySelector('.notification-dropdown');
            if (dropdown) {
                const isVisible = dropdown.style.display === 'block';
                dropdown.style.display = isVisible ? 'none' : 'block';
            }
        });
    });

    // 문서 클릭 시 드롭다운 닫기
    document.addEventListener('click', function(event) {
        const dropdowns = document.querySelectorAll('.notification-dropdown');
        dropdowns.forEach(dropdown => {
            if (!event.target.closest('.notification-container')) {
                dropdown.style.display = 'none';
            }
        });
    });

    // 모두 읽음 처리 버튼
    document.querySelectorAll('.mark-all-read').forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();
            event.stopPropagation();

            const container = this.closest('.notification-container');
            const unreadItems = container.querySelectorAll('.notification-item.unread');
            unreadItems.forEach(item => item.classList.remove('unread'));

            const badge = container.querySelector('.notification-badge');
            if (badge) badge.textContent = '0';
        });
    });

    // 개별 알림 클릭
    document.querySelectorAll('.notification-item').forEach(item => {
        item.addEventListener('click', function(event) {
            event.stopPropagation();
            this.classList.remove('unread');
        });
    });
});