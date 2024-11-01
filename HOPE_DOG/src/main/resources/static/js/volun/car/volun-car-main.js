console.log("제이에스 들어왔는지 확인");
function searchCars() {
    console.log("제이에스 들어왔을까");
    const searchType = document.getElementById("search-type").value;
    const keyword = document.getElementById("keyword").value;

    console.log("js searchType: ", searchType, "keyword: ", keyword);

    fetch(`/car/main/search?searchType=${encodeURIComponent(searchType)}&keyword=${encodeURIComponent(keyword)}`)
        .then(response => {
            if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
            return response.json();
        })
        .then(data => {
            console.log("검색 결과:", data);
            // 검색 결과를 업데이트하는 코드 추가
            displayResults(data); // 새로운 함수 호출
        })
        .catch(error => console.error("Error fetching search results:", error));
}

// 검색 결과를 화면에 표시하는 함수
function displayResults(data) {
    const resultsDiv = document.getElementById("search-results");
    resultsDiv.innerHTML = ""; // 이전 결과 초기화

    if (data.length === 0) {
        resultsDiv.innerHTML = "<p>검색 결과가 없습니다.</p>";
        return;
    }

    data.forEach(car => {
        const carElement = document.createElement("div");
        carElement.innerHTML = `
            <h3>${car.carTitle}</h3>
            <p>카테고리: ${car.carCate}</p>
            <p>내용: ${car.carContent}</p>
            <p>등록일: ${new Date(car.carRegiDate).toLocaleString()}</p>
        `;
        resultsDiv.appendChild(carElement);
    });
}
