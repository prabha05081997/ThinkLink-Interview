package com.thinklink.cryptocurrencytracker.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

@Data
public class CoinData {
    private BigDecimal usd;
    @SerializedName("usd_market_cap")
    private BigDecimal usdMarketCap;
    @SerializedName("usd_24h_vol")
    private BigDecimal usd24HVol;
    @SerializedName("usd_24h_change")
    private BigDecimal usd24HChange;
    @SerializedName("last_updated_at")
    private long lastUpdatedAt;
}
