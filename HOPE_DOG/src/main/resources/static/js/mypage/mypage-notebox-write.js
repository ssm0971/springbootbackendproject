
// 이전 버튼 제한
function cancleClick() {
  const title = document.getElementById('note-board-title').value;
  const nickname = document.getElementById('note-board-nickname').value;
  const content = document.getElementById('note-board-content').value;

  if (title || nickname || content) {
    const confirmation = confirm("작성 중인 내용은 저장되지 않습니다. 이동하시겠습니까?");
    if (confirmation) {
      window.history.back(); // 이전 페이지로 이동
    }
  } else {
    window.history.back(); // 내용이 없으면 바로 이전 페이지로 이동
  }
}

// 보내기 버튼
// function sendClick(event) {
//   // 기본 submit 동작을 막기 위해 event.preventDefault() 사용
//   event.preventDefault();
//
//   const nickname = document.getElementById('note-board-nickname').value; // 입력된 닉네임 값
//
//   // 닉네임이 비어 있으면 실패 처리
//   if (!nickname) {
//     const confirmation = confirm("받는 분의 닉네임이 존재하지 않습니다.");
//     if (confirmation) {
//       window.history.back();  // 이전 페이지로 이동
//     }
//     return;
//   }
//
//   // 서버로 메시지 전송 요청
//   fetch('/send-message', {  // 메시지 전송 API (서버 URL을 적절히 변경)
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//     },
//     body: JSON.stringify({ nickname: nickname }) // 서버에 보내는 데이터
//   })
//       .then(response => response.json())  // 서버의 JSON 응답을 받음
//       .then(data => {
//         if (data.success) {
//           // 서버가 성공 응답을 보내면 "쪽지를 보냈습니다."
//           const confirmation = confirm("쪽지가 성공적으로 전송되었습니다.");
//           if (confirmation) {
//             window.history.back(); // 이전 페이지로 이동
//           }
//         } else {
//           // 서버가 실패 응답을 보내면 "받는 분의 닉네임이 존재하지 않습니다."
//           const confirmation = confirm("받는 분의 닉네임이 존재하지 않습니다.");
//           if (confirmation) {
//             window.history.back(); // 이전 페이지로 이동
//           }
//         }
//       })
//       .catch(error => {
//         // 네트워크 오류 등의 이유로 실패한 경우
//         const confirmation = confirm("서버와 통신 중 오류가 발생했습니다.");
//         if (confirmation) {
//           window.history.back(); // 이전 페이지로 이동
//         }
//         console.error("서버와 통신 중 오류가 발생했습니다:", error);
//       });
// }function sendClick(event) {
//   // 기본 submit 동작을 막기 위해 event.preventDefault() 사용
//   event.preventDefault();
//
//   const nickname = document.getElementById('note-board-nickname').value; // 입력된 닉네임 값
//
//   // 닉네임이 비어 있으면 실패 처리
//   if (!nickname) {
//     const confirmation = confirm("받는 분의 닉네임이 존재하지 않습니다.");
//     if (confirmation) {
//       window.history.back();  // 이전 페이지로 이동
//     }
//     return;
//   }
//
//   // 서버로 메시지 전송 요청
//   fetch('/send-message', {  // 메시지 전송 API (서버 URL을 적절히 변경)
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//     },
//     body: JSON.stringify({ nickname: nickname }) // 서버에 보내는 데이터
//   })
//       .then(response => response.json())  // 서버의 JSON 응답을 받음
//       .then(data => {
//         if (data.success) {
//           // 서버가 성공 응답을 보내면 "쪽지를 보냈습니다."
//           const confirmation = confirm("쪽지가 성공적으로 전송되었습니다.");
//           if (confirmation) {
//             window.history.back(); // 이전 페이지로 이동
//           }
//         } else {
//           // 서버가 실패 응답을 보내면 "받는 분의 닉네임이 존재하지 않습니다."
//           const confirmation = confirm("받는 분의 닉네임이 존재하지 않습니다.");
//           if (confirmation) {
//             window.history.back(); // 이전 페이지로 이동
//           }
//         }
//       })
//       .catch(error => {
//         // 네트워크 오류 등의 이유로 실패한 경우
//         const confirmation = confirm("서버와 통신 중 오류가 발생했습니다.");
//         if (confirmation) {
//           window.history.back(); // 이전 페이지로 이동
//         }
//         console.error("서버와 통신 중 오류가 발생했습니다:", error);
//       });
// }



