//doghouse-list-title 선택하고 각각에 대해 반복
document.querySelectorAll('.doghouse-list-title').forEach(title => {
    title.addEventListener('click', function() { //보호소 이름 클릭했을 때 이벤트리스너 추가 
      const info = this.closest('.doghouse-item').querySelector('.doghouse-list-info'); //클릭했을 떄 doghouse-item에 감싸져있는 doghouse-list-info선택
      //주소,전화번호 ,지도 보여주기
  
      // 모든 리스트 정보 닫기
      document.querySelectorAll('.doghouse-list-info').forEach(item => { //querySelectorAll로 doghouse-list-info 선택(주소,전화번호,지도)
        if (item !== info) { //현재 반복 중인 요소가 클릭한 정보가 아닌 경우
          item.classList.remove('active'); //active 클래스 제거하고 정보 숨김
        }
      });
  
      // 클릭한 리스트 정보 토글하여 숨김
      info.classList.toggle('active');
    });
  });

// test용 더비데이터
const locations = [
    { index: 1, careNm: "보호소 A", careAddr: "주소 A", careTel: "전화 A", lat: 37.5665, lng: 126.978 },
    { index: 2, careNm: "보호소 B", careAddr: "주소 B", careTel: "전화 B", lat: 35.1796, lng: 129.0756 },
    { index: 3, careNm: "보호소 C", careAddr: "주소 C", careTel: "전화 C", lat: 36.3504, lng: 127.3845 }
]; // 더미 데이터 추가

locations.forEach((shelter) => {
    const mapContainer = document.getElementById(`map${shelter.index}`); // 각 지도의 ID
    const mapOption = {
        center: new kakao.maps.LatLng(shelter.lat, shelter.lng), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    const map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커를 생성합니다
    const markerPosition = new kakao.maps.LatLng(shelter.lat, shelter.lng);
    const marker = new kakao.maps.Marker({
        position: markerPosition
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);
});


// 지도 api 생성 코드
// 위치 배열: 보호소 이름에 따라 위도와 경도 설정
// const locations = /*[[${shelters}]]*/ []; // Thymeleaf에서 JSON 형태로 가져오기
// locations.forEach((shelter) => {
//     const mapContainer = document.getElementById(`map${shelter.index}`); // 각 지도의 ID
//     const mapOption = {
//         center: new kakao.maps.LatLng(shelter.lat, shelter.lng), // 지도의 중심좌표
//         level: 3 // 지도의 확대 레벨
//     };
//
//     const map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
//
//     // 마커를 생성합니다
//     const markerPosition = new kakao.maps.LatLng(shelter.lat, shelter.lng);
//     const marker = new kakao.maps.Marker({
//         position: markerPosition
//     });

    // 마커가 지도 위에 표시되도록 설정합니다
//     marker.setMap(map);
// });


// var mapContainer = document.getElementById('map'), // 지도를 표시할 div
//     mapOption = {
//         center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
//         level: 3 // 지도의 확대 레벨
//     };
//
// var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
//
// // 마커가 표시될 위치입니다
// var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667);
//
// // 마커를 생성합니다
// var marker = new kakao.maps.Marker({
//     position: markerPosition
// });
//
// // 마커가 지도 위에 표시되도록 설정합니다
// marker.setMap(map);
//
// // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// // marker.setMap(null);