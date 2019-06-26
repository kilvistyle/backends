package com.example.backends.controller;

import com.example.backends.entity.Comment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static java.util.Comparator.*;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * <p>CommentController</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    @GetMapping("{articleId}")
    public Mono<List<Comment>> findByArticleId(@NotNull @PathVariable Long articleId) {
        return Flux.range(1, 10)
                .map(integer -> Comment.builder()
                        .articleId(articleId)
                        .number(Long.valueOf(integer))
                        .body(String.format("the comment number of %d at article_%d", integer, articleId))
                        .userId("test userId")
                        .postedDate(new Date())
                        .build())
                .sort(comparing(Comment::getNumber, naturalOrder()))
                .collectList()
                .delayElement(Duration.ofMillis(1500)) // delay 1.5sec
                .log(String.format("comments/%d", articleId));
    }

}
