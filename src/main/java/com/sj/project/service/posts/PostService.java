package com.sj.project.service.posts;

import com.sj.project.domain.posts.PostRepository;
import com.sj.project.domain.posts.Posts;
import com.sj.project.web.dto.PostResponseDto;
import com.sj.project.web.dto.PostSaveRequestDto;
import com.sj.project.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public long save(PostSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntitiy()).getId();
    }

    @Transactional
    public Long update(Long id ,PostUpdateRequestDto requestDto) {

        Posts post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당하는 게시물이 존재하지 않습니다." + "ID : " + id )
        );
        post.update(requestDto.getTitle() , requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id){
        Posts post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시물이 없습니다.")
        );

        return new PostResponseDto(post);
    }
}
