package com.sj.project.web;


import com.sj.project.domain.posts.PostRepository;
import com.sj.project.domain.posts.Posts;
import com.sj.project.web.dto.PostSaveRequestDto;
import com.sj.project.web.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void testDown() throws Exception {
        postRepository.deleteAll();
    }


    @Test
    public void Posts_등록된다() throws Exception{
        String title = "테스트 제목";
        String content = "테스트 본문";

        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/post";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postSaveRequestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Post_수정된다() throws Exception{
        Posts savedPost = postRepository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                .build());

        Long update_id = savedPost.getId();

        String expectTitle  = "title2";
        String expectContent = "content2";

        PostUpdateRequestDto requestDto =
                PostUpdateRequestDto.builder()
                                .title(expectTitle)
                                .content(expectContent)
                                .build();

        String url = "http://localhost:" + port + "/api/v1/post/" +  update_id;

        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(expectTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectContent);
    }
}
