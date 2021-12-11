package com.sj.project.web;

import com.sj.project.service.posts.PostService;
import com.sj.project.web.dto.PostResponseDto;
import com.sj.project.web.dto.PostSaveRequestDto;
import com.sj.project.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostService postService;

    @PostMapping("/api/v1/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }

    @GetMapping("/api/v1/post/{id}")
    public PostResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }


}
