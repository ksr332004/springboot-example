package com.example.basic.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageRequest {
    static int DEFAULT_SIZE = 10;
    static int MAX_SIZE = 30;

    private int page;
    private int size;
    private Sort.Direction direction;
    private String sort;

    public void setPage(int page) {
        this.page = page < 1 ? 1 : page;
    }

    public void setSize(int size) {
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public PageRequest() {
        this.page = 0;
        this.size = DEFAULT_SIZE;
        this.direction = Sort.Direction.DESC;
        this.sort = "createDate";
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page-1, size, direction, sort);
    }
}