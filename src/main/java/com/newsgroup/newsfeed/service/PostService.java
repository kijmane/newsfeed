package com.newsgroup.newsfeed.service;


import com.newsgroup.newsfeed.dto.PostRequestDto;
import com.newsgroup.newsfeed.dto.PostResponseDto;
import com.newsgroup.newsfeed.entity.Post;
import com.newsgroup.newsfeed.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.getContent().stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getEmail(),
                        post.getContent(),
                        post.getThumbsUpNum(),
                        post.getCommentsNum(),
                        post.getCreatedAt(),
                        post.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    // 좋아요 수 증가
    @Transactional
    public void increaseThumbsUp(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        post.increaseThumbsUp();

        postRepository.save(post);
    }

    // 댓글 수 증가
    @Transactional
    public void addComment(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        post.incrementCommentsNum();  // commentsNum 증가

        postRepository.save(post);  // 변경된 엔티티 저장
    }

//     제인님이 게시글 생성 구현하면.
//    public PostResponseDto update(Long id, PostRequestDto dto) {
//        Post post = postRepository.findBy(id).orElseThrow(
//                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
//        );
//
//        post.update(dto.getEmail(),
//                dto.getContent(),
//                dto.getThumbsUpNum(),
//                dto.getCommentsNum(),
//                dto.getCreatedAt(),
//                dto. getUpdatedAt());
//        return  new PostResponseDto(post.getId(),
//                dto.getEmail(),
//                dto.getContent(),
//                dto.getThumbsUpNum(),
//                dto.getCommentsNum(),
//                dto.getCreatedAt(),
//                dto. getUpdatedAt());
//    }

    @Transactional
    public void deleteById(Long id) {
        if(!postRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다.");
        }
        postRepository.deleteById(id);
    }
}
