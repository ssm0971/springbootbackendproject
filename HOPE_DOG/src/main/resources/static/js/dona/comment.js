
/**
 * 댓글 작성 모듈
 *
 * @param replyInfo {Object} 댓글 등록에 필요한 정보 boardId, content가 필요하다.
 * @param callback {Function} 정상적으로 처리가 완료되면 실행할 함수
 */

export function register(replyInfo, callback) {
    console.log(replyInfo);
    console.log(replyInfo.donaCommentWriter + "======")
    fetch(`/v1/donas/${replyInfo.donaNo}/comment`, {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(replyInfo),
    }).then(resp => {
        if (resp.status === 200) {
            callback();
        } else {
            console.error("응답 오류:", resp.status);
            resp.text().then(text => console.error("서버 응답 본문:", text));
        }
    }).catch(error => {
        console.error("Fetch error:", error);
    });
}

// 댓글 목록을 가져오는 함수
export function getList(donaNo, callback) {
    console.log(donaNo + "getList 함수호출");
    fetch(`/v1/donas/${donaNo}/comments`, {
        method: 'GET'
    }).then(resp => resp.json())
        .then(dataList => { callback(dataList); });
}

// 페이지 번호를 추가하여 댓글 목록을 가져오는 함수
export function getList2(donaNo, page, callback) {
    // console.log("getList2 확인1 ====");
    // console.log(donaNo + "getList2 확인======");
    // fetch(`/v2/donas/${donaNo}/comments?page=${page}`, {
    fetch(`/v2/donas/${donaNo}/comments?page=${page}`, {
        method: 'GET'
    }).then(resp => resp.json())
        .then(dataList => {
            // return dataList;
            callback(dataList);
            console.log(donaNo);
        });
    console.log("getList2 확인2 ====");
}

// 페이지 번호를 추가하여 댓글 목록을 가져오는 함수
// export function getList2(donaNo, page, callback) {
//     fetch(`/v2/donas/${donaNo}/comments?page=${page}`, {
//         method: 'GET'
//     }).then(resp => resp.json())
//         .then(dataList => {
//             console.log("API 응답 데이터:", dataList); // 응답 데이터 로그 확인
//             if (typeof dataList.hasNext === 'undefined') {
//                 dataList.hasNext = page < dataList.totalPages;
//             }
//             callback(dataList);
//         })
//         .catch(error => {
//             console.error("댓글 목록을 가져오는 데 실패했습니다:", error);
//         });
// }


// 댓글 수정을 위한 함수
export function modify(updateInfo, callback) {
    fetch(`/v1/comments/${updateInfo.donaCommentNo}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json;' },
        body: JSON.stringify({ donaCommentContent: updateInfo.donaCommentContent })
    }).then(resp => {
        if (resp.status === 200) {
            callback();
        }
    });
}

// 댓글 삭제를 위한 함수
export function remove(donaCommentNo, callback) {
    fetch(`/v1/comments/${donaCommentNo}`, {
        method: 'DELETE'
    }).then(resp => {
        if (resp.status === 200) {
            callback();
        }
    });
}

// 주어진 날짜/시간 값을 현재 시간과 비교하여 경과시간을 한국어로 반환
export function timeForToday(value) {
    const today = new Date();
    const timeValue = new Date(value);

    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);

    if (betweenTime < 1) { return "방금 전"; }
    if (betweenTime < 60) {
        return `${betweenTime}분 전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간 전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일 전`;
    }

    return `${Math.floor(betweenTimeDay / 365)}년 전`;
}