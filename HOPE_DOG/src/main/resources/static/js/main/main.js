document.addEventListener('DOMContentLoaded', function() {
    const chatIcon = document.getElementById('chatIcon');
    const chatWindow = document.getElementById('chatWindow');
    const closeChat = document.getElementById('closeChat');
    const chatMessages = document.getElementById('chatMessages');
    const chatQuestions = document.getElementById('chatQuestions');

    const questions = [
        { text: "봉사는 어떻게 신청하나요?", answer: "봉사신청 페이지에서 신청서를 작성할 수 있습니다." },
        { text: "기부는 어떻게 하나요?", answer: "기부 페이지에서 다양한 기부 방법을 확인하실 수 있습니다." },
        { text: "입양 절차가 궁금해요", answer: "입양 페이지에서 입양 절차와 필요한 서류를 확인하실 수 있습니다." }
    ];

    // 채팅을 열고 닫는 기능
    function toggleChatWindow() {
        chatWindow.style.display = chatWindow.style.display === 'none' ? 'flex' : 'none';
        if (chatWindow.style.display === 'flex') {
            showQuestions();
        }
    }

    // 미리 정의된 질문들을 생성해서 표시함
    function showQuestions() {
        chatQuestions.innerHTML = '';
        questions.forEach(q => {
            const btn = document.createElement('button');
            btn.className = 'question-btn';
            btn.textContent = q.text;
            btn.onclick = () => showAnswer(q);
            chatQuestions.appendChild(btn);
        });
    }

    // 선택된 질문과 그 답변을 표시
    function showAnswer(question) {
        addMessage(question.text, 'user-message');
        addMessage(question.answer, 'bot-message');
    }

    // 채팅창에 새로운 메세지 추가
    function addMessage(text, className) {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${className}`;
        messageDiv.textContent = text;
        chatMessages.appendChild(messageDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }

    chatIcon.addEventListener('click', toggleChatWindow);
    closeChat.addEventListener('click', toggleChatWindow);

    // 초기 상태 설정
    chatWindow.style.display = 'none';
});



// 첫 번째 차트: 연도별 총계
const yearlyTotalCtx = document.getElementById('yearlyTotalChart').getContext('2d');
new Chart(yearlyTotalCtx, {
    type: 'bar',
    data: {
        labels: ['2018', '2019', '2020', '2021', '2022', '2023'],
        datasets: [{
            label: '유기동물 수',
            data: [8220, 7515, 6378, 5605, 4870, 5176],
            backgroundColor: 'rgba(54, 162, 235, 0.7)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            title: {
                display: true,
                text: '연도별 유기동물 발생 현황'
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

// 두 번째 차트: 연도별 세부 현황
const yearlyDetailCtx = document.getElementById('yearlyDetailChart').getContext('2d');
new Chart(yearlyDetailCtx, {
    type: 'bar',
    data: {
        labels: ['2018', '2019', '2020', '2021', '2022', '2023'],
        datasets: [{
            label: '주인 인도',
            data: [2017, 1794, 1494, 1260, 1110, 1134],
            backgroundColor: 'rgba(75, 192, 192, 0.7)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        },
            {
                label: '입양/분양',
                data: [1572, 1222, 1017, 1110, 814, 702],
                backgroundColor: 'rgba(255, 159, 64, 0.7)',
                borderColor: 'rgba(255, 159, 64, 1)',
                borderWidth: 1
            },
            {
                label: '폐사/안락사',
                data: [1703, 1300, 777, 341, 536, 565],
                backgroundColor: 'rgba(255, 99, 132, 0.7)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
    },
    options: {
        responsive: true,
        plugins: {
            title: {
                display: true,
                text: '연도별 처리 현황'
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});