package com.example.restapiproject.accounts;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME) // Runtime에서 필요함
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") // 스프링 시큐리티 'anonymousUser' anonymous() => Authentication
public @interface CurrentUser {
}