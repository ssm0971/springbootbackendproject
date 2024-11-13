document.querySelectorAll('.doghouse-list-title').forEach(title => {
    title.addEventListener('click', function() {
        const info = this.closest('.doghouse-item').querySelector('.doghouse-list-info');
        const mapContainer = info.querySelector('.doghouse-map > div');

        if (!mapContainer) {
            console.error('Map container not found for this shelter.');
            return;
        }

        const lat = parseFloat(mapContainer.dataset.lat);
        const lng = parseFloat(mapContainer.dataset.lng);

        if (!mapContainer.dataset.mapLoaded) {
            kakao.maps.load(function() {
                const mapOption = {
                    center: new kakao.maps.LatLng(lat, lng),
                    level: 3
                };

                const map = new kakao.maps.Map(mapContainer, mapOption);

                // HTML 요소에서 데이터 속성으로부터 이미지 경로 가져오기
                const markerImageSrc = info.querySelector('img[data-marker-src]').getAttribute('data-marker-src');
                // const markerImageSrc = info.querySelector('img').getAttribute('data-marker-src');
                const markerImageSize = new kakao.maps.Size(69, 69);
                const markerImageOption = {offset: new kakao.maps.Point(27, 69)};

                // 마커 이미지 객체 생성
                const markerImage = new kakao.maps.MarkerImage(markerImageSrc, markerImageSize, markerImageOption);

                const markerPosition = new kakao.maps.LatLng(lat, lng);
                const marker = new kakao.maps.Marker({
                    position: markerPosition,
                    image: markerImage
                });

                marker.setMap(map);
                mapContainer.dataset.mapLoaded = true;
            });
        }

        document.querySelectorAll('.doghouse-list-info').forEach(item => {
            if (item !== info) {
                item.classList.remove('active');
            }
        });

        info.classList.toggle('active');
    });
});
