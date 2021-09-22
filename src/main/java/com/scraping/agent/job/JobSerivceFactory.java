package com.scraping.agent.job;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JobSerivceFactory {

    private final Map<String, JobService> jobServices = new HashMap<>();

    public JobSerivceFactory(List<JobService> jobServices){
        if (CollectionUtils.isEmpty(jobServices)) {
            throw new IllegalStateException("Job이 존재하지 않음");
        }

        log.info("JobServiceFactory ::::: JobService 를 구현체인 Bean 들을 Agent 타입별로 Map 에 담음");
        for(JobService jobService : jobServices){
            this.jobServices.put(jobService.getAgentType(), jobService);
        }
    }

    public JobService getService(String agentType) {
        log.info("JobServiceFactory ::::: Agent 타입에 맞는 Service Bean 리턴");
        return jobServices.get(agentType);
    }

}
