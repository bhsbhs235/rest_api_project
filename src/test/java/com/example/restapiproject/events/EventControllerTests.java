package com.example.restapiproject.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper; // spring boot에 빈으로 등록되어 있음

    /*@MockBean
    EventRepository eventRepository;*/ // WebMvcTest는 웹용 빈들만 추가해주고 Repository는 안해줌, 그래서 Mock객체 만들어서 넣어줌

    @Test
    public void createEvent() throws Exception {
        Event event = Event.builder()
                        .name("Hyoseong")
                        .description("REST API")
                        .beginEnrollmentDateTime(LocalDateTime.of(2022, 01, 01, 01, 01))
                        .closeEnrollmentDateTime(LocalDateTime.of(2022,01,02,01,01))
                        .beginEventDateTime(LocalDateTime.of(2022, 01, 03, 01, 01))
                        .endEventDateTime(LocalDateTime.of(2022, 01, 04, 01, 01))
                        .basePrice(100)
                        .maxPrice(200)
                        .limitOfEnrollment(100)
                        .location("서초구")
                        .free(true)
                        .offline(false)
                        .eventStatus(EventStatus.PUBLISHED)
                        .build();
        //event.setId(10); // return 할 때 Id가 없기 때문에 만들어줘야함
        //Mockito.when(eventRepository.save(event)).thenReturn(event); //eventRepository.save가 실행되면 event를 return 해라


        // 안해주면 save 하면 return이 null 이라 Exception 발생
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // request content type
                        .accept(MediaTypes.HAL_JSON) // return content type
                        .content(objectMapper.writeValueAsString(event))) // request body(content)에 JSON방식으로 담음
                .andDo(print()) // 무엇을 보내고 무엇을 받는지 출력
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION /*"Location"*/))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE /*"Content-Type"*/, MediaTypes.HAL_JSON_VALUE /*"application/hal+json"*/))
                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()));
    }

    @Test
    public void createBadRequest() throws Exception{
        Event event = Event.builder()
                .name("Hyoseong")
                .description("REST API")
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 01, 01, 01, 01))
                .closeEnrollmentDateTime(LocalDateTime.of(2022,01,02,01,01))
                .beginEventDateTime(LocalDateTime.of(2022, 01, 03, 01, 01))
                .endEventDateTime(LocalDateTime.of(2022, 01, 04, 01, 01))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("서초구")
                .free(true)
                .offline(false)
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // request content type
                        .accept(MediaTypes.HAL_JSON) // return content type
                        .content(objectMapper.writeValueAsString(event))) // request body(content)에 JSON방식으로 담음
                .andDo(print()) // 무엇을 보내고 무엇을 받는지 출력
                .andExpect(status().isBadRequest());
    }
}
