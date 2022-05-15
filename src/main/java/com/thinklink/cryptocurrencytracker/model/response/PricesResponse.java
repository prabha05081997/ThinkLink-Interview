package com.thinklink.cryptocurrencytracker.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PricesResponse {
    private String url;
    private String next;
    private long count;
    private List<CoinDataResponse> data;

    public PricesResponse(String url, String next, long count, List<CoinDataResponse> data) {
        this.url = url;
        this.next = next;
        this.count = count;
        this.data = data;
    }
}
