package com.thinklink.cryptocurrencytracker.service;

import com.google.gson.Gson;
import com.thinklink.cryptocurrencytracker.exception.ServiceUnavailablityException;
import com.thinklink.cryptocurrencytracker.model.BitcoinData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class RestService {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private Gson gson;

    /**
     * This is helper service to send http request over the internet to get the current price for bitcoin
     *
     * @return
     * @throws ServiceUnavailablityException
     */
    public BitcoinData getCryptocurrencyPrice() throws ServiceUnavailablityException {
        log.info("in getCryptocurrencyPrice");
        try {
            long startTime = System.currentTimeMillis();
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.coingecko.com/api/v3")
                    .setPath("/simple/price")
                    .setParameter("ids", "bitcoin")
                    .setParameter("vs_currencies", "usd")
                    .setParameter("include_market_cap", "true")
                    .setParameter("include_24hr_vol", "true")
                    .setParameter("include_24hr_change", "true")
                    .setParameter("include_last_updated_at", "true")
                    .build();

            log.info(" the url created is {}", uri.toString());
            var httpRequest = HttpRequest.newBuilder(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                log.error("Error while getting code model {}", response.body());
                throw new ServiceUnavailablityException("Error while getting crypto currency price");
            }
            String body = response.body();
            log.info("crypto currency price response {}", body);
            BitcoinData bitcoinData = gson.fromJson(body, BitcoinData.class);
            log.info("coinData {}", bitcoinData);
            log.info("time taken to coinData is {} ms", (System.currentTimeMillis() - startTime));
            return bitcoinData;
        } catch (Exception e) {
            log.error("Error while getting code model {} ", e);
            e.printStackTrace();
            throw new ServiceUnavailablityException("Error while getting crypto currency price");
        }
    }
}
