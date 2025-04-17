package com.cord.trs.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class GlobalApiResponse {

    private String message;
    private Boolean status;
    private Object data;

}
