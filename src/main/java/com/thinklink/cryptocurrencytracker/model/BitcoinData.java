package com.thinklink.cryptocurrencytracker.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BitcoinData {
    @SerializedName("bitcoin")
    private CoinData bitcoinData;
}
