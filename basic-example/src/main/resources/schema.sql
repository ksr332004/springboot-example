DROP TABLE IF EXISTS board;

CREATE TABLE board (
    id          BIGINT        PRIMARY KEY   AUTO_INCREMENT,
    name        VARCHAR(255),
    title       VARCHAR(255),
    content     VARCHAR(255),
    create_date TIMESTAMP,
);

DROP TABLE IF EXISTS comment;

CREATE TABLE comment (
    id          BIGINT        PRIMARY KEY   AUTO_INCREMENT,
    author      VARCHAR(255),
    content     VARCHAR(255),
    create_date TIMESTAMP,
    board_id    BIGINT NOT NULL,
    CONSTRAINT FKLIJ9OOR1NAV89JEAT35S6KBP1 FOREIGN KEY (board_id) REFERENCES board
);