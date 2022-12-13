package com.store.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Generate parameterized constructor and getters using lombok library.
@AllArgsConstructor
@Getter
public class ResponseDTO {

    private String message;
    private Object data;
    private String token;
}
