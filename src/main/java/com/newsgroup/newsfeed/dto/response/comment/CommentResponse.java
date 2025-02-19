package com.newsgroup.newsfeed.dto.response.comment;

import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.util.Objects;
/*
    댓글 응답 DTO
    - 클라이언트에 댓글 정보 반환할 때 사용
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String nickname;
    private String content;
    private boolean canModifyOrDelete;

    /*
        댓글 엔티티를 기반으로 응답 객체 생성
     */
    private CommentResponse(Comment comment, Users currentUser) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.canModifyOrDelete = Objects.equals(comment.getUser(), currentUser);
    }
    /*
        댓글 엔티티를 DTO로 변환하는 정적 메서드
     */
    public static CommentResponse from(Comment comment, Users currentUser) {
        return new CommentResponse(comment, currentUser);
    }
}