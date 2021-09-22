package com.scraping.agent.job;

import java.util.Map;

/**
 * Request 에 대한 Job을 실행하는 Service 인터페이스
 */
public interface JobService {

    /**
     * Agent Type 리턴 메소드
     * @return String agent type
     */
    String getAgentType();

    /**
     * Job 실행 메소드
     * @param requestValue
     */
    void doJob(Map requestValue);

}
