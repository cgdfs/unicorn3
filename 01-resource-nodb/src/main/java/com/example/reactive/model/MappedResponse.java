package com.example.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MappedResponse extends Resource{

    private Long id;
    private String processStatus;
}
