package com.newsgroup.newsfeed.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 400 Bad Request (잘못된 요청)
    EMPTY_POST_CONTENT(HttpStatus.BAD_REQUEST, "게시글 내용은 비워둘 수 없습니다."),
    EMPTY_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, "댓글 내용은 비워둘 수 없습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    // 401 Unauthorized (인증 필요)
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),

    // 403 Forbidden (권한 없음)
    NO_PERMISSION(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    UNAUTHORIZED_ACTION(HttpStatus.FORBIDDEN, "해당 작업을 수행할 권한이 없습니다."),

    // 404 Not Found (리소스 없음)
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물이 존재하지 않습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}