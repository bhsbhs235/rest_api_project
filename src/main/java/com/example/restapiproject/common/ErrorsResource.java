package com.example.restapiproject.common;

import com.example.restapiproject.index.IndexController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.Errors;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ErrorsResource extends EntityModel<Errors> {
    public ErrorsResource(Errors content, Link... links) {
        super(content, Arrays.asList(links));
        add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
    }
}
