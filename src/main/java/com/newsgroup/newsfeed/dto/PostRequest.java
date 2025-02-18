package com.newsgroup.newsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {
    // 게시글 생성 요청 DTO
    @NotBlank(message = "게시글 내용은 비워둘 수 없습니다.")
    private String content;
}