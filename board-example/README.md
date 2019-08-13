Board-Example Project
===================================
> Basic-Example Project를 토대로 RESTful, Vue.js 기반의 게시판 프로젝트 생성

### Prerequisites
- JDK 1.8
- Spring Boot 2.x
- Vue.js 2
- JPA
- Maven
- H2


## Structure
### 1. 회원관리 (로그인/로그아웃/회원가입/탈퇴)

### 2. 게시판

### 3. 댓글


## Getting Started
### Git Clone
~~~bash
# make directory
mkdir {your_directory}
cd {your_directory}

git init

git config core.sparseCheckout true

git remote add -f origin https://github.com/ksr332004/springboot-example.git

echo "board-example/*">.git/info/sparse-checkout
# echo "board-example/*"| out-file -encoding ascii .git/info/sparse-checkout  # Windows OS

git pull origin master
~~~

### Server Starter
~~~bash
cd {your_directory}\springboot-example\board-example

#build


#start

~~~

### Client Starter
~~~bash
cd {your_directory}\springboot-example\board-example\frontend

#build
npm run build

#start
npm start
~~~