package com.sj.project.web.dto;

import com.sj.project.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime new_date;
    private LocalDateTime edit_date;

    public PostResponseDto(Posts post){
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.new_date = post.getCreateTime();
        this.edit_date = post.getModifiedDate();
    }
}
