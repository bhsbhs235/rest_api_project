package com.example.restapiproject;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    입력받는 값(Dto)이랑 엔티티랑은 분리해 주면 좋다
    1. Entity의 값이 변하면 Repository 클래스의 Entity Manager의 flush가 호출될 때 DB에 값이 반영되고,
    이는 다른 로직들에도 영향 미친다.View와 통신하면서 필연적으로 데이터의 변경이 많은 DTO클래스를 분리해주어야 한다.

    2. 도메인 설계가 아무리 잘 되있다 해도 Getter만을 이용해서 원하는 데이터를 표시하기 어려운 경우가 발생할 수 있는데,
     이 경우에 Entity와 DTO가 분리되어 있지 않다면 Entity안에 Presentation을 위한 필드나 로직이 추가되게 되어 객체
      설계를 망가뜨리게 된다. 때문에 이런 경우에는 분리한 DTO에 Presentation로직 정도를 추가해서 사용하고, Entity에는
      추가하지 않아서 도메인 모델링을 깨뜨리지 않는다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
}
