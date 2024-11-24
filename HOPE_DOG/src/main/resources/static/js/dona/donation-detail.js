// 댓글 관련 기능을 담당하는 모듈 가져오기
import * as reply from "./comment.js";

// 페이지 상태 관리 변수
let page = 1;          // 현재 페이지
let totalPages = 1;    // 총 페이지 수 (현재 사용되지 않음)
let hasNext = true;    // 다음 페이지가 있는지 여부
let donaNo = document.getElementById('donaNo').value;  // 게시글 번호를 DOM에서 가져오기
console.log("============");
console.log(donaNo);
console.log(memberNo);
console.log(centerMemberNo);
console.log("============");

// URL에서 파라미터를 가져오는 함수
function getParameter(name) {
  const urlParams = new URLSearchParams(window.location.search);  // URLSearchParams를 사용하여 query 파라미터 추출
  return urlParams.get(name);  // 원하는 파라미터를 반환
}

// 글 삭제 버튼 클릭 시 처리 함수
function deleteClick() {
  // DOM에서 donaNo를 가져와서 삭제할 글의 번호를 확인
  const donaNo = document.querySelector('.donaNo').textContent.trim();
  console.log('donaNo:', donaNo); // donaNo 값을 콘솔에 출력하여 확인

  if (!donaNo) {
    console.error('donaNo가 유효하지 않습니다.');
  }

  // 삭제 확인을 위한 confirm 창
  if (confirm('정말 삭제하시겠습니까?')) {
    console.log('글이 삭제되었습니다.');
    // donaNo를 URL에 포함시켜 글 삭제 요청
    location.href = `/dona/delete?donaNo=${donaNo}`;
  } else {
    console.log('글이 삭제되지 않았습니다.');
  }
}

// deleteClick 함수를 전역에서 호출할 수 있도록 window 객체에 등록
window.deleteClick = deleteClick;

// 게시글 수정 버튼 클릭 시 처리 함수
function modifyClick() {
  const donaNo = document.querySelector('.donaNo').textContent.trim();  // 게시글 번호
  const centerMemberNo = document.querySelector('.centerMemberNo').textContent.trim();  // 센터 회원 번호

  console.log(donaNo);
  // 수정 확인을 위한 confirm 창
  if (confirm('정말 수정하시겠습니까?')) {
    console.log('수정페이지로 이동합니다.');
    // 수정 페이지로 이동
    location.href = `/dona/modify?donaNo=${donaNo}&centerMemberNo=${centerMemberNo}`;
  } else {
    console.log('수정 페이지로 이동하지 않습니다.');
  }
}

// modifyClick 함수를 전역에서 호출할 수 있도록 window 객체에 등록
window.modifyClick = modifyClick;

// 댓글 신고 처리 함수
function CommentReportClick(donaCommentNo) {
  const reportComment = prompt('신고 사유를 100글자 이내로 입력해주세요'); // 신고 사유를 입력받는 프롬프트
  if (reportComment && reportComment.length <= 100) {
    const donaNo = document.querySelector('.donaNo').textContent.trim();  // 게시글 번호

    // 신고 데이터를 서버에 전송할 형식으로 준비
    const reportData = {
      reportCommentNo: donaCommentNo,
      reportContentNo: donaNo,
      commentReport: reportComment,
      commentReportWriter: memberNo,  // 현재 로그인한 사용자 번호
      commentReportCate: "기부",       // 신고 카테고리
    };

    // 신고 API 호출
    fetch('/v1/comments/report', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(reportData),  // 신고 데이터를 JSON으로 변환하여 전송
    }).then(response => {
      if (response.ok) {
        alert("신고가 정상적으로 접수되었습니다.");
      } else {
        alert("신고 처리에 실패했습니다.");
      }
    }).catch(error => {
      console.error("신고 중 오류 발생:", error);
    });
  } else if (reportComment) {
    alert("신고 사유는 100글자 이내로 입력해주세요.");  // 입력 글자가 100자를 초과하면 경고
  }
}


// ------------------------ 댓글 메뉴 처리 -------------------------------------------

