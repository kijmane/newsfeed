package com.newsgroup.newsfeed.service.posts;


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
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<Posts> posts = postRepository.findAll(pageable);
        return posts.getContent().stream()
                .map(post -> new PostsResponseDto(
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
