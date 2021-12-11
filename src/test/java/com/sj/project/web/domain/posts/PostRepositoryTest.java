package com.sj.project.web.domain.posts;

import com.sj.project.domain.posts.PostRepository;
import com.sj.project.domain.posts.Posts;
import com.sj.project.web.dto.PostResponseDto;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void cleanup(){
        postRepository.deleteAll();
    }
    @Test
    public void 게시글저장_불러오기테스트(){
        
        String title = "테스트 제목";
        String content = "테스트 중입니다.";
        postRepository.save(
                Posts.builder().title(title)
                        .content(content)
                        .author("최시준")
                        .build()
        );

        //when
        List<Posts> postList = postRepository.findAll();

        //then
        Posts posts = postList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

        

    };

    @Test
    public void TestBaseEntity_시간수정테스트중() throws Exception{
        LocalDateTime now = LocalDateTime.of(2020,12,10,0,0,0);
        postRepository.save(
                Posts.builder()
                        .title("timetest")
                        .author("최시준이")
                        .content("시간 감지 클래스 테스트중입니다.")
                        .build());

        //when
        List<Posts> postsList = postRepository.findAll();
        PostResponseDto ResponseDto = new PostResponseDto(postsList.get(0));

        System.out.println(">>>>> createDate = " + ResponseDto.getNew_date() + "\n"
        + ">>>>>>>> modifyDate" + ResponseDto.getEdit_date()
        );

        assertThat(ResponseDto.getNew_date().isAfter(now));
        assertThat(ResponseDto.getEdit_date().isAfter(now));
    }
}
