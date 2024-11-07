// 이 파일은 모듈을 만들어 두는 파일로 사용한다.
// JS의 함수, 클래스를 모듈화 시켜 저장하는 공간이다. (모듈화 == 부품화)
// 우리는 함수를 부품처럼 만들어 두고 사용은 다른 파일에서 할 것이다.
// 이 모듈들을 밖에서 사용할 수 있도록 내보내는 키워드가 export이다.

/**
 * 댓글 작성 모듈
 *
 * @param replyInfo {Object} 댓글 등록에 필요한 정보 boardId, content가 필요하다.
 * @param callback {Function} 정상적으로 처리가 완료되면 실행할 함수
 */
export function register(replyInfo, callback){
    fetch(`/v1/donas/${replyInfo.donaNo}/comment`,
        {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({content: replyInfo.content}),
        }).then(resp => {
        if (resp.status === 200) {
            callback();
        }
    });
}

// 댓글 목록을 가져오는 함수
export function getList(donaNo, callback){
    fetch(`/v1/donas/${donaNo}/comments`, {
        method : 'GET'
    }).then(resp => resp.json())
        .then(dataList => { callback(dataList) });
}

// 페이지 번호를 추가하여 댓글 목록을 가져오는 함수
export function getList2(donaNo, page, callback){
    fetch(`/v2/donas/${donaNo}/comments?page=${page}`, {
        method : 'GET'
    }).then(resp => resp.json())
        .then(dataList => { callback(dataList) });
}

// 댓글 수정을 위한 함수
export function modify(updateInfo, callback){
    fetch(`/v1/comments/${updateInfo.donaCommentNo}`, {
        method : 'PATCH',
        headers : {'Content-Type' : 'application/json;'},
        body : JSON.stringify({content : updateInfo.content})
    }).then(resp => {
        if(resp.status === 200){
            callback();
        }
    });
}

// 댓글 삭제를 위한 함수
export function remove(donaCommentNo, callback){
    fetch(`/v1/comments/${donaCommentNo}`, {
        method: 'DELETE'
    }).then(resp => {
        if(resp.status === 200){
            callback();
        }
    });
}

// 주어진 날짜/시간 값을 현재 시간과 비교하여 경과시간을 한국어로 반환
export function timeForToday(value){
    // new Date() 현재 날짜와 시간
    const today = new Date();
    const timeValue = new Date(value);

    // console.log(today);
    // console.log(timeValue);

    // Math.floor()는 소수점을 내림 처리 해준다.
    // getTime()은 1970년 01/01 을 기준으로 지금까지 몇 ms가 지났는지 알려준다.
    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);

    // console.log(betweenTime);

    if(betweenTime < 1) { return "방금 전"; }
    if(betweenTime < 60) {
        return `${betweenTime}분 전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if(betweenTimeHour < 24){
        return `${betweenTimeHour}시간 전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if(betweenTimeDay < 365){
        return `${betweenTimeDay}일 전`;
    }

    return `${Math.floor(betweenTimeDay / 365)}년 전`;
}










