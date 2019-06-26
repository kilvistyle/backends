package com.example.backends.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Comment</p>
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
public class Comment implements Serializable {

    private Long articleId;
    private Long number;
    private String body;
    private String userId;
    private Date postedDate;

}
