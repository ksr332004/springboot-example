Spring-Boot-Thymeleaf-Basic-Example
===================================
> Spring Boot와 Thymeleaf를 이용한 간단한 게시판 예제

### Prerequisites
- JDK 1.8
- Spring Boot
- Thymeleaf
- JPA
- H2

##Structure
~~~bash
main
├── java
│   └── com
│       └── example
│           └── basic
│               ├── BasicApplication.java
│               ├── controller
│               │   └── BoardController.java
│               ├── domain
│               │   └── Board.java
│               ├── repository
│               │   └── BoardRepository.java
│               └── service
│                   ├── BoardService.java
│                   └── impl
│                       └── BoardServiceImpl.java
└── resources
    ├── application.yml
    ├── static
    └── templates
        ├── edit-board.html
        ├── list-board.html
        └── write-board.html
~~~

##Comment
###@Controller vs @RestController
- @Controller는 @ResponseBody를 이용하여 객체를 JSON 형식으로 반환시킴
- @RestController는 객체를 JSON/XML 타입으로 반환하는 REST 서비스에 최적화되어 있음

###Formatting date in Thymeleaf
- 객체에 선언된 날짜 타입에 따라 Thymeleaf 날짜 출력 형식이 달라짐
~~~html
/* 객체에 선언된 형식이 Date일 경우 */
<td th:text="${#dates.format(object.date, 'yyyy-MM-dd')}"></td>

/* 객체에 선언된 형식이 Calendar일 경우 */
<td th:text="${#calendars.format(object.date, 'yyyy-MM-dd')}"></td>

/* 객체에 선언된 형식이 LocalDateTime일 경우 */
<td th:text="${#temporals.format(object.date, 'yyyy-MM-dd')}"></td>
~~~
