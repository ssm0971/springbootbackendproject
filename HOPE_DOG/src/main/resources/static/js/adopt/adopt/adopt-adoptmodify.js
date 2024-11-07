function checkNameInput() {
  const inputField = document.getElementById('nameInput');
  const nameError = document.getElementById('nameError');
  const nameError2 = document.getElementById('nameError2');

  if (inputField.value.trim() === '') {
    nameError.style.display = 'block'; // 메시지 표시
    nameError2.style.display = 'block'; // 메시지 표시
  } else {
    nameError.style.display = 'none'; // 메시지 숨김
    nameError2.style.display = 'none'; // 메시지 숨김
  }
}

function checkBreedInput() {
  const inputField = document.getElementById('breedInput');
  const breadError = document.getElementById('breadError');
  const breadError2 = document.getElementById('breadError2');

  if (inputField.value.trim() === '') {
    breadError.style.display = 'block'; // 메시지 표시
    breadError2.style.display = 'block'; // 메시지 표시
  } else {
    breadError.style.display = 'none'; // 메시지 숨김
    breadError2.style.display = 'none'; // 메시지 숨김
  }
}

function checkWeightInput() {
  const inputField = document.getElementById('weightInput');
  const weightError = document.getElementById('weightError');
  const weightError2 = document.getElementById('weightError2');

  if (inputField.value.trim() === '') {
    weightError.style.display = 'block'; // 메시지 표시
    weightError2.style.display = 'block'; // 메시지 표시
  } else {
    weightError.style.display = 'none'; // 메시지 숨김
    weightError2.style.display = 'none'; // 메시지 숨김
  }
}

function checkIntroduceInput() {
  const inputField = document.getElementById('introduceInput');
  const introduceError = document.getElementById('introduceError');
  const introduceError2 = document.getElementById('introduceError2');

  if (inputField.value.trim() === '') {
    introduceError.style.display = 'block'; // 메시지 표시
    introduceError2.style.display = 'block'; // 메시지 표시
  } else {
    introduceError.style.display = 'none'; // 메시지 숨김
    introduceError2.style.display = 'none'; // 메시지 숨김
  }
}

// 글작성취소버튼
function cancleClick() {
  if (confirm('작성중인 글은 저장되지 않습니다. 정말로 취소하시겠습니까?')) {
    console.log('취소되었습니다.');
    location.href = '/adopt/adopt';
  } else {
    console.log('취소하지 않았습니다.');
  }
}

// 썸머노트 크기 및 튜닝
$(function () {
  const maxLength = 1000;

  $("#contents").summernote({
    width: 780,                   // 가로값 설정
    minHeight: 500,               // 높이값 설정
    maxHeight: 500,
    placeholder: "내용을 입력해주세요", // 안내 문구 설정
    disableDrageAndDrop: true,    // 드래그 비활성화
    focus: true,                  // 초기화 후 커서가 편집 가능한 영역에 포커스를 맞춤
    lang: 'ko-KR',                // 한글 설정
    toolbar: [
      ['insert', ['picture']],
      ['fontSize', ['fontsize']],
      ['fontName', ['fontname']],
      ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
      ['color', ['forecolor', 'backcolor']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['table', ['table']],
      ['insert', ['link', 'video']],
      ['view', ['fullscreen', 'codeview', 'help']]
    ],
    callbacks: {
      onKeyup: function(e) {
        // 현재 입력된 HTML 내용 가져오기
        const contents = $('#contents').summernote('code');

        // HTML 태그를 제외하고 텍스트만 추출하여 길이 확인
        const textOnly = $('<div>').html(contents).text();

        // 글자 수가 제한을 초과했을 때
        if (textOnly.length > maxLength) {
          alert(`최대 ${maxLength}자까지 입력할 수 있습니다.`);

          // 텍스트 길이를 제한하여 자르고 HTML 구조 복원
          const trimmedText = textOnly.substring(0, maxLength);

          // 자른 텍스트를 다시 HTML 구조에 맞춰 설정
          const trimmedHTMLContent = $('<div>').text(trimmedText).html();
          $('#contents').summernote('code', trimmedHTMLContent);
        }
      },

      // 이미지 업로드
      onImageUpload: function(files, editor, welEditable) {
        // 다중 이미지 처리를 위해 for문을 사용했습니다.
        for (var i = 0; i < files.length; i++) {
          imageUploader(files[i], this);
        }
      }
    }
  });
});

// 이미지 업로드 AJAX로 보내기
function imageUploader(file, el) {
  var formData = new FormData();
  formData.append('file', file);

  $.ajax({
    data: formData,
    type: "POST",
    // 서버에서 이미지를 업로드 처리하는 경로
    url: '/post/image-upload',
    contentType: false,
    processData: false,
    enctype: 'multipart/form-data',
    success: function(data) {
      // 서버에서 반환된 이미지 파일 이름을 사용하여 URL 생성
      const imageUrl = "/file/adopt/adopt/" + data;

      // Summernote 에디터에 이미지 삽입
      $(el).summernote('insertImage', imageUrl, function($image) {
        $image.css('width', "100%"); // 이미지 크기 조정
      });

      console.log(data); // 업로드된 이미지 경로 확인
    },
    error: function(xhr, status, error) {
      alert("이미지 업로드 실패");
    }
  });
}



{
  let adoptPageBtn = document.getElementById('adoptPage');
  adoptPageBtn.addEventListener('click', function () {
    location.href = '/adopt/adopt';
  });
}

{
  let protectPageBtn = document.getElementById('protectPage');
  protectPageBtn.addEventListener('click', function () {
    location.href = '/adopt/protect';
  });
}

{
  let reviewPageBtn = document.getElementById('reviewPage');
  reviewPageBtn.addEventListener('click', function () {
    location.href = '/adopt/review';
  });
}


//날짜변환
function submitForm() {
  const startDate = document.querySelector('input[name="adoptPeriodstart"]').value;
  const endDate = document.querySelector('input[name="adoptPeriodend"]').value;

  // 문자열을 Date 객체로 변환
  const startDateObj = new Date(startDate);
  const endDateObj = new Date(endDate);

  // 필요한 경우 Date 객체를 처리하거나 서버에 전송
  console.log(startDateObj, endDateObj);

  // 폼 제출 (필요한 경우)
  document.getElementById('adoptForm').submit();
}

