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
    const pageSize = 10; // 한 페이지에 보여줄 항목 수

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

// 검색 폼 이벤트 리스너 등록
    const searchForm = document.querySelector('form[name="search"]');
    if (searchForm) {
        searchForm.addEventListener('submit', function(e) {
            e.preventDefault(); // 기본 제출 방지
            searchCars(); // 검색 함수 호출
        });
    }
});

// 검색 함수
function searchCars() {
    const searchType = document.getElementById("search-type").value;
    const keyword = document.getElementById("keyword").value;
    const resultsDiv = document.getElementById("search-results");

    if (!resultsDiv) return;

    console.log("검색 타입:", searchType, "키워드:", keyword);

    $('#pagination').pagination('destroy'); // 페이지네이션 초기화

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

// 페이지네이션 초기화 함수
function initializePagination(data) {
    const container = $('#pagination');
    const pageSize = 10; // 한 페이지에 보여줄 항목 수

    container.pagination({
        dataSource: data,
        pageSize: pageSize,
        callback: function(data, pagination) {
            displayResults(data); // 페이지별 결과 표시
        }
    });

    // 첫 번째 페이지로 이동
    container.pagination('goToPage', 1);
}
