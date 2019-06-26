package com.example.backends.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Category</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/23 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/23
 */
@Data
@Builder
public class Category implements Serializable {

    private String categoryId;
    private String name;

}
