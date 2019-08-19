package com.example.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    @Column(length = 400, nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime createDate;

    public void setBoard(Board board) {
        if (this.board != null) {
            this.board.getComments().remove(this);
        }
        this.board = board;
        this.board.addComments(this);
    }

}
