function checkNicknameInput() {
  // 입력 필드를 가져옵니다.
  const inputField = document.getElementById('nicknameInput');
  // 오류 메시지 요소들을 가져옵니다.
  const nameError = document.getElementById('nicknameError');

  // 입력값이 비어있거나 공백일 경우
  if (inputField.value.trim() === '') {
      // 첫 번째 오류 메시지를 표시합니다.
      nameError.style.display = 'block'; // 메시지 표시
      // 두 번째 오류 메시지도 표시합니다.
  } else {
      // 입력값이 유효할 경우 첫 번째 오류 메시지를 숨깁니다.
      nameError.style.display = 'none'; // 메시지 숨김
      // 두 번째 오류 메시지도 숨깁니다.-
  }
}


function checkEmailInput() {
  const inputField = document.getElementById('emailInput');
  const emailError = document.getElementById('emailError');

  if (inputField.value.trim() === '') {
    emailError.style.display = 'block'; // 메시지 표시
  } else {
    emailError.style.display = 'none'; // 메시지 숨김
  }
}

function checkPhoneInput() {
  const inputField = document.getElementById('phoneInput');
  const phoneError = document.getElementById('phoneError');

  if (inputField.value.trim() === '') {
      phoneError.style.display = 'block'; // 메시지 표시
  } else {
      phoneError.style.display = 'none'; // 메시지 숨김
  }
}

function checkPnumberInput() {
  const inputField = document.getElementById('phonecheckInput');
  const phone2Error = document.getElementById('phonecheckError');

  if (inputField.value.trim() === '') {
      phone2Error.style.display = 'block'; // 메시지 표시
  } else {
      phone2Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkAddressInput() {
  const inputField = document.getElementById('addressInput');
  const ageError = document.getElementById('addressError');

  if (inputField.value.trim() === '') {
    ageError.style.display = 'block'; // 메시지 표시
  } else {
    ageError.style.display = 'none'; // 메시지 숨김
  }
}

function checkAddInput() {
  const inputField = document.getElementById('addInput');
  const addressError = document.getElementById('addError');

  if (inputField.value.trim() === '') {
    addressError.style.display = 'block'; // 메시지 표시
  } else {
    addressError.style.display = 'none'; // 메시지 숨김
  }
}

function checkPasswordInput() {
  const inputField = document.getElementById('passwordInput');
  const jobError = document.getElementById('passwordError');

  if (inputField.value.trim() === '') {
    jobError.style.display = 'block'; // 메시지 표시
  } else {
    jobError.style.display = 'none'; // 메시지 숨김
  }
}


function checkPcheckInput() {
  const inputField = document.getElementById('passwordcheckInput');
  const jobError = document.getElementById('passwordcheckError');

  if (inputField.value.trim() === '') {
    jobError.style.display = 'block'; // 메시지 표시
  } else {
    jobError.style.display = 'none'; // 메시지 숨김
  }
}

// 전체검사
function validateInputs() {
  const inputs = [
      { id: 'nicknameInput', errors: ['nicknameError'] },
      { id: 'phoneInput', errors: ['phoneError'] },
      { id: 'phonecheckInput', errors: ['phonecheckError'] },
      { id: 'emailInput', errors: ['emailError'] },
      { id: 'addressInput', errors: ['addressError'] },
      { id: 'addInput', errors: ['addError'] },
      { id: 'passwordInput', errors: ['passwordError'] },
      { id: 'passwordcheckInput', errors: ['passwordcheckError'] }
  ];

  let allValid = true;

  inputs.forEach(input => {
      const inputField = document.getElementById(input.id);
      const isEmpty = inputField.value.trim() === '';
      
      input.errors.forEach(errorId => {
          const errorElement = document.getElementById(errorId);
          if (isEmpty) {
              errorElement.style.display = 'block'; // 메시지 표시
              allValid = false;
          } else {
              errorElement.style.display = 'none'; // 메시지 숨김
          }
      });
  });

  if (!allValid) {
      alert('모든 질문을 입력해 주세요.'); // 경고창 표시
  } else {
      alert('신청이 완료되었습니다'); // 모든 입력이 유효한 경우
      // 여기에 추가적인 처리 코드 작성 가능
  }
}


  // 주소 검색
  document.addEventListener('DOMContentLoaded', function() {
    const addressSearchBtn = document.getElementById('addressSearch');

    addressSearchBtn.addEventListener('click', function() {
      new daum.Postcode({
        oncomplete: function(data) {
          document.getElementById('zipcode').value = data.zonecode; // 우편번호 입력
          document.getElementById('address').value = data.address; // 주소 입력
          document.getElementById('detailAddress').focus(); // 상세주소 입력란에 포커스
        }
      }).open();
    });
  });