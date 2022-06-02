package com.example.restapiproject.events;

import com.example.restapiproject.accounts.Account;
import com.example.restapiproject.accounts.AccountSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter  // Entity에는 Setter를 쓰지 않는 것이 좋다(지금은 테스트 용도로 사용중)
@EqualsAndHashCode(of = "id")
@Entity
public class Event {

    @Id @GeneratedValue
    private Integer id;
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
    private boolean offline;
    private boolean free;
    @Enumerated(EnumType.STRING) // EnumType.ORDINAL 은 순서대로 0,1,2 이렇게 값을 넣어주는데 EventStatus 안의 status 변수 순서가 바뀌면 꼬이게 되기 때문에 STRING을 쓺
    private EventStatus eventStatus = EventStatus.DRAFT;
    @ManyToOne
    /*
        Event객체에서 Account를 가져올 때, 모든 정보가 필요하지 않다.
     */
    @JsonSerialize(using = AccountSerializer.class)
    private Account manager;

    public void update() { //  비지니스 로직(Service)에서 처리해야 하는데 간단한거라 넣어는
        // Update free
        if (this.basePrice == 0 && this.maxPrice == 0) {
            this.free = true;
        } else {
            this.free = false;
        }
        // Update offline
        if (this.location == null || this.location.isBlank()) { // isBlank()는 자바 11버전에 추가된 isEmpty보다 더 지향향
            this.offline = false;
        } else {
            this.offline = true;
        }
    }
}

