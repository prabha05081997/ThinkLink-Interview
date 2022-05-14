package com.thinklink.cryptocurrencytracker.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @JsonProperty("msg")
    private String message;
    private Integer code;
}
