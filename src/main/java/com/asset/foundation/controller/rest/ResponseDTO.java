package com.asset.foundation.controller.rest;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private String status;
    private String code;
    private String message;
    private Object details;

}
