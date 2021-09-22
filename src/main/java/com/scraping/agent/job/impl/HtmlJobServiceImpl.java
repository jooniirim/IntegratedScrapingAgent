package com.scraping.agent.job.impl;

import com.scraping.agent.client.HtmlClient;
import com.scraping.agent.dto.HtmlAgentDto;
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
 * JobSerivce 구현체 : HTML Scraping Job
 */
@Slf4j
@Service
@AllArgsConstructor
public class HtmlJobServiceImpl implements JobService {
    @Override
    public String getAgentType() {
        return "HTML";
    }

    @Autowired
    private HtmlClient htmlClient;

    @Autowired
    @Qualifier("kafkaProducer")
    private Producer producer;

    /**
     * Job 시작 로깅 및 HTML Scraping work 호출 실행 메소드
     * @param param
     */
    @Override
    public void doJob(Map param) {
        try {
            log.info("HTML - doJob ::::: 1. Map 에서 ApiAgentDto 로 변환");
            HtmlAgentDto htmlAgentDto = (HtmlAgentDto) ConvertUtil.convertMapToObject(param, HtmlAgentDto.class);

            log.info("HTML - doJob ::::: 2. job 개시 로깅 queue");
            producer.requestLogSend(param);

            log.info("HTML - doJob ::::: 3. Web Scraping Worker 호출");
            htmlClient.callExternal(htmlAgentDto);

        } catch (Exception e){
            log.info("HTML - doJob ::::: Web Scraping Worker 호출 에러");
            log.info("HTML - doJob ::::: 에러 Alert queue");
            producer.errorLogSend(param);
        }
    }


}
