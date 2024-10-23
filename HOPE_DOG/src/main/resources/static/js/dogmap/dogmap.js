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