// // 댓글등록 알터창
// function Comment() {
//   const comment = document.getElementById('commentInput').value.trim();
//
//   if (comment === '') {
//     alert('입력한 내용이 없습니다.');
//     return false; // 입력 내용이 없을 경우
//   }
//   return true; // 입력 내용이 있을 경우
// }
//
// //게시글
//
// // 수정, 삭제,신고 confirm창
// function modifyAlert() {
//   const userConfirmed = confirm("수정하시겠습니까?");
//   if (userConfirmed) {
//       window.location.href ='../../html/commu/commu-post-rewrite.html'; // 수정페이지로 이동
//   }
// }
//
// function deleteAlert() {
//   const userConfirmed = confirm("삭제하시겠습니까?");
//   if (userConfirmed) {
//       alert("삭제가 완료되었습니다."); // 확인 눌렀을 때 알림 창 표시
//       window.location.href = "../../html/commu/commu-main.html"; // 커뮤 메인으로 이동
//   }
// }
//
// function endAlert() {
//   const contentReport = prompt('신고사유를 100글자 이내로 입력해주세요');
//
//   if (contentReport !== null) { // 사용자가 확인을 누르면
//     console.log('게시글이 신고되었습니다');
//     alert('게시글이 신고되었습니다'); // 알림창 추가
//
//   } else {
//     console.log('게시글 신고가 취소되었습니다.');
//     alert('게시글 신고가 취소되었습니다.'); // 알림창 추가
//   }

// DOMContentLoaded 이벤트를 사용하여 모든 요소가 로드된 후에 스크립트 실행
document.addEventListener("DOMContentLoaded", function() {
  const items = $('.commu-ul-all ul'); // 게시글 항목 선택

  // 게시글 수가 10개 이하인 경우 페이지네이션 처리
  if (items.length <= 10) {
    items.show(); // 모든 항목 표시
    return; // 페이지네이션 초기화 중지
  }

  // 처음 10개 항목만 보이게 하고 나머지는 숨김
  items.hide().slice(0, 10).show(); // 첫 10개 항목만 표시

  // 페이지네이션 설정
  const container = $('#pagination');
  const pageSize = 10; // 한 페이지에 보여줄 항목 수 (첫 페이지 10개)

  container.pagination({
    dataSource: items.toArray(), // 게시글 항목을 배열로 변환
    pageSize: pageSize,
    callback: function(data, pagination) {
      items.hide(); // 기존에 보여진 항목 숨김
      $.each(data, function(index, item) {
        $(item).show(); // 현재 페이지에 해당하는 항목만 표시
      });
    }
  });

  // 페이지네이션 플러그인이 초기화된 후에 첫 번째 페이지로 이동
  container.pagination('goToPage', 1);
});

// 검색 함수
function searchCars() {
  const searchType = document.getElementById("search-type").value;
  const keyword = document.getElementById("keyword").value;
  const resultsDiv = document.getElementById("search-results");

  if (!resultsDiv) return;

  console.log("검색 타입:", searchType, "키워드:", keyword);

  fetch(`/main/commuSearch?searchType=${encodeURIComponent(searchType)}&keyword=${encodeURIComponent(keyword)}`)
      .then(response => {
        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        return response.json();
      })
      .then(data => {
        console.log("검색 결과:", data);
        if (data.length === 0) {
          resultsDiv.innerHTML = "<p>검색 결과가 없습니다.</p>";
          $('#pagination').pagination('destroy'); // 결과가 없으면 페이지네이션 제거
        } else {
          displayResults(data); // 검색 결과 표시
          initializePagination(data); // 페이지네이션 설정
        }
      })
      .catch(error => console.error("검색 결과 가져오기 오류:", error));
}

// 검색 결과를 화면에 표시하는 함수
function displayResults(data) {
  const resultsDiv = document.getElementById("search-results");

  if (!resultsDiv) return;

  resultsDiv.innerHTML = ""; // 이전 결과 초기화

  data.forEach(commu => {
    const commuElement = document.createElement("div");
    commuElement.innerHTML = `
            <h3>${commu.commuTitle}</h3>
            <p>카테고리: ${commu.commuCate}</p>
            <p>내용: ${commu.commuContent}</p>
            <p>등록일: ${new Date(commu.commuRegiDate).toLocaleString()}</p>
        `;
    resultsDiv.appendChild(commuElement);
  });
}

// 폼 제출 시 검색 수행
document.addEventListener("DOMContentLoaded", function() {
  const searchForm = document.querySelector('form[name="search"]');
  if (searchForm) {
    searchForm.addEventListener('submit', function(e) {
      e.preventDefault(); // 기본 제출 방지
      searchCars(); // 검색 함수 호출
    });
  }
});