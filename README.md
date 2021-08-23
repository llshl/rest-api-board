# :pushpin: 1. Overview
* Java 1.8
* Spring boot 2.5.2
* Gradle
* Spring Security
* MyBatis 2.2.0
* MySQL

![rest api board erd (2)](https://user-images.githubusercontent.com/52540882/127375384-6be6398f-6809-493f-91a9-602ce3e8219f.jpg)
      

****
# :pushpin: 2. Feature
* Like/Dislike 기능 구현
* OAuth2.0(Kakao Login)
* 페이징 구현
* Hateoas (예정)
* Internationalization (예정)
* Jenkins를 사용한 CI/CD 구축 (예정)


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
## 로그인
* OAuth 2.0을 사용한 카카오 로그인과 일반 로그인 구현
![로그인](https://user-images.githubusercontent.com/52540882/130479761-dba7ab5c-33e7-47d4-84c2-e78ebfbd4440.PNG)
   
      
## 게시글
* 페이징 처리
![홈](https://user-images.githubusercontent.com/52540882/130479778-20db4170-afca-48f0-995e-d802e79a426e.PNG)
   
      
## 좋아요
* 자신의 게시글과 댓글에는 수정/삭제 버튼 출력
* 좋아요/싫어요 2번 누르면 취소됨
* 좋아요 -> 싫어요 , 싫어요 -> 좋아요 누르면 토글
![게시글보기](https://user-images.githubusercontent.com/52540882/130479783-841a7959-1b50-4d7b-8f1d-23a58af565d6.PNG)
   
      
##HATEOAS
* HATEOAS를 통한 상태 전이 하이퍼링크
![헤테오스](https://user-images.githubusercontent.com/52540882/130479979-338e6500-17c0-428b-9cfc-6a6af2bfe546.png)
   
      
##예외 처리
* ResponseEntityExceptionHandler를 사용한 예외 처리 커스터마이징
![예외처리](https://user-images.githubusercontent.com/52540882/130479982-bf6d6668-6c09-401d-86d7-6e132ab4f255.png)        
   
      
         
****
# :pushpin: 5. Problem/Improve
* MyBatis보단 역시 JPA가 더 편리한 것 같다.
