package com.newsgroup.newsfeed.service.posts;


import com.newsgroup.newsfeed.dto.request.post.PostRequest;
import com.newsgroup.newsfeed.dto.response.post.PostResponse;
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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    // 게시글 생성 (인증된 사용자만 가능)
    @Transactional
    @Override
    public PostResponse createPost(Users user, PostRequest request) {
//        Users user = session.getAttribute("세션명");
        if (user == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED); // 401
        } // 로그인한 유저가 맞나?

        Posts post = Posts.builder()
                .user(user)
                .email(request.getEmail())
                .content(request.getContent())
                .thumbsUpCount(0L)
                .build();

        Posts savedPost = postRepository.save(post);
        return new PostResponse(
                savedPost.getId(),
                savedPost.getEmail(),
                savedPost.getContent(),
                savedPost.getThumbsUpCount(),
                savedPost.getCreatedDate(),
                savedPost.getUpdateDate(),
                savedPost.getCommentsCount()
        );
    }

    // 전체 게시글 조회
    @Transactional(readOnly = true)
    @Override
    public List<PostResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending()); // 페이지네이션
        Page<Posts> posts = postRepository.findAll(pageable);
        return posts.getContent().stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getEmail(),
                        post.getContent(),
                        post.getThumbsUpCount(),
                        post.getCreatedDate(),
                        post.getUpdateDate(),
                        post.getCommentsCount()))
                .collect(Collectors.toList());
    }

    // 게시글 수정 (작성자만 수정가능)
    @Transactional
    @Override
    public PostResponse update(Long id, String email, PostRequest dto) {
        Posts post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        // 작성자 검증
        if (!post.getEmail().equals(email)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_POST_UPDATE);
        }

        post.update(dto.getContent());
        postRepository.save(post);
        return  new PostResponse(post.getId(),
                dto.getEmail(),
                dto.getContent(),
                dto.getThumbsUpCount(),
                dto.getCreatedDate(),
                dto. getUpdateDate(),
                dto.getCommentsCount());
    }

    @Transactional
    @Override
    public void deleteById(Long id, String email) {
        Posts post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND)); // 수정할 게시글이 없을 경우
        // 게시글 작성자와 수정자의 이메일 일치확인
        if (!post.getEmail().equals(email)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_POST_DELETE); // 삭제 권한이 없을 경우
        }
        postRepository.deleteById(id);
    }


    // 좋아요 수 증가
    @Transactional
    @Override
    public void increaseThumbsUp(Long postId) {
        Posts posts = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND)); // 삭제할 게시글이 없을 경우
        posts.increaseThumbsUp();
        postRepository.save(posts);
    }

    // 댓글 수 증가
    @Transactional
    @Override
    public void addComment(Long postId) {
        Posts posts = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND)); // 좋아요 할 게시글이 없을 경우
        postRepository.save(posts);
    }
}
