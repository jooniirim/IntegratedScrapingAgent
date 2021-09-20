package com.scraping.agent.job.impl;

import com.scraping.agent.job.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class HtmlJobServiceImpl implements JobService {
    @Override
    public String getAgentType() {
        return "HTML";
    }

    @Override
    public boolean doJob(Map param) {
        try {
            log.info("Web Scraping Worker 호출");
            return true;
        }catch (Exception e){
            log.info("Web Scraping Worker 호출 에러");
            return false;
        }
    }


}
