# Newsfeed
## Project Overview
- SNS의 뉴스피드 백엔드를 구현하는 팀 프로젝트

## Calender
- 25.02.14 : 아이디어 회의 , S.A 작성
- 25.02.14~25.02.18 : 웹 페이지 제작
- 25.02.19 : 기능 시연 및 영상 촬영

## Tech Stack
- Spring
- JPA
- Session 인증 방식

## ERD 설계
<img width="1105" alt="스크린샷 2025-03-09 오후 3 26 29" src="https://github.com/user-attachments/assets/8d86dbcd-a4c8-44ef-8d77-ad15a84460d4" />

## API 명세서
| 기능 | HTTP Method | URL | Request Body | Return Values |
| --- | --- | --- | --- | --- | 
| 게시물 별 댓글 목록 조회 | GET | /comments/{postId} | 200 OK
{
"contents": [
{
"id": 1,
"author": "사용자 닉네임",
"content": "안녕?",
"createdAt": "2025-02-18T14:30:00",
"modifiedAt": "2025-02-18T14:30:00"
},
{
"id": 2,
"author": "다른 사용자",
"content": "반가워!",
"createdAt": "2025-02-18T15:00:00",
"modifiedAt": "2025-02-18T15:10:00"
}
]
} |
