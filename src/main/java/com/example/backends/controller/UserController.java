package com.example.backends.controller;

import com.example.backends.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.Duration;

// mock api controller
@RestController
@RequestMapping("users")
public class UserController {

    @Value("${mock.api.delayofmillis}")
    private long delayOfMillis;

    @GetMapping("{userId}")
    public Mono<User> getById(@NotNull @PathVariable final String userId) {
        if ("unsubscribed-user".equals(userId)) {
            return Mono.just(genUser(userId, User.UserState.UNSUBSCRIBE))
                    .delayElement(Duration.ofMillis(delayOfMillis))
                    .log(String.format("users/%s(invalid)", userId));
        }
        return Mono.just(genUser(userId, User.UserState.VALID))
                .delayElement(Duration.ofMillis(delayOfMillis))
                .log(String.format("users/%s", userId));
    }

    private User genUser(String userId, User.UserState userState) {
        return User.builder()
                .userId(userId)
                .name(String.format("the name of %s", userId))
                .mail(String.format("%s@example.com", userId))
                .userState(userState)
                .build();
    }

}
