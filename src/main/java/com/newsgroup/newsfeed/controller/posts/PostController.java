package com.newsgroup.newsfeed.controller.posts;

import com.newsgroup.newsfeed.dto.requestDtos.post.PostRequestDto;
import com.newsgroup.newsfeed.dto.responseDtos.post.PostResponseDto;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시물 추가 (인증된 사용자만 가능)
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost(
            @AuthenticationPrincipal Users user,
            @RequestBody PostRequestDto request
    ) {
        PostResponseDto response = postService.createPost(user, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping ("/posts")
    public ResponseEntity<List<PostResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size
    ) {
        List<PostResponseDto> result = postService.findAll(page, size);
        return ResponseEntity.ok(result);
    }

    @PutMapping ("/posts/{id}")
    public ResponseEntity<PostResponseDto> update(
            @PathVariable Long id,
            @RequestBody String email,
            @RequestBody PostRequestDto dto
            ) {
        return ResponseEntity.ok(postService.update(id, email, dto));
    }
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
