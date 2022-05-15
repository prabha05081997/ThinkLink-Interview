package com.thinklink.cryptocurrencytracker.controller;

import com.thinklink.cryptocurrencytracker.model.BitcoinData;
import com.thinklink.cryptocurrencytracker.model.response.GenericResponse;
import com.thinklink.cryptocurrencytracker.model.response.PricesResponse;
import com.thinklink.cryptocurrencytracker.service.CryptocurrencyPriceTrackerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CryptocurrencyPriceTrackerController {

    @Autowired
    private CryptocurrencyPriceTrackerService cryptocurrencyPriceTrackerService;

    /**
     * This is controller method for cron process
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/v1/getPrice")
    public GenericResponse<BitcoinData> queryForCryptocurrencyPrice() throws Exception {
        log.info("in queryForCryptocurrencyPrice with token {}");
        GenericResponse<BitcoinData> response = new GenericResponse<>();
        BitcoinData bitcoinData = cryptocurrencyPriceTrackerService
                .queryForCryptocurrencyPrice();
        response.setModel(bitcoinData);
        return response;
    }

    /**
     * This is the controller method for prices API to fetch price from db based on date
     *
     * @param limit
     * @param offset
     * @param date
     * @return
     * @throws Exception
     */
    @GetMapping("/v1/prices")
    public GenericResponse<PricesResponse> queryCryptocurrencyListFromDb(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
                                                                         @RequestParam("date") String date) throws Exception {
        log.info("in queryForCryptocurrencyPrice with date {} limit {} offset {}", date, limit, offset);
        GenericResponse<PricesResponse> response = new GenericResponse<>();
        PricesResponse pricesResponse = cryptocurrencyPriceTrackerService
                .queryCryptocurrencyListFromDb(date, limit, offset);
        response.setModel(pricesResponse);
        return response;
    }
}
