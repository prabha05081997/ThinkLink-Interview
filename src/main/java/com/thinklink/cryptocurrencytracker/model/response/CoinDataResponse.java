package com.thinklink.cryptocurrencytracker.model.response;

import com.thinklink.cryptocurrencytracker.model.dao.CryptocurrencyPriceTracker;
import lombok.Data;

@Data
public class CoinDataResponse {
    private String timestamp;
    private String price;
    private String coin;

    public CoinDataResponse(CryptocurrencyPriceTracker cryptocurrencyPriceTracker) {
        this.timestamp = cryptocurrencyPriceTracker.getCoinPriceLastUpdatedAt();
        this.price = cryptocurrencyPriceTracker.getPrice();
        this.coin = cryptocurrencyPriceTracker.getCoinName();
    }
}
