package com.scraping.agent.job;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Request 타입에 따라 JobService 구현체 리턴 Factory 클래스
 */
@Slf4j
@Component
public class JobSerivceFactory {

    private final Map<String, JobService> jobServices = new HashMap<>();

    /**
     * JobService 구현체들을 Type 별로 Map에 put 하는 메소드
     * @param jobServices
     */
    public JobSerivceFactory(List<JobService> jobServices){
        if (CollectionUtils.isEmpty(jobServices)) {
            throw new IllegalStateException("Job이 존재하지 않음");
        }

        log.info("JobServiceFactory ::::: JobService 를 구현체인 Bean 들을 Agent 타입별로 Map 에 담음");
        for(JobService jobService : jobServices){
            this.jobServices.put(jobService.getAgentType(), jobService);
        }
    }

    /**
     * JobService 상속 구현체의 Type 확인하는 메소드
     * @param agentType
     * @return Type에 맞는 Jobservice 구현체 리턴
     */
    public JobService getService(String agentType) {
        log.info("JobServiceFactory ::::: Agent 타입에 맞는 Service Bean 리턴");
        return jobServices.get(agentType);
    }

}
