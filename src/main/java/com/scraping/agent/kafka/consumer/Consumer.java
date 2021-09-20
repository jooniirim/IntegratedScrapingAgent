package com.scraping.agent.kafka.consumer;

import com.scraping.agent.dto.ErrorMessageDto;
import com.scraping.agent.job.JobLauncherService;
import com.scraping.agent.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @Autowired
    JobLauncherService jobLauncherService;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    JsonUtil jsonUtil;

    @KafkaListener(topics = "${kafka.html.scraping.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, String> consumerRecord) throws ParseException {
        try {
            String topicMessage = consumerRecord.value();
            JSONObject message = jsonUtil.jsonParser(topicMessage);
            String agentType = getType(message);
            log.info("작업 시작 : Topic Consume / Type 구분(HTML or API)");
            jobLauncherService.executeJob(agentType, message);
        } catch (Exception e) {
            ErrorMessageDto errorMessageDto = ErrorMessageDto.builder().build();
            kafkaTemplate.send("alert.agent.job.error", errorMessageDto);
        }
    }

    public String getType(JSONObject jsonObject) {
        String type = jsonObject.get("type").toString();
        return type;
    }

    public void loggingAgentJobStartMessage(JSONObject message) {


    }
}
