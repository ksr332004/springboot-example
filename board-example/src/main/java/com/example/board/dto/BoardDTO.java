package com.example.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    @NotNull
    @Size(min = 1, max = 200)
    private String title;

    @Getter
    @Setter
    public static class RequestToCreate extends BoardDTO {
        @NotNull
        @Size(min = 1, max = 300)
        private String content;
    }

    @Getter
    @Setter
    public static class RequestToUpdate extends BoardDTO {
        @NotNull
        private Long id;

        @NotNull
        @Size(min = 1, max = 300)
        private String content;
    }

    @Getter
    @Setter
    public static class ResponseToList extends BoardDTO {
        private Long id;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private LocalDateTime createDate;

        @Builder
        public ResponseToList(Long id, String title, LocalDateTime createDate) {
            super(title);
            this.id = id;
            this.createDate = createDate;
        }
    }

    @Getter
    @Setter
    public static class ResponseToDetail extends BoardDTO {
        private Long id;
        private String content;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private LocalDateTime createDate;
        private List<CommentDTO.ResponseToDetail> comments;

        @Builder
        public ResponseToDetail(Long id, String title, String content, LocalDateTime createDate, List<CommentDTO.ResponseToDetail> comments) {
            super(title);
            this.id = id;
            this.content = content;
            this.createDate = createDate;
            this.comments = comments;
        }
    }
}
