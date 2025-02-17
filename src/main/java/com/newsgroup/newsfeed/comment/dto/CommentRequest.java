package com.newsgroup.newsfeed.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    // 댓글 수정 시 새로운 내용만 전달받음
    @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.") // 빈 값 방지
    private String content;
}