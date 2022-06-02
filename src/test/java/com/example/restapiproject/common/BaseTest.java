package com.example.restapiproject.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class) // configure 적용
@ActiveProfiles("test")
/*
    pom.xml에 보면 test에서는 postgres 말고 h2를 사용한다
    기본으로 프로젝트의 resource application.properties를 사용하는데
    profile로 test를 주면 application-test.properties의 내용을 overwrite한다 (단, 겹치는 것만 안겹치는건 application.properties 내용을 그대로 가져다 쓴다)
 */
@Disabled
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // spring boot에 빈으로 등록되어 있음

    @Autowired
    protected ModelMapper modelMapper;
}
