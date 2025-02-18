package com.newsgroup.newsfeed.service;

import com.newsgroup.newsfeed.dto.PostRequest;
import com.newsgroup.newsfeed.dto.PostResponse;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import com.newsgroup.newsfeed.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    // 게시물 생성
    @Override
    @Transactional
    public PostResponse createPost(Users user, PostRequest request) {
        if (user == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED); // 401
        }

        Posts post = Posts.builder()
                .user(user)
                .content(request.getContent())
                .thumbsUpNum(0L)
                .commentsNum(0L)
                .build();

        Posts savedPost = postRepository.save(post);
        return new PostResponse(savedPost);
    }

    // 전체 게시물 조회
    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        List<Posts> posts = postRepository.findAll();

        if (posts.isEmpty()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND); // 404
        }

        return posts.stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
}