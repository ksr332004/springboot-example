package com.example.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class BoardRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String title;
    private String content;

    public String getUri() {
        UriComponentsBuilder uriComponents = UriComponentsBuilder.newInstance();
        if (!StringUtils.isEmpty(getName())) {
            uriComponents.queryParam("name", getName());
        }
        if (!StringUtils.isEmpty(getTitle())) {
            uriComponents.queryParam("title", getTitle());
        }
        if (!StringUtils.isEmpty(getContent())) {
            uriComponents.queryParam("content", getContent());
        }
        return uriComponents.build().toUriString();
    }
}
