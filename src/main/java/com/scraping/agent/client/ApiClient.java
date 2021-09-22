package com.scraping.agent.client;

import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.messagesystem.Producer;
import com.scraping.agent.service.impl.ApiServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Map;

/**
 * 외부 REST API 호출 Client 클래스
 */
@Component
@Slf4j
@Retryable
@Service
@AllArgsConstructor
public class ApiClient implements Client<ApiAgentDto>{
    @Autowired
    ApiServiceImpl apiServiceImpl;

    @Autowired
    @Qualifier("kafkaProducer")
    private Producer producer;

    /**
     * 외부 REST API 호출 메서드
     * @param apiAgentDto
     * @throws Exception
     */
    @Override
    public void callExternal(ApiAgentDto apiAgentDto) throws Exception{
        // TODO 여러 Api 스펙에 대응해서 조립할 방법 필요(querystring 생성 시)
        try {
            String apiUrl = apiAgentDto.getApiUrl();

            log.info("ApiClient - restApiCall ::::: 1. API code 에 따른 API Key 값 가져오기");
            String apiKey = apiServiceImpl.getApiKey(apiAgentDto.getApiCode());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", apiKey);
            HttpEntity<String> request = new HttpEntity<>(httpHeaders);

            log.info("ApiClient - restApiCall ::::: 2. 외부 REST API 호출");
            ResponseEntity<Map> apiCall = (ResponseEntity<Map>) apiServiceImpl.apiGetCall(apiUrl, request, Map.class);
            Map<String, Object> result = apiCall.getBody();

            log.info("ApiClient - restApiCall ::::: 3. 외부 REST API 호출 결과 값 DB 저장");
            log.info("ApiClient - restApiCall ::::: 4. 작업 완료 queue");
        } catch (AuthenticationException authenticationException) {
            log.info("ApiClient - restApiCall ::::: 인증 에러 Alert queue");
            producer.errorLogSend(apiAgentDto);
        }
    }
}
