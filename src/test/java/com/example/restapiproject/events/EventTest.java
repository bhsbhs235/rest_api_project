package com.example.restapiproject.events;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.converters.Param;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
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

    @Test
    @Parameters //(method = "parametersForTestFree")
    public void testFree(int basePrice, int maxPrice, boolean isFree){
        /*Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();

        event.update();

        assertThat(event.isFree()).isTrue();

        event = Event.builder()
                .basePrice(100)
                .maxPrice(0)
                .build();

        event.update();

        assertThat(event.isFree()).isFalse();*/

        //위의 코드를 파라미터로 중복을 줄일 수 있다.
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        event.update();
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    private Object[] parametersForTestFree() { // prefix로 parametersFor메소드 이름 하면 알아서 찾아서 됨
        return new Object[] {
                new Object[] {0, 0, true},
                new Object[] {100, 0, false},
                new Object[] {0, 100, false},
                new Object[] {100, 200, false}
        };
    }

    @Test
    @Parameters
    public void testOffline(String location, boolean offline){
        /*Event event = Event.builder()
                .location("서초구")
                .build();

        event.update();

        assertThat(event.isOffline()).isTrue();

        event = Event.builder()
                .build();
        event.update();
        assertThat(event.isOffline()).isFalse();*/
        Event event = Event.builder()
                .location(location)
                .build();

        event.update();

        assertThat(event.isOffline()).isEqualTo(offline);
    }

    private Object[] parametersForTestOffline() {
        return new Object[] {
                new Object[] {"강남", true},
                new Object[] {null, false},
                new Object[] {"       ", false}
        };
    }
}