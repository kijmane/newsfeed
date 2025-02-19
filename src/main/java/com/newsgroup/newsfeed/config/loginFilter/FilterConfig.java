package com.newsgroup.newsfeed.config.loginFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());

        /* 팔로우 URL 필터링 */
        registrationBean.addUrlPatterns("/users/*/follow/*", "/users/*/unfollow/*");

        /* 댓글 URL 필터링 */
        registrationBean.addUrlPatterns("/comments/*");

        /* 로그인 URL 필터링 */
        registrationBean.addUrlPatterns("/users/update/*");

        /* 게시물 URL 필터링 */
        registrationBean.addUrlPatterns("/posts/*");

        registrationBean.setOrder(1); // 필터 순서 설정 (낮을수록 먼저 실행)
        return registrationBean;
    }
}
