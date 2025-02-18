package com.newsgroup.newsfeed.controller.posts;

import com.newsgroup.newsfeed.dto.requestDtos.posts.PostsRequestDto;
import com.newsgroup.newsfeed.dto.responseDtos.posts.PostsResponseDto;
import com.newsgroup.newsfeed.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping ("/posts")
    public ResponseEntity<List<PostsResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<PostsResponseDto> result = postsService.findAll(page, size);
        return ResponseEntity.ok(result);
    }

    @PutMapping ("/posts/{id}")
    public ResponseEntity<PostsResponseDto> update(
            @PathVariable Long id,
            @RequestParam String email,
            @RequestBody PostsRequestDto dto
            ) {
        return ResponseEntity.ok(postsService.update(id, email, dto));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                       @RequestParam String email) {
        postsService.deleteById(id, email);
        return ResponseEntity.ok(). build();
    }

    // 좋아요 수 증가
    @PostMapping("/posts/{id}/thumbs-up")
    public ResponseEntity<Void> increaseThumbsUp(@PathVariable Long postId) {
        postsService.increaseThumbsUp(postId);
        return ResponseEntity.ok().build();
    }
}
