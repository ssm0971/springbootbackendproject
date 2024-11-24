/**
 * 댓글 작성 모듈
 *
 * @param replyInfo {Object} 댓글 등록에 필요한 정보 (boardId, content 등)
 * @param callback {Function} 댓글 등록이 정상적으로 완료되면 호출할 함수
 */


// register: 댓글을 작성하는 함수
// 댓글 정보를 받아 서버로 POST 요청을 보내고, 성공 시 콜백 함수를 호출
export function register(replyInfo, callback) {
    // 댓글 정보를 콘솔에 출력하여 확인
    console.log(replyInfo);
    console.log(replyInfo.donaCommentWriter + "======")

    // 댓글 등록을 위한 POST 요청
    fetch(`/v1/donas/${replyInfo.donaNo}/comment`, {
        method: "POST",  // HTTP 메서드는 POST
        headers: { 'Content-Type': 'application/json' },  // 요청 본문은 JSON 형식
        body: JSON.stringify(replyInfo),  // 댓글 정보 (replyInfo) 를 JSON으로 변환하여 요청 본문에 포함
    }).then(resp => {
        if (resp.status === 200) {
            // 요청이 성공적으로 처리되면 callback 함수 호출
            callback();
        } else {
            // 응답이 오류인 경우, 상태 코드와 응답 본문을 출력하여 문제를 진단
            console.error("응답 오류:", resp.status);
            resp.text().then(text => console.error("서버 응답 본문:", text));
        }
    }).catch(error => {
        // fetch 요청 중 오류가 발생한 경우, 에러 메시지 출력
        console.error("Fetch error:", error);
    });
}

/**
 * 댓글 목록을 가져오는 함수
 * @param donaNo {number} 게시글 번호
 * @param callback {Function} 댓글 목록을 받은 후 실행할 콜백 함수
 */

// getList: 특정 게시글 번호에 대한 댓글 목록을 가져오는 함수
// GET 요청으로 댓글 목록을 받아오고, 콜백 함수에 데이터를 전달
export function getList(donaNo, callback) {
    console.log(donaNo + "getList 함수호출");

    // GET 요청을 통해 댓글 목록을 가져옴
    fetch(`/v1/donas/${donaNo}/comments`, {
        method: 'GET'  // HTTP 메서드는 GET
    }).then(resp => resp.json())  // 응답을 JSON 형식으로 파싱
        .then(dataList => {
            callback(dataList);  // 받은 댓글 목록을 콜백 함수에 전달
        });
}

/**
 * 페이지 번호를 추가하여 댓글 목록을 가져오는 함수
 * @param donaNo {number} 게시글 번호
 * @param page {number} 페이지 번호
 * @param callback {Function} 댓글 목록을 받은 후 실행할 콜백 함수
 */

// getList2: 페이지 번호를 받아 댓글 목록을 가져오는 함수
// 페이징 기능을 구현할 때 사용
export function getList2(donaNo, page, callback) {
    // 페이지 번호를 추가하여 댓글 목록을 가져옴
    fetch(`/v2/donas/${donaNo}/comments?page=${page}`, {
        method: 'GET'  // HTTP 메서드는 GET
    }).then(resp => resp.json())  // 응답을 JSON 형식으로 파싱
        .then(dataList => {
            callback(dataList);  // 받은 댓글 목록을 콜백 함수에 전달
            console.log(donaNo);  // donaNo 출력 (디버깅용)
        });
    console.log("getList2 확인2 ====");  // 디버깅 로그
}

/**
 * 댓글을 수정하는 함수
 * @param updateInfo {Object} 수정할 댓글 정보 (donaCommentNo, donaCommentContent)
 * @param callback {Function} 수정이 완료된 후 실행할 콜백 함수
 */

// modify: 댓글을 수정하는 함수
// 수정할 댓글 내용을 PATCH 요청으로 서버에 보냄
export function modify(updateInfo, callback) {
    // PATCH 요청을 통해 댓글 내용을 수정
    fetch(`/v1/comments/${updateInfo.donaCommentNo}`, {
        method: 'PATCH',  // HTTP 메서드는 PATCH
        headers: { 'Content-Type': 'application/json;' },  // 요청 본문은 JSON 형식
        body: JSON.stringify({ donaCommentContent: updateInfo.donaCommentContent })  // 수정할 내용만 JSON으로 변환
    }).then(resp => {
        if (resp.status === 200) {
            // 수정이 성공적으로 완료되면 콜백 함수 호출
            callback();
        }
    });
}

/**
 * 댓글을 삭제하는 함수
 * @param donaCommentNo {number} 삭제할 댓글의 ID
 * @param callback {Function} 삭제가 완료된 후 실행할 콜백 함수
 */

// remove: 댓글을 삭제하는 함수 DELETE 요청으로 댓글을 삭제
export function remove(donaCommentNo, callback) {
    // DELETE 요청을 통해 댓글 삭제
    fetch(`/v1/comments/${donaCommentNo}`, {
        method: 'DELETE'  // HTTP 메서드는 DELETE
    }).then(resp => {
        if (resp.status === 200) {
            // 삭제가 성공적으로 완료되면 콜백 함수 호출
            callback();
        }
    });
}

/**
 * 주어진 날짜/시간 값을 현재 시간과 비교하여 경과 시간을 한국어로 반환
 * @param value {string} 날짜/시간 값 (ISO 8601 형식 등)
 * @returns {string} 경과 시간 (예: "방금 전", "1분 전", "2시간 전" 등)
 */

// timeForToday: 댓글 작성 시간이 현재 시간과 얼마나 차이가 나는지 계산하여, 그 차이를 한국어로 표현
// 예: "1시간 전", "2일 전" 등.
export function timeForToday(value) {
    const today = new Date();  // 현재 시간
    const timeValue = new Date(value);  // 입력된 시간

    // 두 시간의 차이를 분 단위로 계산
    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);

    if (betweenTime < 1) { return "방금 전"; }
    if (betweenTime < 60) {
        return `${betweenTime}분 전`;  // 1분 이상 60분 미만
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);  // 시간 단위로 변환
    if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간 전`;  // 24시간 미만
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);  // 일 단위로 변환
    if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일 전`;  // 1년 미만
    }

    return `${Math.floor(betweenTimeDay / 365)}년 전`;  // 1년 이상
}

/**
 * 댓글 신고 기능
 * @param DonaCommentReportDTO {Object} 신고할 댓글에 대한 정보
 * @param callback {Function} 신고가 완료된 후 실행할 콜백 함수
 */

// report: 댓글을 신고하는 함수
// 신고할 댓글 정보를 서버로 보내 POST 요청을 보냄
export function report(DonaCommentReportDTO, callback) {
    // 댓글 신고를 위한 POST 요청
    fetch('/v1/comments/report', {
        method: 'POST',  // HTTP 메서드는 POST
        headers: { 'Content-Type': 'application/json' },  // 요청 본문은 JSON 형식
        body: JSON.stringify(DonaCommentReportDTO),  // 신고할 댓글 정보 (DTO)를 JSON으로 변환하여 요청 본문에 포함
    })
        .then(response => {
            if (response.ok) {
                // 신고가 성공적으로 처리되면 콜백 함수 호출
                callback();
            } else {
                // 신고 처리 응답이 오류인 경우 상태 코드 및 응답 본문 출력
                console.error("신고 처리 응답 오류:", response.status);
                response.text().then(text => console.error("신고 처리 서버 응답 본문:", text));
            }
        })
        .catch(error => {
            // 신고 처리 중 오류가 발생한 경우, 에러 메시지 출력
            console.error('신고 처리 중 오류 발생:', error);
        });
}
