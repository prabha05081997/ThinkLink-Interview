package com.thinklink.cryptocurrencytracker.model.dao.repository;

import com.thinklink.cryptocurrencytracker.model.dao.CryptocurrencyPriceTracker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface CryptocurrencyPriceTrackerRepository extends JpaRepository<CryptocurrencyPriceTracker, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cryptocurrency_price_tracker (coin_name, currency_type, price, coin_price_last_updated_at, " +
            "created_on, updated_on) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void save(String coinName, String currencyType, BigDecimal price, String coinPriceLastUpdatedAt, String currentTime,
              String updatedTime);

    @Query(value = "SELECT * FROM cryptocurrency_price_tracker where date(coin_price_last_updated_at)=?1 limit ?3 offset ?2", nativeQuery = true)
    public List<CryptocurrencyPriceTracker> findBycoinPriceLastUpdatedAt(String coinPriceLastUpdatedAt, int offset, int limit);

    @Query(value = "SELECT * FROM cryptocurrency_price_tracker where date(coin_price_last_updated_at)=?1", nativeQuery = true)
    public List<CryptocurrencyPriceTracker> findBycoinPriceLastUpdatedAt(String coinPriceLastUpdatedAt);

    @Query(value = "SELECT * FROM cryptocurrency_price_tracker where date(coin_price_last_updated_at)=?1",
            countQuery = "SELECT count(*) FROM cryptocurrency_price_tracker where date(coin_price_last_updated_at)=?1", nativeQuery = true)
    public Page<CryptocurrencyPriceTracker> findBycoinPriceLastUpdatedAt(String coinPriceLastUpdatedAt, Pageable pageable);
}
