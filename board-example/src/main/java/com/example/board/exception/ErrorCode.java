package com.example.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "C001", "유효하지 않는 값 입니다."),
    BOARD_NOT_EXIST(400, "M002", "존재하지 않는 게시글 입니다."),
    COMMENT_NOT_EXIST(400, "M003", "존재하지 않는 댓글 입니다.");

    private int status;
    private final String code;
    private final String message;

}
