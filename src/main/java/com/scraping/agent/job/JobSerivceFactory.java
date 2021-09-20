package com.scraping.agent.job;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JobSerivceFactory {

    private final Map<String, JobService> jobServices = new HashMap<>();

    public JobSerivceFactory(List<JobService> jobServices){
        if (CollectionUtils.isEmpty(jobServices)) {
            throw new IllegalStateException("Job이 존재하지 않음");
        }

        for(JobService jobService : jobServices){
            this.jobServices.put(jobService.getAgentType(), jobService);
        }
    }

    public JobService getService(String agentType) {
        return jobServices.get(agentType);
    }

}
