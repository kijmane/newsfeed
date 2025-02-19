package com.newsgroup.newsfeed.service.posts;


import com.newsgroup.newsfeed.dto.request.post.PostRequest;
import com.newsgroup.newsfeed.dto.response.post.PostResponse;
import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import com.newsgroup.newsfeed.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

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
                .email(user.getEmail())
                .content(request.getContent())
                .thumbsUpCount(0L)
                .build();

        Posts savedPost = postRepository.save(post);
        return new PostResponse(
                savedPost.getId(),
                savedPost.getUser().getEmail(),
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
        Pageable pageable = PageRequest.of(page, size, Sort.by("updateDate").descending()); // 페이지네이션
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

        return  new PostResponse(post.getId(),
                post.getEmail(),
                post.getContent(),
                post.getThumbsUpCount(),
                post.getCreatedDate(),
                post. getUpdateDate(),
                post.getCommentsCount());
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


    // likePost에서 increaseThumbsUp 호출
    @Transactional
    @Override
    public void likePost(Long id, Users user) {
        Posts post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));  // 게시글을 찾지 못한 경우

        // 좋아요 수 증가
        increaseThumbsUp(id); // increaseThumbsUp 메서드 호출
    }

    // increaseThumbsUp 메서드 구현
    @Transactional
    @Override
    public void increaseThumbsUp(Long id) {
        Posts post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));  // 게시글을 찾지 못한 경우

        // 좋아요 수 증가
        post.setThumbsUpCount(post.getThumbsUpCount() + 1); // 좋아요 수 1 증가
        postRepository.save(post);  // 변경된 게시글 저장
    }

    // 댓글 추가 및 댓글 수 증가
    @Transactional
    @Override
    public void addCommentAndUpdateCount(Long postId, Comment comment) {  // (수정) 메서드 정의 수정
        // 게시글 찾기
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        // 댓글과 게시글 연관 설정
        comment.setPost(post);  // 댓글이 어느 게시글에 속하는지 설정

        // 댓글 추가
        post.getCommentList().add(comment);  // 게시글의 댓글 리스트에 댓글 추가

        // 댓글 저장
        commentRepository.save(comment);  // (수정) 댓글을 데이터베이스에 저장

        // 게시글 저장 (Post 엔티티에서 댓글 수는 getCommentsCount() 메서드로 자동으로 처리됨)
        postRepository.save(post);  // 댓글이 추가된 게시글을 저장
    }
}
