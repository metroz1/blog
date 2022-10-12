# SpringWeek02

JWT를 이용한 로그인 기능 및 게시글의 등록, 수정, 댓글 작성 등의 API가 구현되어 있습니다.

구현한 API는 아래와 같습니다.

API 명세서

| 기능           |    Method.    | URL.              | Request           |   Response|
| ------------- | ------------- |-----------         |------------------|------------
| 회원 가입       | POST           |/api/member/signup | MemberRequestDto| MemberResponseDto
| 로그인          | GET           |/api/member/login| MemberResponseDto| MemberResponseDto
| 게시글 작성     | POST         | /api/auth/post | Authorization, Refresh-Token, PostRequestDto| PostResponseDto
| 게시글 조회     | GET           | /api/post       |                      | PostResponseDto
| 게시글 상세 조회 | GET           | /api/post/{id}.  |                      | PostResponseDto
| 게시글 수정     | PUT          |. /api/auth/post/{id}| Authorization, Refresh-Token, PostRequestDto | PostResponseDto
| 게시글 삭제     | DELETE.      | /api/auth/post/{id} | Authorization, Refresh-Token | String msg
| 댓글 생성      | POST         | /api/auth/comment.  | Authorization, Refresh-Token, CommentRequestDto | CommentResponseDto
| 댓글 목록 조회  | GET.        | /api/comment/{post_id}.   |               | PostResponseDto
| 댓글 수정      | PUT.         | /api/auth/comment/{id}. |  Authorization, Refresh-Token, CommentRequestDto | CommentResponseDto
| 댓글 삭제      | DELETE.      | /api/auth/comment/{id}.  | Authorization, Refresh-Token, CommentRequestDto | String msg
