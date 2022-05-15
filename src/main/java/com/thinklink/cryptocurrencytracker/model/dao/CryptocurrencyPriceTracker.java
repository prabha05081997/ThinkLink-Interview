package com.thinklink.cryptocurrencytracker.model.dao;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "cryptocurrency_price_tracker")
@Data
public class CryptocurrencyPriceTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "coin_name", nullable = false)
    private String coinName;
    @Column(name = "currency_type", nullable = false)
    private String currencyType;
    @Column(nullable = false)
    private String price;
    @Column(name = "coin_price_last_updated_at", nullable = false)
    private String coinPriceLastUpdatedAt;
    @Column(name = "created_on", nullable = false)
    private String createdOn;
    @Column(name = "updated_on", nullable = false)
    private String updatedOn;
}
