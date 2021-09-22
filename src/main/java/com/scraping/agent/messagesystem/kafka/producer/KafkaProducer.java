package com.scraping.agent.messagesystem.kafka.producer;

import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.dto.ErrorMessageDto;
import com.scraping.agent.messagesystem.Producer;
import com.scraping.agent.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Qualifier("kafkaConsumer")
public class KafkaProducer implements Producer<Map, ErrorMessageDto> {

    @Autowired
    KafkaTemplate kafkaTemplate;

    private final String requestLogTopic = "logging.agent.job.request";
    private final String completeLogTopic = "logging.agent.job.complete";
    private final String accountErrorTopic = "alert.agent.account.error";
    private final String jobErrorTopic = "alert.agent.job.error";

    @Override
    public void requestLogSend(Map map) {
        kafkaTemplate.send(requestLogTopic, map);
    }

    @Override
    public void completeLogSend(Map map) {
        kafkaTemplate.send(completeLogTopic, map);
    }

    @Override
    public void accountErrorLogSend(Map map) {
        ErrorMessageDto errorMessageDto = ErrorMessageDto.builder()
                .agentId(map.get("workId").toString())
                .errorTime("에러 발생 timestamp")
                .errorType("계정 에러")
                .agentType("API or HTML")
                .build();
        kafkaTemplate.send(accountErrorTopic, map);
    }

    @Override
    public void errorLogSend(Map map) {
        ErrorMessageDto errorMessageDto = ErrorMessageDto.builder()
                .agentId(map.get("workId").toString())
                .errorTime("에러 발생 timestamp")
                .errorType("작업 실행 중 에러")
                .agentType("API or HTML")
                .build();
        kafkaTemplate.send(jobErrorTopic, map);
    }
}
