
// 시간 선택 기능

// 1~24시 옵션 생성
const hourSelect = document.getElementById('hourInput');
for (let i = 0; i <= 12; i++) {
    const option = document.createElement('option');
    option.value = i;
    option.textContent = `${i}시간`;
    hourSelect.appendChild(option);
}

// 초기 값을 설정 (예: 12시)
document.getElementById('hourInput').value = 8;

document.getElementById('submitTime').addEventListener('click', function() {
    const hourInput = document.getElementById('hourInput').value;
    
});