// function sendClick() {
//   const nickname = document.getElementById('note-board-nickname').value; // 입력된 닉네임 값
//   const sessionCenterMemberName = sessionStorage.getItem('centerMemberName'); // 세션에서 centerMemberName 가져오기
//   const sessionMemberNickname = sessionStorage.getItem('memberNickname'); // 세션에서 memberNickname 가져오기
//
//   // 세션 값과 닉네임 값이 제대로 가져와졌는지 확인
//   console.log('nickname:', nickname);
//   console.log('sessionCenterMemberName:', sessionCenterMemberName);
//   console.log('sessionMemberNickname:', sessionMemberNickname);
//
//   // 닉네임이 비어 있거나 세션 값에 없는 경우
//   if (!nickname || !sessionCenterMemberName || !sessionMemberNickname) {
//     const confirmation = confirm("받는 분의 닉네임이 존재하지 않습니다.");
//     if (confirmation) {
//       window.location.href = '/mypage/noteboxWrite';  // 원하는 페이지 URL로 변경
//       return;
//     }
//   } else {
//     // 서버에 메시지 전송 요청
//     fetch('/send-message', {  // 메시지 전송 API (서버 URL을 적절히 변경)
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json',
//       },
//       body: JSON.stringify({
//         nickname: nickname,
//         centerMemberName: sessionCenterMemberName,
//         memberNickname: sessionMemberNickname
//       })
//     })
//         .then(response => response.json())  // 서버의 JSON 응답을 받음
//         .then(data => {
//           if (data.success) {
//             // 서버가 성공 응답을 보내면 "쪽지를 보냈습니다."
//             const confirmation = confirm("쪽지를 보냈습니다.");
//             if (confirmation) {
//               console.log("쪽지가 성공적으로 전송되었습니다.");
//               // 성공적으로 메시지를 보낸 후 필요한 추가 작업
//             }
//           } else {
//             // 서버가 실패 응답을 보내면 "받는 분의 닉네임이 존재하지 않습니다."
//             const confirmation = confirm("받는 분의 닉네임이 존재하지 않습니다.");
//             if (confirmation) {
//               console.log("메시지 전송 실패.");
//             }
//           }
//         })
//         .catch(error => {
//           // 네트워크 오류 등의 이유로 실패한 경우
//           console.error("서버와 통신 중 오류가 발생했습니다:", error);
//           const confirmation = confirm("서버와 통신 중 오류가 발생했습니다.");
//           if (confirmation) {
//             console.log("서버 통신 실패.");
//           }
//         });
//   }
// }


// function sendClick() {
//   const nickname = document.getElementById('note-board-nickname').value; // 닉네임 입력값 가져오기
//
//   // 닉네임이 비어있는 경우
// //   if (!nickname) {
// //     const confirmation = confirm("받는 분의 닉네임이 존재하지 않습니다.");
// //     if (confirmation) {
// //       // 사용자가 확인을 클릭했을 때 아무 작업을 하지 않음 (대화상자만 띄운 뒤)
// //       return; // 함수 종료
// //     }
// //   } else {
// //     // 닉네임이 존재하는 경우
// //     const confirmation = confirm("쪽지를 보냈습니다.");
// //     if (confirmation) {
// //       // 사용자가 "확인"을 클릭한 경우, 실제로 쪽지가 보내지는 로직을 추가할 수 있습니다.
// //       console.log("쪽지가 보내졌습니다."); // 예시로 콘솔에 출력
// //     }
// //   }
// // }
//
//   const centerMemberName = sessionStorage.getItem('centerMemberName');
//   const memberNickname = sessionStorage.getItem('memberNickname');
//
//   fetch('/save-session-info', {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//     },
//     body: JSON.stringify({
//       centerMemberName,
//       memberNickname,
//     }),
//   })
//       .then(response => response.json())
//       .then(data => console.log('세션 정보 저장 성공:', data))
//       .catch(error => console.error('세션 정보 저장 실패:', error));
// }
// function sendClick() {
//   const nickname = document.getElementById('note-board-nickname').value; // 입력된 닉네임 값
//   const sessionCenterMemberName = sessionStorage.getItem('centerMemberName'); // 세션에서 centerMemberName 가져오기
//   const sessionMemberNickname = sessionStorage.getItem('memberNickname'); // 세션에서 memberNickname 가져오기
//
//   // 세션 값과 닉네임 값이 제대로 가져와졌는지 확인
//   console.log('nickname:', nickname);
//   console.log('sessionCenterMemberName:', sessionCenterMemberName);
//   console.log('sessionMemberNickname:', sessionMemberNickname);
//
//   // 세션 값이 제대로 가져왔는지 확인
//   if (!sessionCenterMemberName || !sessionMemberNickname) {
//     console.error("세션 값이 없습니다. 세션에서 값을 제대로 가져오지 못했습니다.");
//   }
//
//   // 닉네임이 비어 있거나 세션 값에 없는 경우
//   if (!nickname || !sessionCenterMemberName || !sessionMemberNickname) {
//     const confirmation = confirm("받는 분의 닉네임이 존재하지 않습니다.");
//     if (confirmation) {
//       window.location.href = '/mypage/noteboxWrite';  // 원하는 페이지 URL로 변경
//       return;
//     }
//   } else {
//     // 닉네임이 존재하는 경우
//     const confirmation = confirm("쪽지를 보냈습니다.");
//     if (confirmation) {
//       console.log("쪽지가 보내졌습니다."); // 실제 메시지 보내는 로직 추가
//     }
//   }
// }




