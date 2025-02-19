package com.newsgroup.newsfeed.dto.request.comment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequest {
    @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.")
    private final String content;
}