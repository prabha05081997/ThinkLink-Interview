package com.thinklink.cryptocurrencytracker.configuration;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
@Slf4j
public class BeanConfiguration {

    @Value("${spring.datasource.driver-class-name}")
    private String sqliteDriverClassName;
    @Value("${spring.datasource.url}")
    private String sqliteDb;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        long startTime = System.currentTimeMillis();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(sqliteDriverClassName);
        dataSourceBuilder.url(sqliteDb);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        log.info("time taken to intialize dataSourceBuilder is {}", (System.currentTimeMillis() - startTime));
        return dataSourceBuilder.build();
    }

    @Bean
    public HttpClient getHttpClient() {
        long startTime = System.currentTimeMillis();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(60))
                .build();
        log.info("time taken to intialize httpClient is {}", (System.currentTimeMillis() - startTime));
        return httpClient;
    }

    @Bean
    public Gson createGson() {
        long startTime = System.currentTimeMillis();
        Gson gson = new Gson();
        log.info("time taken to intialize gson is {}", (System.currentTimeMillis() - startTime));
        return gson;
    }

}
