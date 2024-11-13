// 눈 깜빡임 효과
function blinkEyes() {
    const eyes = document.querySelectorAll('.eye-left, .eye-right');
    eyes.forEach(eye => {
        eye.style.height = '2px';
        setTimeout(() => {
            eye.style.height = '25px';
        }, 200);
    });
}

// 3초마다 눈 깜빡임
setInterval(blinkEyes, 3000);