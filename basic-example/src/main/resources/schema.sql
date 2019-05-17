DROP TABLE IF EXISTS board;

CREATE TABLE board (
    id          BIGINT        PRIMARY KEY   AUTO_INCREMENT,
    name        VARCHAR(255),
    title       VARCHAR(255),
    content     VARCHAR(255),
    create_date TIMESTAMP,
);