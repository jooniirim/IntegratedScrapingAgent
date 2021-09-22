package com.scraping.agent.job;

import com.scraping.agent.messagesystem.Producer;
import com.scraping.agent.util.ConvertUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Request 에 따라 Job Launch 클래스
 */
@Slf4j
@Service
@AllArgsConstructor
public class JobLauncherService {

    @Autowired
    ConvertUtil convertUtil;

    @Autowired
    @Qualifier("kafkaProducer")
    private Producer producer;

    @Autowired
    private JobSerivceFactory jobServiceFactory;

    /**
     * Request Type 구분 및 Json Data 전달, Job Execute 메소드
     * @param agentType
     * @param requestJsonData
     */
    public void executeJob(String agentType, JSONObject requestJsonData) {
        try {
            log.info("JobLauncherService - executeJob ::::: 1. Type 에 따라 구동 Agent Job 지정 및 Data 전달");
            Map<String, Object> requestData = convertUtil.convertMapToJson(requestJsonData);
            log.info("JobLauncherService - executeJob ::::: 2. doJob 호출");
            jobServiceFactory.getService(agentType).doJob(requestData);
        } catch (Exception e) {
            log.info("JobLauncherService - error ::::: Agent 요청 호출 에러");
            log.info("JobLauncherService - error ::::: 에러 Alert queue");
            producer.errorLogSend(requestJsonData);
        }

    }

}
