package com.example.backends.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Category implements Serializable {

    private String categoryId;
    private String name;

}
