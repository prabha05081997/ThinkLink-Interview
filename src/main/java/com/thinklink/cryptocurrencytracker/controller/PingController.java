package com.thinklink.cryptocurrencytracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PingController {

    private final String PING_RESPONSE = "I Am Healthy";

    @GetMapping("/v1/ping")
    public ResponseEntity<String> ping() throws Exception {
        log.info("in ping");
        ResponseEntity<String> responseEntity = new ResponseEntity(PING_RESPONSE, HttpStatus.OK);
        log.info("the response entity is {}", responseEntity);
        return responseEntity;
    }

}
