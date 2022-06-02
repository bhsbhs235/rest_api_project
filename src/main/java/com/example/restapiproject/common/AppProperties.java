package com.example.restapiproject.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component // 빈으로 등록해야함
@ConfigurationProperties(prefix = "my-app")
/*
@Value(${client.id})
private String clientId;
위에 처럼 바인딩 하는 것처럼 클래스 파일로 바인딩 하는 설정 properties 파일에 있는 my-app.*에 대하여 바인딩한다
 */
@Getter @Setter
public class AppProperties {

    @NotEmpty
    private String adminUsername;

    @NotEmpty
    private String adminPassword;

    @NotEmpty
    private String userUsername;

    @NotEmpty
    private String userPassword;

    @NotEmpty
    private String clientId;

    @NotEmpty
    private String clientSecret;

}

