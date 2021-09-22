package com.scraping.agent.job.impl;

import com.scraping.agent.client.ApiClient;
import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.job.JobService;
import com.scraping.agent.messagesystem.Producer;
import com.scraping.agent.util.ConvertUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * JobSerivce 구현체 : REST API Scraping Job
 */
@Slf4j
@Service
@AllArgsConstructor
public class ApiJobServiceImpl implements JobService {
    @Autowired
    private ApiClient apiClient;

    @Autowired
    @Qualifier("kafkaProducer")
    private Producer producer;

    @Override
    public String getAgentType() {
        return "API";
    }

    /**
     * Job 시작 로깅 및 외부 REST API 호출 메소드
     * @param param
     */
    @Override
    public void doJob(Map param) {
        try {
            log.info("API - doJob ::::: 1. Map 에서 ApiAgentDto 로 변환");
            ApiAgentDto apiAgentDto = (ApiAgentDto) ConvertUtil.convertMapToObject(param, ApiAgentDto.class);
            log.info("API - doJob ::::: 2. 호출 대상 외부 Rest API 호출");
            apiClient.callExternal(apiAgentDto);
        } catch (Exception e) {
            log.info("API - doJob ::::: 외부 Rest API 호출 에러");
            log.info("API - doJob ::::: 에러 alert queue");
            producer.errorLogSend(param);
        }
    }

}

