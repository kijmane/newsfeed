package com.newsgroup.newsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.")
    private String content;
}