package com.thinklink.cryptocurrencytracker.service;

import com.thinklink.cryptocurrencytracker.exception.ServiceUnavailablityException;
import com.thinklink.cryptocurrencytracker.model.BitcoinData;
import com.thinklink.cryptocurrencytracker.model.dao.CryptocurrencyPriceTracker;
import com.thinklink.cryptocurrencytracker.model.dao.repository.CryptocurrencyPriceTrackerRepository;
import com.thinklink.cryptocurrencytracker.model.response.CoinDataResponse;
import com.thinklink.cryptocurrencytracker.model.response.PricesResponse;
import com.thinklink.cryptocurrencytracker.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static com.thinklink.cryptocurrencytracker.constants.CryptocurrencyConstants.*;

@Service
@Slf4j
public class CryptocurrencyPriceTrackerService {

    @Autowired
    private RestService restService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CryptocurrencyPriceTrackerRepository cryptocurrencyPriceTrackerRepository;
    @Autowired
    private MailerService mailerService;

    @Value("${bitcoin.price.max}")
    private BigDecimal bitcoinPriceMax;
    @Value("${bitcoin.price.min}")
    private BigDecimal bitcoinPriceMin;
    @Value("${mail.to}")
    private String toMailAddress;

    @Scheduled(fixedRate = 30000)
    public BitcoinData queryForCryptocurrencyPrice() throws ServiceUnavailablityException, ParseException {
        BitcoinData bitcoinData = restService.getCryptocurrencyPrice();
        log.info("bitcoinData {}", bitcoinData);
        BigDecimal price = bitcoinData.getBitcoinData().getUsd();
        String lastUpdatedStr = TimeUtils.getUtcTimeFromMillis(bitcoinData.getBitcoinData().getLastUpdatedAt() * 1000);
        log.info("lastUpdatedStr {}", lastUpdatedStr);
        String currentTimeStr = TimeUtils.getUtcTimeFromMillis(System.currentTimeMillis());
        log.info("currentTimeStr {}", currentTimeStr);
        cryptocurrencyPriceTrackerRepository.save(BITCOIN, USD, price, lastUpdatedStr, currentTimeStr, currentTimeStr);

        String subject = null;
        String body = null;
        boolean isSendMail = Boolean.FALSE;
        if(price.compareTo(bitcoinPriceMax) > 0) {
            subject = "Coin dekho price increase alert";
            body = "Coin dekho price is increased to " + price;
            isSendMail = Boolean.TRUE;
        } else if(price.compareTo(bitcoinPriceMin) < 0) {
            subject = "Coin dekho price drop alert";
            body = "Coin dekho price is decreased to " + price;
            isSendMail = Boolean.TRUE;
        }

        if(isSendMail) {
            mailerService.sendFromGMail(new String[]{toMailAddress}, subject, body);
        }
        return bitcoinData;
    }

    public PricesResponse queryCryptocurrencyListFromDb(String date, int limit, int offset) throws ServiceUnavailablityException, ParseException {
        log.info("date {} limit {} offset {}", date, limit, offset);
        Pageable pageable = PageRequest.of(offset, limit);
        Page<CryptocurrencyPriceTracker> cryptocurrencyPriceTrackerPage = cryptocurrencyPriceTrackerRepository.findBycoinPriceLastUpdatedAt(date, pageable);
        log.info("cryptocurrencyPriceTrackerPage {}", cryptocurrencyPriceTrackerPage);
        cryptocurrencyPriceTrackerPage.getTotalPages();
        List<CoinDataResponse> coinDataResponseList = cryptocurrencyPriceTrackerPage.stream().map(CoinDataResponse::new).collect(Collectors.toList());
        log.info("coinDataResponseList {} cryptocurrencyPriceTrackerPage.getTotalPages {} cryptocurrencyPriceTrackerPage.getTotalElements {}",
                coinDataResponseList, cryptocurrencyPriceTrackerPage.getTotalPages(), cryptocurrencyPriceTrackerPage.getTotalElements());
        long totalCount = cryptocurrencyPriceTrackerPage.getTotalElements();
        String url = PRICES_API_URL + "?date=" + date + "&offset=" + offset + "&limit=" + limit;
        String nextUrl = PRICES_API_URL + "?date=" + date + "&offset=" + (offset * limit) + "&limit=" + limit;

        PricesResponse pricesResponse = new PricesResponse(url, nextUrl, totalCount, coinDataResponseList);
        log.info("pricesResponse {}", pricesResponse);

        return pricesResponse;
    }
}
