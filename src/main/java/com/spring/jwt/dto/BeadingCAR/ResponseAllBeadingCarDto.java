package com.spring.jwt.dto.BeadingCAR;

import java.util.List;

public class ResponseAllBeadingCarDto {
    private String message;
    private List<BeadingCARDto> list;
    private String exception;

    public ResponseAllBeadingCarDto(String message){
        this.message=message;
    }
}


