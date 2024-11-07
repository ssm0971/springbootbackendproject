// 모듈 가져오기
import * as reply from "./comment.js";

let page = 1;
let hasNext = true;

// URL에서 파라미터를 가져오는 함수
function getParameter(name) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(name);
}

// 글 삭제 버튼
function deleteClick() {
  // donaNo 가져오기
  const donaNo = document.querySelector('.donaNo').textContent.trim();

  console.log('donaNo:', donaNo); // donaNo 값을 콘솔에 출력하여 확인

  if (!donaNo) {
    console.error('donaNo가 유효하지 않습니다.');
  }

  if (confirm('정말 삭제하시겠습니까?')) {
    console.log('글이 삭제되었습니다.');

    // donaNo URL에 포함시켜 이동
    location.href = `/dona/delete?donaNo=${donaNo}`;
  } else {
    console.log('글이 삭제되지 않았습니다.');
  }
}

  // 버튼 처리
  let $modifyBtn = document.querySelector('.btn-modify');

  // 수정 버튼 처리
  $modifyBtn?.addEventListener('click', function (){
    let donaNo = this.dataset.id; //클릭된 요소의 data-id 속성값 가져와 변수에 저장
    //this : eventhandler 내에서 이벤트가 발생한 요소
    //dataset : 요소의 모든 data-* 속성을 포함
    //id : data-id 속성의 값 가져오기
    console.log(donaNo);
    location.href = `/dona/modify?donaNo=${donaNo}`;
  });


// ------------------------댓글 메뉴 처리-------------------------------------------
{
  let $replyListWrap = document.querySelector('.reply-list-wrap');
  // 댓글 목록 wrapper 요소 선택
  // .reply-list-wrap 클래스가 적용된 DOM 요소를 선택하고 $replyListWrap 변수에 저장

  $replyListWrap.addEventListener('click', function (e){
    // console.log(e.target);
    // e는 클릭 이벤트 객체
    let $target = e.target;
    // 클릭된 요소 저장


    // 1. 댓글 메뉴 토글
    if($target.classList.contains('reply-btns')){ // 클릭된 요소가 reply-btns 클래스 가진지 확인
      // closest('선택자') : 클릭된 요소의 상위 요소 중 reply-btn-box 클래스를 가진 요소를 찾음
      // querySelector('선택자') : 하위 요소 중 reply-btns__box 클래스를 가진 요소를 찾음
      let $menu = $target.closest('.reply-btn-box').querySelector('.reply-btns__box');
      // 상위요소에서 클릭된 reply-btns__box 박스 찾기

      $menu.classList.toggle('none');
      // none 클래스 토글
      // toggle : 없으면 클래스 넣고, 있으면 빼주기
    // 2. 댓글 수정 초기화
    }else if($target.classList.contains('reply-modify-btn')){
      // 클릭한 요소가 reply-modify-btn 가지고 있는지 확인
      $target.closest('.reply-btns__box').classList.add('none')
      // 상위요소에서 reply-btns__box 추가하여 숨기기

      let $contentBox = $target.closest('.reply').querySelector('.reply-box__content');
      // 상위요소에서 reply-box__content 찾기
      let oldContent = $contentBox.innerText;
      // 기존 댓글 내용 저장

      $contentBox.innerHTML = `
                <div class="modify-box">
                    <textarea class="modify-content">${oldContent}</textarea>
                    <button type="button" class="modify-content-btn">수정 완료</button>
                </div>
            `;
      // 수정 입력창과 버튼 표시
    // 3. 댓글 삭제 처리
    }else if($target.classList.contains('reply-remove-btn')){
      // 클릭된 요소가 reply-remove-btn 클래스 가지고 있는지 확인
      $target.closest('.reply-btns__box').classList.add('none');
      // 상위요소에 reply-btns__box 클래스 추가하여 숨기기

      let donaCommentNo = $target.closest('.reply').dataset.id;
      // 클릭된 댓글의 id 저장
      reply.remove(donaCommentNo, () => {
        // 댓글 삭제 함수
        page = 1; // 페이지 초기화
        reply.getList2(donaNo, page, function (data) {
          // 댓글 목록 다시 가지고 오기
          hasNext = data.hasNext;
          // 다음 페이지 여부 갱신
          displayReply(data.contentList);
          // 댓글 목록 화면에 표시
        });
      });

    // 4. 댓글 수정 처리
    }else if($target.classList.contains('modify-content-btn')) {
      // 클릭된 요소기 modify-content-btn 가지고 있는지
      let donaCommentNo = $target.closest('.reply').dataset.id;
      // 상위에서 reply 찾아 data-id 속성값 가져오기
      let content = $target.closest('.modify-box').querySelector('.modify-content').value;
      // 수정된 댓글 가져와서 변수에 저장

      let updateInfo = {donaCommentNo : donaCommentNo, donaCommentContent : donaCommentContent};
      // 수정된 댓글 정보 객체로 생성

      reply.modify(updateInfo, () => { // 댓글 수정함수 호출
        page = 1; // 페이지 초기화
        reply.getList2(donaNo, page, function (data) { // 댓글목록 가져오기
          hasNext = data.hasNext; // 다음 페이지가 있는지 여부 업데이트
          displayReply(data.contentList); // 댓글 목록 화면에 표시
        });
      });

    }else{
      document.querySelectorAll('.reply-btns__box')
          .forEach(ele => ele.classList.add('none'));
      // reply-btns__box 모든 요소 찾아서 none 클래스 add
    }

  });

}


