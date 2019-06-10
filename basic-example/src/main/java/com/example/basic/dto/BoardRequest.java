package com.example.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class BoardRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String title;
    private String content;
}
