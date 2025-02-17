package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.PostResponseDto;
import com.newsgroup.newsfeed.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping ("/posts")
    public ResponseEntity<List<PostResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<PostResponseDto> result = postService.findAll(page, size);
        return ResponseEntity.ok(result);
    }

    // 좋아요 수 증가
    @PostMapping("/posts/{postId}/thumbs-up")
    public ResponseEntity<Void> increaseThumbsUp(@PathVariable Long postId) {
        postService.increaseThumbsUp(postId);
        return ResponseEntity.ok().build();
    }

    // 댓글 수 증가
    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<Void> addComment(@PathVariable Long postId) {
        postService.addComment(postId);  // 서비스에서 댓글 수 증가
        return ResponseEntity.ok().build();    // 200 OK 응답
    }

        // 제인님이 게시글 생성 구현하면.
//    @PutMapping ("/posts{id}")
//    public ResponseEntity<PostResponseDto> update(
//            @PathVariable Long id,
//            @RequestBody PostRequestDto dto
//            ) {
//        return ResponseEntity.ok(postService.update(id, dto));
//    }

    @DeleteMapping("/posts{id}")
    public void delete(@PathVariable Long id) {
        postService.deleteById(id);
    }
}
