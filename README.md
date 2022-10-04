# SpringWeek01

로그인 기능 없는 개인 블로그 개념으로 웹 사이트를 만들었습니다.

기본적으로 메인 페이지에서 게시글들이 카드 형식으로 표시되고 게시글 상세 페이지, 수정 페이지로 구성되어 있습니다.

구현한 API는 아래와 같습니다.

API 명세서

| 기능           |    Method.    | URL.      |   Return|
| ------------- | ------------- |-----------|----------
| 게시글 전체 조회  | GET           |/post/list | List<PostDto>|
| 게시글 상세 조회  | GET           |/post/detail/{id}| PostDto|
| 게시글 삭제 | POST | /post/detail/delete | String msg|
| 게시글 수정 | POST | /post/detail/update | String msg|
