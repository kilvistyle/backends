package com.example.backends.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Body implements Serializable {

    private Long entryId;
    private String body;

}
