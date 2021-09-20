package com.scraping.agent.job;

import com.scraping.agent.dto.ErrorMessageDto;
import com.scraping.agent.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class JobLauncherService {

    @Autowired
    ConvertUtil convertUtil;

    @Autowired
    KafkaTemplate kafkaTemplate;

    private final JobSerivceFactory jobServiceFactory;

    public JobLauncherService(JobSerivceFactory jobServiceFactory){
        this.jobServiceFactory = jobServiceFactory;
    }

    public void executeJob(String agentType, JSONObject requestDataJson) {
        try {
            log.info("Type 에 따라 구동 Agent Job 지정 및 Data 전달");
            Map<String, Object> requestData = convertUtil.convertMapToJson(requestDataJson);
            jobServiceFactory.getService(agentType).doJob(requestData);
        } catch (Exception e) {
            log.info("Agent 요청 호출 에러");
            ErrorMessageDto errorMessageDto = ErrorMessageDto.builder()
                    .build();
            kafkaTemplate.send("alert.agent.job.error", errorMessageDto);
        }

    }

}
