document.addEventListener('DOMContentLoaded', function() {
    class ChatBot {
        constructor() {
            this.chatIcon = document.getElementById('chatIcon');
            this.chatWindow = document.getElementById('chatWindow');
            this.closeChat = document.getElementById('closeChat');
            this.chatMessages = document.getElementById('chatMessages');
            this.chatQuestions = document.getElementById('chatQuestions');
            this.chatInput = document.getElementById('chatInput');
            this.sendButton = document.getElementById('sendMessage');

            this.questions = [
                { text: "봉사는 어떻게 신청하나요?", answer: "봉사신청 페이지에서 신청서를 작성할 수 있습니다." },
                { text: "기부는 어떻게 하나요?", answer: "기부 페이지에서 다양한 기부 방법을 확인하실 수 있습니다." },
                { text: "입양 절차가 궁금해요", answer: "입양 페이지에서 입양 절차와 필요한 서류를 확인하실 수 있습니다." }
            ];

            this.initialize();
        }

        initialize() {
            this.chatIcon.addEventListener('click', () => this.toggleChatWindow());
            this.closeChat.addEventListener('click', () => this.toggleChatWindow());
            this.sendButton.addEventListener('click', () => this.handleUserInput());
            this.chatInput.addEventListener('keypress', (e) => {
                if (e.key === 'Enter') this.handleUserInput();
            });

            // 채팅창 외부 클릭시 닫기
            document.addEventListener('click', (e) => {
                if (!this.chatWindow.contains(e.target) && e.target !== this.chatIcon) {
                    this.chatWindow.classList.remove('active');
                }
            });

            // 초기 상태 설정
            this.chatWindow.classList.remove('active');
        }

        toggleChatWindow() {
            this.chatWindow.classList.toggle('active');
            if (this.chatWindow.classList.contains('active')) {
                this.showQuestions();
                this.chatInput.focus();
            }
        }

        showQuestions() {
            this.chatQuestions.innerHTML = '';
            this.questions.forEach(q => {
                const btn = document.createElement('button');
                btn.className = 'question-btn';
                btn.textContent = q.text;
                btn.onclick = () => this.showAnswer(q);
                this.chatQuestions.appendChild(btn);
            });
        }

        showAnswer(question) {
            this.addMessage(question.text, 'user-message');
            this.addMessage(question.answer, 'bot-message');
        }

        handleUserInput() {
            const message = this.chatInput.value.trim();
            if (message) {
                this.addMessage(message, 'user-message');
                this.chatInput.value = '';

                // 간단한 봇 응답 로직
                const botResponse = this.getBotResponse(message);
                setTimeout(() => {
                    this.addMessage(botResponse, 'bot-message');
                }, 500);
            }
        }

        getBotResponse(message) {
            // 키워드 기반의 간단한 응답 로직
            const keywords = {
                '봉사': '봉사신청 페이지를 확인해 주세요.',
                '기부': '기부 방법을 안내해 드리겠습니다.',
                '입양': '입양 절차를 설명해 드리겠습니다.'
            };

            for (const [key, value] of Object.entries(keywords)) {
                if (message.includes(key)) return value;
            }

            return '자세한 내용은 담당자에게 문의해 주세요.';
        }

        addMessage(text, className) {
            const messageDiv = document.createElement('div');
            messageDiv.className = `message ${className}`;
            messageDiv.textContent = text;
            this.chatMessages.appendChild(messageDiv);
            this.chatMessages.scrollTop = this.chatMessages.scrollHeight;
        }
    }

    // 챗봇 인스턴스 생성
    const chatBot = new ChatBot();
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
        maintainAspectRatio: true,
        plugins: {
            title: {
                display: false
            },
            legend: {
                position: 'top'
            }
        },
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: '동물 수'
                }
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
        maintainAspectRatio: true,
        plugins: {
            title: {
                display: false
            },
            legend: {
                position: 'top'
            }
        },
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: '동물 수'
                }
            }
        }
    }
});