package com.newsgroup.newsfeed.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 400 Bad Request (잘못된 요청)
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    EMPTY_POST_CONTENT(HttpStatus.BAD_REQUEST, "게시글 내용은 비워둘 수 없습니다."),
    EMPTY_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, "댓글 내용은 비워둘 수 없습니다."),

    // 401 Unauthorized (인증 필요)
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),

    // 403 Forbidden (권한 없음)
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    UNAUTHORIZED_POST_UPDATE(HttpStatus.FORBIDDEN, "게시글 수정 권한이 없습니다."),
    UNAUTHORIZED_POST_DELETE(HttpStatus.FORBIDDEN, "게시글 삭제 권한이 없습니다."),
    NO_PERMISSION(HttpStatus.FORBIDDEN, "댓글 수정/삭제 권한이 없습니다."),

    // 404 Not Found (리소스 없음)
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 사용자가 존재하지 않습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물이 존재하지 않습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글이 존재하지 않습니다."),

    // 409 Conflict (데이터 충돌)
    CONFLICT(HttpStatus.CONFLICT, "데이터 충돌이 발생했습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),

    // 500 Internal Server Error (서버 오류)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}