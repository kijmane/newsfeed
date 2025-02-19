package com.newsgroup.newsfeed.config.loginFilter;

import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


//  로그인 인증 필터
//  특정 UPL요청 시 세션 정보를 확인하여 인증된 사용자만 접근 허용
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*Http요청, 응답 객체로 변환*/
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);    // 기존 세션 없으면 null 반환
        if (session != null) {
            filterChain.doFilter(servletRequest, servletResponse);  // 인증된 경우 다음 필터 진행
        } else {
            throw new CustomException(ErrorCode.FORBIDDEN); // 인증되지 않은 경우 예외 발생
        }
    }
}
