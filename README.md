# :pushpin: 1. Overview
* Java 1.8
* Spring boot 2.5.2
* Gradle
* Spring Security
* MyBatis 2.2.0
* MySQL
![rest api board erd](https://user-images.githubusercontent.com/52540882/127374429-2a05a7e3-dddc-455c-bfc9-d04ab0e1d1c9.jpg)
      

****
# :pushpin: 2. Feature
* Like/Dislike 기능 구현
* OAuth2.0(Kakao Login)
* 페이징 구현
* Hateoas (예정)
* Internationalization (예정)


****
# :pushpin: 3. API
## 3.1 게시판 API
|methods|GET|POST|PUT|DELETE|
|------|---|---|---|---|
|/list|게시글 불러오기|게시글 작성하기|
|/list/{id}|게시글 1개 불러오기||게시글 수정하기|게시글 삭제하기|
|/list/search|게시글 검색하기|||
|/list/like/{id}||게시글 좋아요 처리||
|/list/dislike/{id}||게시글 싫어요 처리||   
   
      
## 3.2 댓글 API
|methods|GET|POST|PUT|DELETE|
|------|---|---|---|---|
|/comment/{id}|댓글 불러오기|댓글 작성하기|댓글 수정하기|댓글 삭제하기
   
      
## 3.2 회원 API
|methods|GET|POST|PUT|DELETE|
|------|---|---|---|---|
|/member/{id}|회원 검색하기|||회원 삭제하기
|/member/{nickname}|||닉네임 변경하기|
****

         
# :pushpin: 4. Result

         
****
# :pushpin: 5. Problem/Improve
* 개발중