{
  let $replyListWrap = document.querySelector('.reply-list-wrap');

  $replyListWrap.addEventListener('click', function (e) {
    let $target = e.target;  // 클릭된 요소를 가져옴

    // 댓글 메뉴 토글 (수정/삭제/신고 메뉴)
    if ($target.classList.contains('reply-btns')) {
      let $menu = $target.closest('.reply-btn-box').querySelector('.reply-btns__box');
      $menu.classList.toggle('none');  // 메뉴 표시/숨기기
    }
    // 댓글 수정 초기화
    else if ($target.classList.contains('reply-modify-btn')) {
      $target.closest('.reply-btns__box').classList.add('none');  // 수정 버튼 클릭 시 메뉴 숨기기
      let $contentBox = $target.closest('.reply').querySelector('.reply-box__content');
      let oldContent = $contentBox.innerText;  // 기존 댓글 내용
      // 수정용 텍스트박스 추가
      $contentBox.innerHTML = `
        <div class="modify-box">
            <textarea class="modify-content">${oldContent}</textarea>
            <button type="button" class="modify-content-btn">등록</button>
        </div>
      `;
    }
    // 댓글 삭제 처리
    else if ($target.classList.contains('reply-remove-btn')) {
      $target.closest('.reply-btns__box').classList.add('none');  // 메뉴 숨기기
      let donaCommentNo = $target.closest('.reply').dataset.id;  // 삭제할 댓글의 ID

      let confirmed = confirm('정말 이 댓글을 삭제하시겠습니까?');
      if (confirmed) {
        reply.remove(donaCommentNo, () => {
          page = 1;  // 페이지 초기화
          reply.getList2(donaNo, page, function (data) {  // 댓글 목록 재조회
            hasNext = data.hasNext;
            displayReply(data.contentList);  // 댓글 목록 갱신
          });
        });
      }
    }
    // 댓글 수정 처리
    else if ($target.classList.contains('modify-content-btn')) {
      let donaCommentNo = $target.closest('.reply').dataset.id;
      let content = $target.closest('.modify-box').querySelector('.modify-content').value;  // 수정된 내용

      let updateInfo = { donaCommentNo: donaCommentNo, donaCommentContent: content };
      reply.modify(updateInfo, () => {
        page = 1;  // 페이지 초기화
        reply.getList2(donaNo, page, function (data) {  // 댓글 목록 재조회
          hasNext = data.hasNext;
          displayReply(data.contentList);  // 댓글 목록 갱신
        });
      });
    }
    // 댓글 신고 처리
    else if ($target.classList.contains('reply-report-btn')) {
      let donaCommentNo = $target.closest('.reply').dataset.id;
      CommentReportClick(donaCommentNo);  // 신고 처리 함수 호출
    }
    else {
      document.querySelectorAll('.reply-btns__box').forEach(ele => ele.classList.add('none'));  // 메뉴 숨기기
    }
  });
}



// ------------------------------ 댓글 처리 -----------------------------

{
  // 댓글 작성 버튼 클릭 시 처리
  document.querySelector('.btn-reply').addEventListener('click', function () {
    let donaCommentContent = document.querySelector('#reply-content').value;

    if (!donaCommentContent) {
      alert("댓글 내용은 필수 사항입니다.");
      return;
    }

    // 댓글 정보 생성
    let replyInfo = {
      donaNo: donaNo,
      donaCommentContent: donaCommentContent,
      memberNo: memberNo,
      centerMemberNo: centerMemberNo,
      donaCommentWriter: centerMemberNo == null ? memberNo : centerMemberNo,  // 작성자 설정
    };

    console.log("====================확인=========");
    // 댓글 등록
    reply.register(replyInfo, () => {
      document.querySelector('#reply-content').value = '';  // 댓글 작성 후 텍스트 초기화
      console.log("댓글 작성 완료");

      // 댓글 등록 후 목록 재갱신
      page = 1;
      reply.getList2(donaNo, page, function (data) {
        hasNext = data.hasNext;
        displayReply(data.contentList);  // 댓글 목록 표시
      });
    });
  });

  // 초기 댓글 목록 가져오기
  reply.getList2(donaNo, page, function (data) {
    console.log(donaNo + "getList2 확인===");
    hasNext = data.hasNext;
    displayReply(data.contentList);  // 댓글 목록 표시
  });

  // 스크롤 이벤트 처리 (스크롤이 바닥에 닿으면 다음 페이지의 댓글 로드)
  window.addEventListener('scroll', function () {
    if (!hasNext) return;
    let { scrollTop, scrollHeight, clientHeight } = document.documentElement;

    if (clientHeight + scrollTop >= scrollHeight - 5) {
      page++;  // 페이지 증가

      reply.getList2(donaNo, page, function (data) {
        hasNext = data.hasNext;
        appendReply(data.contentList);  // 추가된 댓글 목록 표시
      });
    }
  });
}

