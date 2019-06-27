package com.example.backends.controller;

import com.example.backends.entity.Header;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static java.util.Comparator.*;

/**
 * <p>HeaderController</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@RestController
@RequestMapping("headers")
public class HeaderController {

    @GetMapping("find")
    public Mono<List<Header>> findByCategoryId(@RequestParam(required = false) final String categoryId) {
        return Flux.range(1, 10)
                .map(integer -> Header.builder()
                        .entryId(Long.valueOf(integer))
                        .subject(String.format("the subject of %d", integer))
                        .userId("test userId")
                        .categoryId(StringUtils.isEmpty(categoryId) ? "test category" : categoryId)
                        .isDraft(false)
                        .createDate(new Date())
                        .updateDate(new Date())
                        .version(Long.valueOf(1))
                        .build()
                )
                .sort(comparing(Header::getEntryId, naturalOrder()))
                .collectList()
                .delayElement(Duration.ofMillis(1500)) // delay 1.5sec
                .log(String.format("headers/findByCategory/%s", categoryId));
    }

    @GetMapping("{entryId}")
    public Mono<Header> getById(@NotNull @PathVariable final Long entryId) {
        return Mono.just(Header.builder()
                .entryId(entryId)
                .subject(String.format("the subject of %d", entryId))
                .userId("test userId")
                .categoryId("test category")
                .isDraft(false)
                .createDate(new Date())
                .updateDate(new Date())
                .version(Long.valueOf(1))
                .build())
                .delayElement(Duration.ofMillis(1500)) // delay 1.5sec
                .log(String.format("headers/%d", entryId));
    }

}
