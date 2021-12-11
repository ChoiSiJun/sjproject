package com.sj.project.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


//lombok 테스트 클래스
public class HelloResponseDtoTest {

    @Test
    public void 롬북_기능_테스트() {

        //given
        String name = "SJ Test";
        int amount = 1000;

        //When
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
