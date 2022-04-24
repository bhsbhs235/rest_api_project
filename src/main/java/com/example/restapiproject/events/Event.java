package com.example.restapiproject.events;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter // Entity에는 Setter를 쓰지 않는 것이 좋다(지금은 테스트 용도로 사용중)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Event {

    @Id @GeneratedValue
    private Integer id;  // intellij 단축키 command shift t 해당 테스트 클래스로 이동
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    @Enumerated(EnumType.STRING) // EnumType.ORDINAL 은 순서대로 0,1,2 이렇게 값을 넣어주는데 EventStatus 안의 status 변수 순서가 바뀌면 꼬이게 되기 때문에 STRING을 쓺
    private EventStatus eventStatus = EventStatus.DRAFT;

}
