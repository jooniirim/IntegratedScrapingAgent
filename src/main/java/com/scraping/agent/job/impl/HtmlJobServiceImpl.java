package com.scraping.agent.job.impl;

import com.scraping.agent.client.HtmlClient;
import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.dto.ErrorMessageDto;
import com.scraping.agent.dto.HtmlAgentDto;
import com.scraping.agent.job.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class HtmlJobServiceImpl implements JobService {
    @Override
    public String getAgentType() {
        return "HTML";
    }

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    HtmlClient htmlClient;

    @Override
    public void doJob(Map param) {
        try {

            HtmlAgentDto htmlAgentDto
                    = HtmlAgentDto.builder()
                        // 필요한 값들 build
                    .build();
            kafkaTemplate.send("logging.agent.job.request", htmlAgentDto);

            log.info("Web Scraping Worker 호출");

            //TODO 재시도 처리 필요
            htmlClient.rpcCall(htmlAgentDto);

        }catch (Exception e){
            log.info("Web Scraping Worker 호출 에러");
            ErrorMessageDto errorMessageDto = ErrorMessageDto.builder()
                    .build();
            kafkaTemplate.send("alert.agent.job.error", errorMessageDto);
        }
    }


}
