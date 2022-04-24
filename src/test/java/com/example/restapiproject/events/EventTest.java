package com.example.restapiproject.events;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder()
                .name("Hyoseong")
                .description("builder test")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void createObject(){
        String name = "Event";
        String description = "Spring";

        Event event = new Event();
        event.setName(name);
        event.setDescription(description); // intellij 단축키 command option v = 리팩토링

        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }
}