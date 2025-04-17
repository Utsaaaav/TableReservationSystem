package com.cord.trs.controller;

import com.cord.trs.dto.GlobalApiResponse;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class BaseClass {

    public GlobalApiResponse success(String message, Object data) {

        return GlobalApiResponse.builder()
                .message(message)
                .status(true)
                .data(data)
                .build();

    }

    public GlobalApiResponse failure(String message, Object data) {

        return GlobalApiResponse.builder()
                .message(message)
                .status(false)
                .data(data)
                .build();

    }


}
