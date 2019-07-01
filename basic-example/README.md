Spring-Boot-Thymeleaf-Basic-Example
===================================
> Spring Boot와 Thymeleaf를 이용한 간단한 게시판 예제

### Prerequisites
- JDK 1.8
- Spring Boot
- Thymeleaf
- JPA
- H2

## Structure
### 1. 게시글 조회/등록/수정/삭제
- [github tags](https://github.com/ksr332004/springboot-example/releases/tag/basic-example-1)

### 2. 게시판 Pagination
- JpaRepository 이용  
- Querydsl 이용  
- [github tags](https://github.com/ksr332004/springboot-example/releases/tag/basic-example-2)

### 3. 검색 조건에 따른 게시글 조회(동적쿼리)
- JpaRepository 이용  
   - Specification 형식  
- Querydsl 이용  
   - Predicate 형식  
   - Custom 형식
- [github tags](https://github.com/ksr332004/springboot-example/releases/tag/basic-example-3)

### 4. 게시글에 따른 댓글 구현
- @OneToMany 양방향 바인딩 이용   

## Getting Started
### Git Clone
~~~bash
# make directory
mkdir {your_directory}
cd {your_directory}

git init

git config core.sparseCheckout true

git remote add -f origin https://github.com/ksr332004/springboot-example.git

echo "basic-example/*">.git/info/sparse-checkout
# echo "basic-example/*"| out-file -encoding ascii .git/info/sparse-checkout  # Windows OS

git pull origin master
~~~

### Server Starter
~~~bash
cd {your_directory}\springboot-example\basic-example

#build
gradle build
# gradlew build  # Windows OS

#start
gradle bootRun
~~~

## Comment
### @Controller vs @RestController
- @Controller는 @ResponseBody를 이용하여 객체를 JSON 형식으로 반환시킴
- @RestController는 객체를 JSON/XML 타입으로 반환하는 REST 서비스에 최적화되어 있음

### Formatting date in Thymeleaf
- 객체에 선언된 날짜 타입에 따라 Thymeleaf 날짜 출력 형식이 달라짐
~~~html
/* 객체에 선언된 형식이 Date일 경우 */
<td th:text="${#dates.format(object.date, 'yyyy-MM-dd')}"></td>

/* 객체에 선언된 형식이 Calendar일 경우 */
<td th:text="${#calendars.format(object.date, 'yyyy-MM-dd')}"></td>

/* 객체에 선언된 형식이 LocalDateTime일 경우 */
<td th:text="${#temporals.format(object.date, 'yyyy-MM-dd')}"></td>
~~~

### Add Static Imports in IntelliJ(Version 16)
- IntelliJ에서 AssertJ 관련 함수에 대한 import를 자동으로 불러오지 못할 경우
   > Settings → Editor → General → Auto Import
   > Add unambiguous imports on the fly 체크