// displayReply 함수:
//     이 함수는 replyList에 담긴 댓글 목록을 HTML 형식으로 변환하여 화면에 표시
//     댓글 작성자가 본인(센터 회원 혹은 일반 사용자)일 경우 수정 및 삭제 버튼을 표시하고, 다른 사용자는 신고 버튼만 표시

// 댓글 목록 표시 함수
function displayReply(replyList) {
  console.log("displayReply 확인 ===== ");
  let $replyWrap = document.querySelector('.reply-list-wrap');
  let tags = '';

  // 댓글 목록을 역순으로 정렬하여 최신 댓글이 맨 아래에 오도록 처리
  replyList.reverse().forEach(r => {
    console.log("===== 댓글 내용 ====");
    console.log(r); // 각 댓글 데이터 객체 확인

    tags += `
      <div class="reply" data-id="${r.donaCommentNo}">
        <div class="reply-box">
          <div class="reply-box__writer">${r.commentWriterName}</div>
          <div class="reply-box__content">${r.donaCommentContent}</div>
        </div>
        <div class="reply-time">${reply.timeForToday(r.donaCommentRegidate)}</div>
        <div class="reply-btn-box">
          <span class="reply-btns"></span>
          <div class="reply-btns__box">`;

    // 댓글 작성자가 센터 회원이거나 현재 로그인한 사용자일 경우 수정/삭제 버튼 표시
    if (r.donaCommentWriter == centerMemberNo || r.donaCommentWriter == memberNo) {
      console.log('작성자 일치 =====');
      tags += `<div class="reply-remove-btn">삭제</div>
               <div class="reply-modify-btn">수정</div>`;
    } else {
      tags += `<div class="reply-report-btn">신고</div>`;  // 그 외에는

    }

    tags += `</div>
      </div>
    </div>`;
  });

  // 댓글 목록을 HTML에 삽입
  $replyWrap.innerHTML = tags;
}

// appendReply 함수:
//     이 함수는 스크롤을 내려서 더 많은 댓글을 로드할 때 사용
//     새로운 댓글이 로드되면 기존 댓글 목록에 추가하는 방식으로 동작
// insertAdjacentHTML("beforeend", tags)를 사용하여 기존 댓글 목록의 끝에 새로운 댓글을 추가

// 댓글 목록 추가 함수
function appendReply(replyList) {
  let $replyWrap = document.querySelector('.reply-list-wrap');
  let tags = '';

  // 댓글 목록을 역순으로 정렬하여 최신 댓글이 밑에 오도록 처리
  replyList.reverse().forEach(r => {
    r.center_member_name = undefined;  // center_member_name 데이터가 필요 없으면 undefined 처리
    r.member_nickname = undefined;     // member_nickname 데이터가 필요 없으면 undefined 처리
    console.log(r); // 각 댓글 데이터 객체 확인

    // 작성자가 회원인지 센터 회원인지 확인하여 적절한 이름 표시
    const writerName = r.dona_comment_writer === 'member'
        ? r.member_nickname
        : r.center_member_name;
    console.log(r.commentWriterName); // 각 댓글 데이터 객체 확인

    tags += `
      <div class="reply" data-id="${r.donaCommentNo}">
        <div class="reply-box">
          <div class="reply-box__writer">${r.commentWriterName}</div>
          <div class="reply-box__content">${r.donaCommentContent}</div>
        </div>
        <div class="reply-time">${reply.timeForToday(r.donaCommentRegidate)}</div>
        <div class="reply-btn-box">
          <span class="reply-btns"></span>
          <div class="reply-btns__box none">
            <div class="reply-modify-btn">수정</div>
            <div class="reply-remove-btn">삭제</div>
            <div class="reply-report-btn">신고</div>
          </div>
        </div>
      </div>
    `;
  });

  // 새로운 댓글을 기존 댓글 목록에 추가
  $replyWrap.insertAdjacentHTML("beforeend", tags);
}

