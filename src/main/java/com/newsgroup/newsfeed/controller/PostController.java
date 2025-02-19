package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.config.GetLoginUser;
import com.newsgroup.newsfeed.dto.request.post.PostRequest;
import com.newsgroup.newsfeed.dto.response.post.PostResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.posts.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.newsgroup.newsfeed.config.GetLoginUser.getLoginUser;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 추가 (인증된 사용자만 가능)
    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(
            HttpSession session,
            @RequestBody PostRequest request
    ) {
        Users user = getLoginUser(session);
        PostResponse response = postService.createPost(user, request);
        return ResponseEntity.ok(response);
    }

    // 게시글 전체조회 (15개 게시물 조회)
    @GetMapping ("/posts")
    public ResponseEntity<List<PostResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size
    ) {
        List<PostResponse> result = postService.findAll(page, size);
        return ResponseEntity.ok(result);
    }

    // 게시글 수정 (작성자만 수정가능)
    @PutMapping ("/posts/{id}")
    public ResponseEntity<PostResponse> update(
            @PathVariable Long id,
            @RequestBody String email,
            @RequestBody PostRequest dto
            ) {
        return ResponseEntity.ok(postService.update(id, email, dto));
    }

    //게시글 삭제 (작성자만 수정가능)
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                       @RequestParam String email) {
        postService.deleteById(id, email);
        return ResponseEntity.ok(). build();
    }

    // 좋아요 수 증가
    @PostMapping("/posts/{id}/thumbs-up")
    public ResponseEntity<Void> increaseThumbsUp(@PathVariable Long postId) {
        postService.increaseThumbsUp(postId);
        return ResponseEntity.ok().build();
    }
}
