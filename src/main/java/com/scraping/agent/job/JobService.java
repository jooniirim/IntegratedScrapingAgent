package com.scraping.agent.job;

import java.util.Map;

public interface JobService {

    String getAgentType();
    void doJob(Map requestValue);

}