// // 페이지네이션 UI 갱신
// function updatePagination() {
//   const paginationContainer = document.querySelector('.pagination');
//   paginationContainer.innerHTML = ''; // 기존 버튼들 초기화
//
//   // 이전 페이지 버튼
//   if (page > 1) {
//     const prevBtn = document.createElement('button');
//     prevBtn.innerText = '이전';
//     prevBtn.addEventListener('click', () => {
//       page--;
//       fetchComments();
//     });
//     paginationContainer.appendChild(prevBtn);
//   }
//
//   // 각 페이지 번호 버튼
//   for (let i = 1; i <= totalPages; i++) {
//     const pageBtn = document.createElement('button');
//     pageBtn.innerText = i;
//     if (i === page) {
//       pageBtn.classList.add('active'); // 현재 페이지 표시
//     }
//     pageBtn.addEventListener('click', () => {
//       page = i;
//       fetchComments();
//     });
//     paginationContainer.appendChild(pageBtn);
//   }
//
//   // 다음 페이지 버튼
//   if (page < totalPages) {
//     const nextBtn = document.createElement('button');
//     nextBtn.innerText = '다음';
//     nextBtn.addEventListener('click', () => {
//       page++;
//       fetchComments();
//     });
//     paginationContainer.appendChild(nextBtn);
//   }
// }
//
// // // 댓글 목록 갱신
// // function fetchComments() {
// //   reply.getList2(donaNo, page, function (data) {
// //     displayReply(data.contentList);
// //
// //     // hasNext 값으로 페이지네이션 업데이트
// //     updatePagination(data.hasNext);
// //   });
// // }
//
// function fetchComments() {
//   if (!donaNo) return; // donaNo 값을 확인합니다.
//
//   reply.getList2(donaNo, page, function(data) {
//     console.log("fetchComments 내부 데이터:", data); // 데이터 로그 확인
//     if (!data || typeof data.totalPages === 'undefined' || !Array.isArray(data.contentList)) {
//       console.error("잘못된 데이터 구조입니다:", data);
//       return;
//     }
//
//     totalPages = data.totalPages;
//     console.log("API 응답으로 받은 totalPages:", totalPages);
//     displayReply(data.contentList);
//     updatePagination(); // 페이지네이션 업데이트
//   });
// }
//
// function updatePagination() {
//   const paginationContainer = document.querySelector('.pagination');
//   paginationContainer.innerHTML = ''; // 기존 버튼들 초기화
//
//   // 이전 페이지 버튼
//   if (page > 1) {
//     const prevBtn = document.createElement('button');
//     prevBtn.innerText = '이전';
//     prevBtn.addEventListener('click', () => {
//       page--;
//       fetchComments();
//     });
//     console.log("이전 버튼 추가");
//     paginationContainer.appendChild(prevBtn);
//   }
//
//   // 페이지 번호 버튼들 추가
//   for (let i = 1; i <= totalPages; i++) {
//     const pageBtn = document.createElement('button');
//     pageBtn.innerText = i;
//     if (i === page) {
//       pageBtn.classList.add('active'); // 현재 페이지 표시
//     }
//     pageBtn.addEventListener('click', () => {
//       page = i;
//       fetchComments();
//     });
//     console.log("페이지 버튼 추가:", i); // 각 페이지 번호 로그
//     paginationContainer.appendChild(pageBtn);
//   }
//
//   // 다음 페이지 버튼
//   if (page < totalPages) {
//     const nextBtn = document.createElement('button');
//     nextBtn.innerText = '다음';
//     nextBtn.addEventListener('click', () => {
//       page++;
//       fetchComments();
//     });
//     console.log("다음 버튼 추가");
//     paginationContainer.appendChild(nextBtn);
//   }
// }
