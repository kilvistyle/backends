package com.example.backends.controller;

import com.example.backends.entity.Body;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.Duration;

/**
 * <p>BodyController</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@RestController
@RequestMapping("bodies")
public class BodyController {

    @GetMapping("{entryId}")
    public Mono<Body> getByEntryId(@NotNull @PathVariable Long entryId) {
        return Mono.just(Body.builder()
                .entryId(entryId)
                .body(String.format("the body of entry_%d", entryId))
                .build())
                .delayElement(Duration.ofMillis(1500)) // delay 1.5sec
                .log(String.format("bodies/%d", entryId));
    }

}
