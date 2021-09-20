package com.scraping.agent.job.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.scraping.agent.client.ApiClient;
import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.job.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ApiJobServiceImpl implements JobService {
    @Autowired
    private ApiClient apiClient;

    @Override
    public String getAgentType() {
        return "API";
    }

    @Override
    public boolean doJob(Map param) {
        try {

            // map에서 get할 때 예외 처리 필요
            ApiAgentDto apiAgentDto
                    = ApiAgentDto
                        .builder()
                            .agentId(param.get("agentId").toString())
                            .requestTime(param.get("requestTime").toString())
                            .retryCount(Integer.parseInt(param.get("retryCount").toString()))
                            .apiCode(param.get("apiCode").toString())
                            .apiUrl(param.get("apiUrl").toString())
                        .build();

            log.info("REST API 호출");
            JsonNode apiCallResult = apiClient.restApiCall(apiAgentDto);
            
            // TODO 결과 값 전달, 파일 저장과 ETL 호출하는 로직 호출

            return true;
        }catch (Exception e){
            log.info("REST API 호출 에러");
            return false;
        }
    }

}

