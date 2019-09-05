package com.example.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    @NotNull
    @Size(min = 1, max = 300)
    private String content;

    @Getter
    @Setter
    public static class RequestToCreate extends CommentDTO {
    }

    @Getter
    @Setter
    public static class RequestToUpdate extends CommentDTO {
        @NotNull
        private Long id;
    }

    @Getter
    @Setter
    public static class ResponseToDetail extends CommentDTO {
        private Long id;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private LocalDateTime createDate;

        @Builder
        public ResponseToDetail(Long id, String content, LocalDateTime createDate) {
            super(content);
            this.id = id;
            this.createDate = createDate;
        }
    }
}
