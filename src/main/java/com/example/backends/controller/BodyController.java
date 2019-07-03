package com.example.backends.controller;

import com.example.backends.entity.Body;
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
@RequestMapping("bodies")
public class BodyController {

    @Value("${mock.api.delayofmillis}")
    private long delayOfMillis;

    @GetMapping("{entryId}")
    public Mono<Body> getByEntryId(@NotNull @PathVariable Long entryId) {
        return Mono.just(Body.builder()
                .entryId(entryId)
                .body(String.format("the body of entry_%d", entryId))
                .build())
                .delayElement(Duration.ofMillis(delayOfMillis))
                .log(String.format("bodies/%d", entryId));
    }

}
