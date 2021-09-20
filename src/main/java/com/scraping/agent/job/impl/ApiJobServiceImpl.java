package com.scraping.agent.job.impl;

import com.scraping.agent.client.ApiClient;
import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.dto.ErrorMessageDto;
import com.scraping.agent.job.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ApiJobServiceImpl implements JobService {
    @Autowired
    private ApiClient apiClient;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public String getAgentType() {
        return "API";
    }

    @Override
    public void doJob(Map param) {
        try {

            // TODO map에서 get할 때 예외 처리 필요
            ApiAgentDto apiAgentDto
                    = ApiAgentDto
                    .builder()
                    //필요한 값들 build
                    .build();
            // job 개시 로깅 queue
            kafkaTemplate.send("logging.agent.job.request", apiAgentDto);

            Map<String, Object> apiCallResult = apiClient.restApiCall(apiAgentDto);

            // TODO apiCallResult 결과 값 DB 저장

        } catch (Exception e) {
            log.info("REST API 호출 에러");
            ErrorMessageDto errorMessageDto = ErrorMessageDto.builder()
                    .agentId(param.get("agentId").toString())
                    .errorTime("에러 발생 timestamp")
                    .agentType("API")
                    .build();
            kafkaTemplate.send("alert.agent.job.error", errorMessageDto);
        }
    }

}

