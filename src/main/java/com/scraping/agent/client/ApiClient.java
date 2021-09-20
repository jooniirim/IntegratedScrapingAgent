package com.scraping.agent.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.service.ApiService;
import com.scraping.agent.vo.ApiRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Retryable
@Component
public class ApiClient {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ApiService apiService;

    public JsonNode restApiCall(ApiAgentDto apiAgentDto){
        String apiUrl = apiAgentDto.getApiUrl();
        //호출 대상 API 마다 spec이 다르겠지만 header에 key 값 넣는 것은 공통일 것
        log.info("API code 에 따른 API Key 값 가져오기");
        String apiKey = apiService.getApiKey(apiAgentDto.getApiCode());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", apiKey);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<JsonNode> apiResult = restTemplate.exchange(apiUrl, HttpMethod.GET, request, JsonNode.class);

        //URL 조립하기
        //TODO 여러 Api 스펙에 대응해서 조립할 방법 없을까

        return apiResult.getBody();
    }

}
