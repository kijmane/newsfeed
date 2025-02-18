package com.newsgroup.newsfeed.service.posts;


import com.newsgroup.newsfeed.dto.requestDtos.posts.PostsRequestDto;
import com.newsgroup.newsfeed.dto.responseDtos.posts.PostsResponseDto;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.repository.PostsRepository;
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
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostsResponseDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending());
        Page<Posts> posts = postsRepository.findAll(pageable);
        return posts.getContent().stream()
                .map(post -> new PostsResponseDto(
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
    public PostsResponseDto update(Long id, String email, PostsRequestDto dto) {
        Posts post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );

        // 작성자 검증

        if (!post.getEmail().equals(email)) {
            throw new IllegalArgumentException("작성자만 수정이 가능합니다.");
        }

        post.update(dto.getContent());
        postsRepository.save(post);

        return  new PostsResponseDto(post.getId(),
                dto.getEmail(),
                dto.getContent(),
                dto.getThumbsUpCount(),
                dto.getCreatedDate(),
                dto. getUpdateDate(),
                dto.getCommentsCount());

    }

    @Transactional
    public void deleteById(Long id, String email) {

        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다."));

        // 게시글 작성자와 수정자의 이메일 일치확인
        if (!post.getEmail().equals(email)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }


        postsRepository.deleteById(id);
    }


    // 좋아요 수 증가
    @Transactional
    public void increaseThumbsUp(Long postId) {
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        posts.increaseThumbsUp();

        postsRepository.save(posts);
    }

    // 댓글 수 증가
    @Transactional
    public void addComment(Long postId) {
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        postsRepository.save(posts);
    }
}
