package com.example.backends.controller;

import com.example.backends.entity.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import static java.util.Comparator.*;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;

// mock api controller
@RestController
@RequestMapping("comments")
public class CommentController {

    @Value("${mock.api.delayofmillis}")
    private long delayOfMillis;

    @GetMapping("{entryId}")
    public Flux<Comment> findByEntryId(@NotNull @PathVariable Long entryId) {
        return Flux.range(1, 10)
                .map(integer -> Comment.builder()
                        .entryId(entryId)
                        .number(Long.valueOf(integer))
                        .body(String.format("the comment number of %d at entry_%d", integer, entryId))
                        .userId("test userId")
                        .postedDate(new Date())
                        .build())
                .sort(comparing(Comment::getNumber, naturalOrder()))
                .delaySequence(Duration.ofMillis(delayOfMillis))
                .log(String.format("comments/%d", entryId));
    }

}