// ------------------------------댓글 처리-----------------------------
{
  let $btnReply = document.querySelector('.btn-reply');
  // 댓글 작성 버튼 선택


// ?. 연산자는 Optional Chaining (옵셔널 체이닝) 연산자로, JavaScript에서 객체나 배열 등의 속성에 접근할 때
// 값이 null 또는 undefined 인 경우에도 에러를 발생시키지 않고 안전하게 처리할 수 있게 해줍니다.
  $btnReply?.addEventListener('click', function () {
    let content = document.querySelector('#reply-content').value;
    // 댓글 입력창 내용 가져오기

    if(!content) { // 내용 비었는지 확인
      alert("댓글 내용은 필수 사항입니다.") // 경고메시지 표시
      return;
    }

    let replyInfo = {
      donaNo : donaNo,
      donaCommentContent : donaCommentContent
    };

    reply.register(replyInfo, () => { // 댓글 등록 함수 호출
      document.querySelector('#reply-content').value = ''; // 입력창 비워줌
      page = 1; // 페이지 초기화
      reply.getList2(donaNo, page, function (data) {
        hasNext = data.hasNext;
        displayReply(data.contentList);
      });
    });
  });


  // reply.getList(boardId, displayReply);
  reply.getList2(donaNo, page, function (data){ // 페이지 로드 시 댓글 목록 가져오기
    hasNext = data.hasNext;
    displayReply(data.contentList);
  });

  // 스크롤 이벤트 처리
  window.addEventListener('scroll', function (){
    // console.dir(document.documentElement)
    if(!hasNext) return;
    // documentElement 객체에서 3개의 프로퍼티를 동시에 가져온다.
    let {scrollTop, scrollHeight, clientHeight} = document.documentElement;

    // console.log("scrollTop(스크롤 상단의 현재 위치) : ", scrollTop);
    // console.log("scrollHeight(전체 문서의 높이) : ", scrollHeight);
    // console.log("clientHeight(클라이언트[웹브라우저]의 화면 높이) : ", clientHeight);

    if (clientHeight + scrollTop >= scrollHeight - 5) { // 스크롤이 페이지 끝에 도달했는지 확인
      // console.log("바닥!!!!!")

      page++; // 페이지 번호 증가

      reply.getList2(donaNo, page, function (data){ // 다음 페이지의 댓글 목록 가져오기
        hasNext = data.hasNext;
        appendReply(data.contentList); // 댓글 목록 화면에 추가
      });
    }
  });
}

// 댓글 목록
function displayReply(replyList){
  let $replyWrap = document.querySelector('.reply-list-wrap'); // 댓글 목록 감싸는 요소

  let tags = ''; // html 태그 저장할 변수 초기화

  replyList.forEach(r => { // 댓글 목록 순회해 각 댓글 html로 생성
    // console.log(reply)
    // console.log("확인 " + r + "=======")
    tags += `
            <div class="reply" data-id="${r.donaCommentNo}">
                <div class="reply-box">
                    <div class="reply-box__writer">${r.memberNickname}</div>
                    <div class="reply-box__content">${r.donaCommentContent}</div>
                </div>
                
                <div class="reply-time">
                    ${reply.timeForToday(r.donaCommentRegidate)}
                </div>
                
                <div class="reply-btn-box">
                    <span class="reply-btns"></span>
                    <div class="reply-btns__box none">
                        <div class="reply-remove-btn">삭제</div>
                        <div class="reply-modify-btn">수정</div>
                    </div>
                </div>
            </div>
        `;
  });

  $replyWrap.innerHTML = tags; // 생성된 html 삽입해 댓글 목록 화면에 표시
}


function appendReply(replyList){
  let $replyWrap = document.querySelector('.reply-list-wrap');

  let tags = '';

  replyList.forEach(r => {
    // console.log(reply)

    tags += `
            <div class="reply" data-id="${r.donaCommentNo}">
                <div class="reply-box">
                    <div class="reply-box__writer">${r.memberNickname}</div>
                    <div class="reply-box__content">${r.donaCommentContent}</div>
                </div>
                
                <div class="reply-time">
                    ${reply.timeForToday(r.donaCommentRegidate)}
                </div>
                
                <div class="reply-btn-box">
                    <span class="reply-btns"></span>
                    <div class="reply-btns__box none">
                        <div class="reply-remove-btn">삭제</div>
                        <div class="reply-modify-btn">수정</div>
                    </div>
                </div>
            </div>
        `;
  });

  // innerHTML은 기존의 자식요소들을 전부 덮어 씌우기때문에 새로운 값을 누적하지 않는다.
  // $replyWrap.innerHTML = tags;

  // 새로운 요소를 누적하기 위해서는 insertAdjacentHTML() 메서드를 이용해야한다.
  // insertAdjacentHTML(position, html)
  // position의 종류는 4가지가 있다.
  // 1. beforebegin
  // 2. afterbegin
  // 3. beforeend
  // 4. afterend

  //      [beforebegin] -> element 요소 바로 앞 삽입
  // <div class="reply-wrap">
  //      [afterbegin] -> element 요소의 첫 자식 요소로 삽입
  //    <div class="child"></div>
  //    <div class="child"></div>
  //      [beforeend] -> element 요소의 마지막 자식 요소로 삽입
  // </div>
  //      [afterend] -> element 요소 바로 뒤에 삽입
  $replyWrap.insertAdjacentHTML("beforeend", tags);
}
