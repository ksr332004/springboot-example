# Spring Boot Example
> Spring Boot 2.x와 JPA에 익숙해지고 개념정리를 하기 위한 프로젝트 


## 1. [Basic-Example Project](https://github.com/ksr332004/springboot-example/tree/master/basic-example)
> Spring Boot와 JPA 기본 개념 정리를 위한 프로젝트 

### Prerequisites
- JDK 1.8
- Spring Boot 2.x
- Thymeleaf
- JPA
- Gradle
- H2

### 구현기능
- 게시판
   1. 생성/조회/수정/삭제 기능 구현 
   2. 페이징 처리 
   3. 검색 조건에 따른 게시글 검색(동적쿼리) 
- 댓글
   1. @OneToMany의 양방향 바인딩 구현
   2. 생성/조회/삭제 기능 구현


## 2. [Board-Example Project](https://github.com/ksr332004/springboot-example/tree/master/board-example)
> Basic-Example Project를 토대로 RESTful, Vue.js 기반의 게시판 프로젝트 생성

### Prerequisites
- JDK 1.8
- Spring Boot 2.x
   - Spring Security
   - Spring Rest Docs
- Vue.js 2
- JPA
- Gradle
- H2

### 구현기능
- 회원관리
   1. 로그인
   2. 로그아웃
   3. 회원가입
   4. 탈퇴
- 게시판
- 댓글
