package com.newsgroup.newsfeed.service.posts;


import com.newsgroup.newsfeed.dto.requestDtos.post.PostRequestDto;
import com.newsgroup.newsfeed.dto.responseDtos.post.PostResponseDto;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import com.newsgroup.newsfeed.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시물 생성

    @Transactional
    public PostResponseDto createPost(Users user, PostRequestDto request) {
        if (user == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED); // 401
        }

        Posts post = Posts.builder()
                .user(user)
                .email(request.getEmail())
                .content(request.getContent())
                .thumbsUpCount(0L)
                .build();

        Posts savedPost = postRepository.save(post);
        return new PostResponseDto(
                savedPost.getId(),
                savedPost.getEmail(),
                savedPost.getContent(),
                savedPost.getThumbsUpCount(),
                savedPost.getCreatedDate(),
                savedPost.getUpdateDate(),
                savedPost.getCommentsCount()
        );
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending());
        Page<Posts> posts = postRepository.findAll(pageable);
        return posts.getContent().stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getEmail(),
                        post.getContent(),
                        post.getThumbsUpCount(),
                        post.getCreatedDate(),
                        post.getUpdateDate(),
                        post.getCommentsCount()))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto update(Long id, String email, PostRequestDto dto) {
        Posts post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );

        // 작성자 검증

        if (!post.getEmail().equals(email)) {
            throw new IllegalArgumentException("작성자만 수정이 가능합니다.");
        }

        post.update(dto.getContent());
        postRepository.save(post);

        return  new PostResponseDto(post.getId(),
                dto.getEmail(),
                dto.getContent(),
                dto.getThumbsUpCount(),
                dto.getCreatedDate(),
                dto. getUpdateDate(),
                dto.getCommentsCount());

    }

    @Transactional
    public void deleteById(Long id, String email) {

        Posts post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다."));

        // 게시글 작성자와 수정자의 이메일 일치확인
        if (!post.getEmail().equals(email)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }


        postRepository.deleteById(id);
    }


    // 좋아요 수 증가
    @Transactional
    public void increaseThumbsUp(Long postId) {
        Posts posts = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        posts.increaseThumbsUp();

        postRepository.save(posts);
    }

    // 댓글 수 증가
    @Transactional
    public void addComment(Long postId) {
        Posts posts = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        postRepository.save(posts);
    }
}
