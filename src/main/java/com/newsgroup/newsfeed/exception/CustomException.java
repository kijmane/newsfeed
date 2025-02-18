package com.newsgroup.newsfeed.exception;

public class CustomException extends RuntimeException {
    // 예외 클래스 : 예외 발생 시 사용자 정의 메시지 전달
    public CustomException(String message) {
        super(message);
    }
}