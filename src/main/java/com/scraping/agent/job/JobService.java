package com.scraping.agent.job;

import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.dto.HtmlAgentDto;

import java.util.Map;

public interface JobService {

    String getAgentType();

    boolean doJob(Map requestValue);

}
