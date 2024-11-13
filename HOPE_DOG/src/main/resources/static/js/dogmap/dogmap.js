document.querySelectorAll('.doghouse-list-title').forEach(title => {
    title.addEventListener('click', function() {
        // 클릭한 항목에 해당하는 doghouse-item의 doghouse-list-info 가져오기
        const info = this.closest('.doghouse-item').querySelector('.doghouse-list-info');

        // 지도 컨테이너 찾기
        const mapContainer = info.querySelector('.doghouse-map > div');

        // mapContainer가 제대로 선택되었는지 확인
        if (!mapContainer) {
            console.error('Map container not found for this shelter.');
            return;
        }

        // lat, lng 값을 data-* 속성에서 가져오기
        const lat = parseFloat(mapContainer.dataset.lat);
        const lng = parseFloat(mapContainer.dataset.lng);

        // 지도가 아직 로드되지 않았을 경우 지도 로딩
        if (!mapContainer.dataset.mapLoaded) {
            kakao.maps.load(function() {
                // 지도 옵션 설정
                const mapOption = {
                    center: new kakao.maps.LatLng(lat, lng), // 지도 중심 좌표
                    level: 3 // 확대 레벨
                };

                // 지도 생성
                const map = new kakao.maps.Map(mapContainer, mapOption);

                // 마커 생성
                const markerPosition = new kakao.maps.LatLng(lat, lng);
                const marker = new kakao.maps.Marker({
                    position: markerPosition
                });

                // 마커 지도에 표시
                marker.setMap(map);

                // 지도 로드되었음을 표시하기 위해 데이터 속성에 mapLoaded 추가
                mapContainer.dataset.mapLoaded = true;
            });
        }

        // 리스트 정보 토글 (active 클래스 추가/제거)
        document.querySelectorAll('.doghouse-list-info').forEach(item => {
            if (item !== info) {
                item.classList.remove('active');
            }
        });

        info.classList.toggle('active');
    });
});

// document.querySelectorAll('.doghouse-list-title').forEach(title => {
//     title.addEventListener('click', function() {
//         const info = this.closest('.doghouse-item').querySelector('.doghouse-list-info'); // 클릭한 항목에 해당하는 정보
//         const mapContainer = info.querySelector('.doghouse-map > div'); // 지도 영역
//         // const lat = parseFloat(info.querySelector('span[th\\:text="${shelter.lat}"]').textContent); // lat (위도)
//         // const lng = parseFloat(info.querySelector('span[th\\:text="${shelter.lng}"]').textContent); // lng (경도)
//         // data-* 속성에서 lat과 lng 값을 읽어오기
//         const lat = parseFloat(mapContainer.dataset.lat); // data-lat 값
//         const lng = parseFloat(mapContainer.dataset.lng); // data-lng 값
//
//         // 모든 리스트 정보 닫기
//         document.querySelectorAll('.doghouse-list-info').forEach(item => {
//             if (item !== info) {
//                 item.classList.remove('active'); // active 클래스 제거하여 다른 정보 숨김
//             }
//         });
//
//         // 클릭한 리스트 정보 토글하여 숨김
//         info.classList.toggle('active');
//
//         // 지도가 아직 로드되지 않은 경우에만 지도를 로드합니다.
//         if (mapContainer && !mapContainer.dataset.mapLoaded) {
//             // 카카오 지도 API 로드
//             kakao.maps.load(function() {
//                 // 지도 옵션 설정
//                 const mapOption = {
//                     center: new kakao.maps.LatLng(lat, lng), // 지도의 중심 좌표
//                     level: 3 // 확대 레벨
//                 };
//
//                 // 지도 생성
//                 const map = new kakao.maps.Map(mapContainer, mapOption);
//
//                 // 마커 생성
//                 const markerPosition = new kakao.maps.LatLng(lat, lng);
//                 const marker = new kakao.maps.Marker({
//                     position: markerPosition
//                 });
//
//                 marker.setMap(map); // 마커를 지도에 표시
//
//                 // 지도가 로드되었음을 표시하기 위해 data-mapLoaded 속성 설정
//                 mapContainer.dataset.mapLoaded = true;
//             });
//         }
//     });
// });

console.log('Clicked shelter:', title);
console.log('Map container:', mapContainer);
console.log('Lat:', lat, 'Lng:', lng);


