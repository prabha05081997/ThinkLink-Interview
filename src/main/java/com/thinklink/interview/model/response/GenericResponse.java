package com.thinklink.interview.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GenericResponse<T> implements Serializable {
    @JsonProperty("code")
    private int statusCode;
    private T model;
    private String message;

    public GenericResponse() {
        this.statusCode = 200;
        this.message = "success";
    }

}