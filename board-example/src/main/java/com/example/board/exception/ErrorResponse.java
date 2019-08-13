package com.example.board.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    private int status;
    private String code;
    private String message;
    private List<String> error;

    private ErrorResponse(ErrorCode code, List<String> error) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.error = error;
        this.code = code.getCode();
    }

    private ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.error = new ArrayList<>();
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        final List<String> fieldErrors = bindingResult.getFieldErrors()
                .stream()
                .map(e -> e.getField() + "은(는) " + e.getDefaultMessage())
                .collect(Collectors.toList());
        return new ErrorResponse(code, fieldErrors);
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

}