// //doghouse-list-title 선택하고 각각에 대해 반복
// document.querySelectorAll('.doghouse-list-title').forEach(title => {
//     title.addEventListener('click', function() { //보호소 이름 클릭했을 때 이벤트리스너 추가
//       const info = this.closest('.doghouse-item').querySelector('.doghouse-list-info'); //클릭했을 떄 doghouse-item에 감싸져있는 doghouse-list-info선택
//       //주소,전화번호 ,지도 보여주기
//
//       // 모든 리스트 정보 닫기
//       document.querySelectorAll('.doghouse-list-info').forEach(item => { //querySelectorAll로 doghouse-list-info 선택(주소,전화번호,지도)
//         if (item !== info) { //현재 반복 중인 요소가 클릭한 정보가 아닌 경우
//           item.classList.remove('active'); //active 클래스 제거하고 정보 숨김
//         }
//       });
//
//       // 클릭한 리스트 정보 토글하여 숨김
//       info.classList.toggle('active');
//     });
//   });
//
// // test용 더비데이터
// // const locations = [
// //     { index: 1, careNm: "보호소 A", careAddr: "주소 A", careTel: "전화 A", lat: 37.5665, lng: 126.978 },
// //     { index: 2, careNm: "보호소 B", careAddr: "주소 B", careTel: "전화 B", lat: 35.1796, lng: 129.0756 },
// //     { index: 3, careNm: "보호소 C", careAddr: "주소 C", careTel: "전화 C", lat: 36.3504, lng: 127.3845 }
// // ]; // 더미 데이터 추가
// console.log("Shelters before loading:", /*[[${shelters}]]*/ [{index, lat, lng}]);
// const locations = /*[[${shelters}]]*/ [{index, lat, lng}];
// // const locations = JSON.parse(/*[[${shelters}]]*/ '[]');
// console.log("Locations after loading:", locations);
// // const locations = /*[[${shelters}]]*/ []; // Thymeleaf에서 JSON 형태로 가져오기
// // console.log(locations);
// locations.forEach(({index, lat, lng}) => {
//     const mapContainer = document.getElementById(`map${index}`); // 각 지도의 ID
//     const mapOption = {
//         center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
//         // center: new kakao.maps.LatLng('35.922276', '128.59904'), // 지도의 중심좌표
//         level: 3 // 지도의 확대 레벨
//     };
//
//     const map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
//
//     // 마커를 생성합니다
//     const markerPosition = new kakao.maps.LatLng(lat, lng);
//     const marker = new kakao.maps.Marker({
//         position: markerPosition
//     });
//
//     // 마커가 지도 위에 표시되도록 설정합니다
//     marker.setMap(map);
// });
//
//
// // 지도 api 생성 코드
// // 위치 배열: 보호소 이름에 따라 위도와 경도 설정
// // const locations = /*[[${shelters}]]*/ []; // Thymeleaf에서 JSON 형태로 가져오기
// // locations.forEach((shelter) => {
// //     const mapContainer = document.getElementById(`map${shelter.index}`); // 각 지도의 ID
// //     const mapOption = {
// //         center: new kakao.maps.LatLng(shelter.lat, shelter.lng), // 지도의 중심좌표
// //         level: 3 // 지도의 확대 레벨
// //     };
// //
// //     const map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
// //
// //     // 마커를 생성합니다
// //     const markerPosition = new kakao.maps.LatLng(shelter.lat, shelter.lng);
// //     const marker = new kakao.maps.Marker({
// //         position: markerPosition
// //     });
//
//     // 마커가 지도 위에 표시되도록 설정합니다
// //     marker.setMap(map);
// // });
//
//
// // var mapContainer = document.getElementById('map'), // 지도를 표시할 div
// //     mapOption = {
// //         center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
// //         level: 3 // 지도의 확대 레벨
// //     };
// //
// // var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
// //
// // // 마커가 표시될 위치입니다
// // var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667);
// //
// // // 마커를 생성합니다
// // var marker = new kakao.maps.Marker({
// //     position: markerPosition
// // });
// //
// // // 마커가 지도 위에 표시되도록 설정합니다
// // marker.setMap(map);
// //
// // // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// // // marker.setMap(null);