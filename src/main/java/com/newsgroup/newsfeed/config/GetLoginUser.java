package com.newsgroup.newsfeed.config;

import com.newsgroup.newsfeed.entity.Users;
import jakarta.servlet.http.HttpSession;

import static com.newsgroup.newsfeed.enums.LoginEnum.LOGIN_USER;

// 현재 로그인 한 사용자를 가져오는 유틸
public class GetLoginUser {
    public static Users getLoginUser(HttpSession session) {
        return (Users) session.getAttribute(LOGIN_USER);
    }
}
