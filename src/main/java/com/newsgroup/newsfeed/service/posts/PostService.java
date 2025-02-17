package com.newsgroup.newsfeed.service.posts;


import com.newsgroup.newsfeed.dto.PostRequestDto;
import com.newsgroup.newsfeed.dto.PostResponseDto;
import com.newsgroup.newsfeed.dto.responseDtos.posts.PostsResponseDto;
import com.newsgroup.newsfeed.entity.posts.Posts;
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

    @Transactional(readOnly = true)
    public List<PostsResponseDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending());
        Page<Posts> posts = postRepository.findAll(pageable);
        return posts.getContent().stream()
                .map(post -> new PostsResponseDto(
                        post.getId(),
                        post.getEmail(),
                        post.getContent(),
                        post.getThumbsUpCount(),
                        post.getCommentsCount(),
                        post.getCreatedAt(),
                        post.getModifiedAt()))
                .collect(Collectors.toList());
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

        posts.incrementCommentsNum();  // commentsNum 증가

        postRepository.save(posts);  // 변경된 엔티티 저장
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
                dto.getThumbsUpNum(),
                dto.getCommentsNum(),
                dto.getCreatedAt(),
                dto. getModifiedAt());
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
}
