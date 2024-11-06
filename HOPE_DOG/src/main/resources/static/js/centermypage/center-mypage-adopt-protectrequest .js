function checkNameInput() {
  const inputField = document.getElementById('nameInput');
  const nameError = document.getElementById('nameError');
  const nameError2 = document.getElementById('nameError2');

  if (inputField.value.trim() === '') {
      nameError.style.display = 'block'; // 메시지 표시
      nameError2.style.display = 'block'; // 메시지 표시
  } else {
      nameError.style.display = 'none'; // 메시지 숨김
      nameError2.style.display = 'none'; // 메시지 표시
  }
}

function checkPhoneInput() {
  const inputField = document.getElementById('phoneInput');
  const phoneError = document.getElementById('phoneError');
  const phoneError2 = document.getElementById('phoneError2');

  if (inputField.value.trim() === '') {
      phoneError.style.display = 'block'; // 메시지 표시
      phoneError2.style.display = 'block'; // 메시지 표시
  } else {
      phoneError.style.display = 'none'; // 메시지 숨김
      phoneError2.style.display = 'none'; // 메시지 표시
  }
}

function checkPhone2Input() {
  const inputField = document.getElementById('phone2Input');
  const phone2Error = document.getElementById('phone2Error');
  const phone2Error2 = document.getElementById('phone2Error2');

  if (inputField.value.trim() === '') {
      phone2Error.style.display = 'block'; // 메시지 표시
      phone2Error2.style.display = 'block'; // 메시지 표시
  } else {
      phone2Error.style.display = 'none'; // 메시지 숨김
      phone2Error2.style.display = 'none'; // 메시지 표시
  }
}

function checkEmailInput() {
  const inputField = document.getElementById('emailInput');
  const emailError = document.getElementById('emailError');
  const emailError2 = document.getElementById('emailError2');

  if (inputField.value.trim() === '') {
    emailError.style.display = 'block'; // 메시지 표시
    emailError2.style.display = 'block'; // 메시지 표시
  } else {
    emailError.style.display = 'none'; // 메시지 숨김
    emailError2.style.display = 'none'; // 메시지 표시
  }
}

function checkAgeInput() {
  const inputField = document.getElementById('ageInput');
  const ageError = document.getElementById('ageError');
  const ageError2 = document.getElementById('ageError2');

  if (inputField.value.trim() === '') {
    ageError.style.display = 'block'; // 메시지 표시
    ageError2.style.display = 'block'; // 메시지 표시
  } else {
    ageError.style.display = 'none'; // 메시지 숨김
    ageError2.style.display = 'none'; // 메시지 표시
  }
}

function checkAddressInput() {
  const inputField = document.getElementById('detailAddress');
  const addressError = document.getElementById('addressError');
  const addressError2 = document.getElementById('addressError2');

  if (inputField.value.trim() === '') {
    addressError.style.display = 'block'; // 메시지 표시
    addressError2.style.display = 'block'; // 메시지 표시
  } else {
    addressError.style.display = 'none'; // 메시지 숨김
    addressError2.style.display = 'none'; // 메시지 표시
  }
}

function checkJobInput() {
  const inputField = document.getElementById('jobInput');
  const jobError = document.getElementById('jobError');
  const jobError2 = document.getElementById('jobError2');

  if (inputField.value.trim() === '') {
    jobError.style.display = 'block'; // 메시지 표시
    jobError2.style.display = 'block'; // 메시지 표시
  } else {
    jobError.style.display = 'none'; // 메시지 숨김
    jobError2.style.display = 'none'; // 메시지 표시
  }
}



