package com.scraping.agent.client;

import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/*
    API 호출 클라이언트
 */
@Slf4j
@Retryable
@Component
public class ApiClient {
    @Autowired
    private RestTemplate retryableRestTemplate;
    @Autowired
    ApiService apiService;

    public Map<String, Object> restApiCall(ApiAgentDto apiAgentDto) throws Exception{
        // TODO 여러 Api 스펙에 대응해서 조립할 방법 필요(querystring 생성 시)
        String apiUrl = apiAgentDto.getApiUrl();

        //호출 대상 API 마다 spec이 다르겠지만 header에 key 값 넣는 것은 공통일 것
        log.info("API code 에 따른 API Key 값 가져오기");
        String apiKey = apiService.getApiKey(apiAgentDto.getApiCode());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", apiKey);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        // retry 처리된 resttemplate 호출
        ResponseEntity<Map> apiCall = retryableRestTemplate.exchange(apiUrl, HttpMethod.GET, request, Map.class);

        return apiCall.getBody();
    }

}
