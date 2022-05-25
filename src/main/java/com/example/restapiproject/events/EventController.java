package com.example.restapiproject.events;

import com.example.restapiproject.EventDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE /* Hal 스펙을 따르는 Json 형태 응답을 주겠다 따라서 body(event)에서 자동으로 JSON형태로 변환함(ObejctMapper에서)*/)
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventValidator eventValidator;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors){
        eventValidator.validate(eventDto, errors);
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        Event event = modelMapper.map(eventDto, Event.class);
        Event newEvent = eventRepository.save(event);
        var selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());  // hateoas Link정보 생성
        URI uri = selfLinkBuilder.toUri();

        EventResource eventResource = new EventResource(event);
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        eventResource.add(selfLinkBuilder.withRel("update-event"));

        return ResponseEntity.created(uri).body(eventResource);

        /*
            ObjectMapper(Spring 에서 Bean으로 자동으로 등록되 있음)에서 BeanSerializer가 event 객체를 Json형태로 바꿈 근데 Java Bean 스펙을 준수해준것만
            Error 객체는 자바빈 스펙을 준수하지 않기 때문에 Json형태로 변경되지 않음
         */
    }
}



