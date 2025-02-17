package com.newsgroup.newsfeed.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    // 댓글 작성 요청 받을 DTO
    // 수정 시 새로운 content만 전달받음
    private String content;
}