// 질문사항에러
function checkQuest1Input() {
  const inputField = document.getElementById('quest1Input');
  const quest1Error = document.getElementById('quest1Error');

  if (inputField.value.trim() === '') {
    quest1Error.style.display = 'block'; // 메시지 표시
  } else {
    quest1Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest2Input() {
  const inputField = document.getElementById('quest2Input');
  const quest1Error = document.getElementById('quest2Error');

  if (inputField.value.trim() === '') {
    quest2Error.style.display = 'block'; // 메시지 표시
  } else {
    quest2Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest3Input() {
  const inputField = document.getElementById('quest3Input');
  const quest3Error = document.getElementById('quest3Error');

  if (inputField.value.trim() === '') {
    quest3Error.style.display = 'block'; // 메시지 표시
  } else {
    quest3Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest4Input() {
  const inputField = document.getElementById('quest4Input');
  const quest4Error = document.getElementById('quest4Error');

  if (inputField.value.trim() === '') {
    quest4Error.style.display = 'block'; // 메시지 표시
  } else {
    quest4Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest5Input() {
  const inputField = document.getElementById('quest5Input');
  const quest5Error = document.getElementById('quest5Error');

  if (inputField.value.trim() === '') {
    quest5Error.style.display = 'block'; // 메시지 표시
  } else {
    quest5Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest6Input() {
  const inputField = document.getElementById('quest6Input');
  const quest6Error = document.getElementById('quest6Error');

  if (inputField.value.trim() === '') {
    quest6Error.style.display = 'block'; // 메시지 표시
  } else {
    quest6Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest7Input() {
  const inputField = document.getElementById('quest7Input');
  const quest7Error = document.getElementById('quest7Error');

  if (inputField.value.trim() === '') {
    quest7Error.style.display = 'block'; // 메시지 표시
  } else {
    quest7Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest8Input() {
  const inputField = document.getElementById('quest8Input');
  const quest8Error = document.getElementById('quest8Error');

  if (inputField.value.trim() === '') {
    quest8Error.style.display = 'block'; // 메시지 표시
  } else {
    quest8Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest9Input() {
  const inputField = document.getElementById('quest9Input');
  const quest9Error = document.getElementById('quest9Error');

  if (inputField.value.trim() === '') {
    quest9Error.style.display = 'block'; // 메시지 표시
  } else {
    quest9Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest10Input() {
  const inputField = document.getElementById('quest10Input');
  const quest10Error = document.getElementById('quest10Error');

  if (inputField.value.trim() === '') {
    quest10Error.style.display = 'block'; // 메시지 표시
  } else {
    quest10Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest11Input() {
  const inputField = document.getElementById('quest11Input');
  const quest11Error = document.getElementById('quest11Error');

  if (inputField.value.trim() === '') {
    quest11Error.style.display = 'block'; // 메시지 표시
  } else {
    quest11Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest12Input() {
  const inputField = document.getElementById('quest12Input');
  const quest12Error = document.getElementById('quest12Error');

  if (inputField.value.trim() === '') {
    quest12Error.style.display = 'block'; // 메시지 표시
  } else {
    quest12Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest13Input() {
  const inputField = document.getElementById('quest13Input');
  const quest13Error = document.getElementById('quest13Error');

  if (inputField.value.trim() === '') {
    quest13Error.style.display = 'block'; // 메시지 표시
  } else {
    quest13Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest14Input() {
  const inputField = document.getElementById('quest14Input');
  const quest14Error = document.getElementById('quest14Error');

  if (inputField.value.trim() === '') {
    quest14Error.style.display = 'block'; // 메시지 표시
  } else {
    quest14Error.style.display = 'none'; // 메시지 숨김
  }
}

function checkQuest15Input() {
  const inputField = document.getElementById('quest15Input');
  const quest15Error = document.getElementById('quest15Error');

  if (inputField.value.trim() === '') {
    quest15Error.style.display = 'block'; // 메시지 표시
  } else {
    quest15Error.style.display = 'none'; // 메시지 숨김
  }
}

// 전체검사
// function validateInputs() {
//   const inputs = [
//       { id: 'nameInput', errors: ['nameError', 'nameError2'] },
//       { id: 'phoneInput', errors: ['phoneError', 'phoneError2'] },
//       { id: 'phone2Input', errors: ['phone2Error', 'phone2Error2'] },
//       { id: 'emailInput', errors: ['emailError', 'emailError2'] },
//       { id: 'ageInput', errors: ['ageError', 'ageError2'] },
//       { id: 'jobInput', errors: ['jobError', 'jobError2'] },
//       { id: 'detailAddress', errors: ['addressError', 'addressError2'] },
//       { id: 'quest1Input', errors: ['quest1Error'] },
//       { id: 'quest2Input', errors: ['quest2Error'] },
//       { id: 'quest3Input', errors: ['quest3Error'] },
//       { id: 'quest4Input', errors: ['quest4Error'] },
//       { id: 'quest5Input', errors: ['quest5Error'] },
//       { id: 'quest6Input', errors: ['quest6Error'] },
//       { id: 'quest7Input', errors: ['quest7Error'] },
//       { id: 'quest8Input', errors: ['quest8Error'] },
//       { id: 'quest9Input', errors: ['quest9Error'] },
//       { id: 'quest10Input', errors: ['quest10Error'] },
//       { id: 'quest11Input', errors: ['quest11Error'] },
//       { id: 'quest12Input', errors: ['quest12Error'] },
//       { id: 'quest13Input', errors: ['quest13Error'] },
//       { id: 'quest14Input', errors: ['quest14Error'] },
//       { id: 'quest15Input', errors: ['quest15Error'] },
//   ];
//
//   let allValid = true;
//
//   inputs.forEach(input => {
//       const inputField = document.getElementById(input.id);
//       const isEmpty = inputField.value.trim() === '';
//
//       input.errors.forEach(errorId => {
//           const errorElement = document.getElementById(errorId);
//           if (isEmpty) {
//               errorElement.style.display = 'block'; // 메시지 표시
//               allValid = false;
//           } else {
//               errorElement.style.display = 'none'; // 메시지 숨김
//           }
//       });
//   });
//
//   if (!allValid) {
//       alert('모든 질문을 입력해 주세요.'); // 경고창 표시
//   } else {
//       alert('신청이 완료되었습니다'); // 모든 입력이 유효한 경우
//       // 여기에 추가적인 처리 코드 작성 가능
//   }
// }

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