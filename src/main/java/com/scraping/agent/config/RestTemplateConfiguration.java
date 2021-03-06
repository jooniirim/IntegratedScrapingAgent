package com.scraping.agent.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * REST API 호출 위한 RestTemplate 설정 클래스
 */
@Slf4j
@EnableRetry
@Configuration
public class RestTemplateConfiguration {
    @Value("${resttemplate.factory.read-timeout}")
    private int READ_TIMEOUT;

    @Value("${resttemplate.factory.connect-timeout}")
    private int CONNECT_TIMEOUT;

    @Value("${resttemplate.httpclient.max-conn-total}")
    private int MAX_CONN_TOTAL;

    @Value("${resttemplate.httpclient.max-conn-per-route}")
    private int MAX_CONN_PER_ROUTE;

    @Value("${resttemplate.retry.attempts}")
    private int MAX_RETRY_ATTEMPTS;

    /**
     * 실패시 재시도 가능하도록 설정
     * @return HTTP 통신 간 Timeout 설정 및 Connection Pool 설정, 재시도 설정된 Resttemplate
     */
    @Bean
    public RestTemplate retryableRestTemplate(){
        
        HttpComponentsClientHttpRequestFactory factory
                = new HttpComponentsClientHttpRequestFactory();

        factory.setReadTimeout(READ_TIMEOUT);
        factory.setConnectTimeout(CONNECT_TIMEOUT);

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE).build();
        factory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(factory){
            @Override
            @Retryable(
                    value = RestClientException.class,
                    maxAttempts = 3,
                    backoff = @Backoff(delay = 1000))
            public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType)
                    throws RestClientException {
                return super.exchange(url, method, requestEntity, responseType);
            }

            @Recover
            public <T> ResponseEntity<String> exchangeRecover(RestClientException e) {
                return ResponseEntity.badRequest().body("재시도 실패");
            }
        };

        return restTemplate;

    }
}
