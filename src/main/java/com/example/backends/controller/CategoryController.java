package com.example.backends.controller;

import com.example.backends.entity.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.Duration;

/**
 * <p>CategoryController</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@RestController
@RequestMapping("categories")
public class CategoryController {

    @GetMapping
    public Flux<Category> findAll() {
        return Flux.range(1, 10)
                .flatMap(integer -> this.getById("category_"+integer))
                .log("categories");
    }
    @GetMapping("{categoryId}")
    public Mono<Category> getById(@NotNull @PathVariable String categoryId) {
        if ("nothing".equals(categoryId)) {
            return Mono.empty();
        }
        return Mono.just(Category.builder()
                .categoryId(categoryId)
                .name(String.format("Name of %s", categoryId))
                .build())
                .delayElement(Duration.ofMillis(1500))
                .log(String.format("categories/%s", categoryId));
    }


}
