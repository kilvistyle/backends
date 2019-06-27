package com.example.backends.controller;

import com.example.backends.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.Duration;

/**
 * <p>UserController</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping("{userId}")
    public Mono<User> getById(@NotNull @PathVariable final String userId) {
        if ("unsubscribed-user".equals(userId)) {
            return Mono.just(genUser(userId, User.UserState.UNSUBSCRIBE))
                    .delayElement(Duration.ofMillis(1500)) // delay 1.5sec
                    .log(String.format("users/%s(invalid)", userId));
        }
        return Mono.just(genUser(userId, User.UserState.VALID))
                .delayElement(Duration.ofMillis(1500)) // delay 1.5sec
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
