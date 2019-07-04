package com.example.backends.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class Comment implements Serializable {

    private Long entryId;
    private Long number;
    private String body;
    private String userId;
    private Date postedDate;

}
