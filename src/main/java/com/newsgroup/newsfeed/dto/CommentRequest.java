package com.newsgroup.newsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    // 댓글 수정 요청 시 전달되는 데이터 - 새로운 댓글 내용만 포함 , 빈 값일 경우 예외 발생
    @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.")
    private String content;
}