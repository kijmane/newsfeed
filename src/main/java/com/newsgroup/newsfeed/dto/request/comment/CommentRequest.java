package com.newsgroup.newsfeed.dto.request.comment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
/*
    댓글 작성 및 수정 요청을 위한 DTO
    - 댓글 작성 시 본 DTO를 이용하여 클라이언트에서 데이터를 전달받음
    - 댓글 내용 (공백 불가) 필수 입력 값 , 공백일 경우 유효성 검사에 의해 예외 발생
 */
@Getter
@AllArgsConstructor
public class CommentRequest {
    @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.")
    private final String content;
}