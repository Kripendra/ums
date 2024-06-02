package com.example.ums.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ServerResponse<T> {

    // Custom error parameters
    private boolean success;
    private String message;
    private T data;
}
