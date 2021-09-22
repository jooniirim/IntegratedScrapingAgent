package com.scraping.agent.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobLauncherServiceTest {
    @Autowired
    private Mock mock;

    @Test
    public void executeJobTest(){
        Map<String, Object> testData = new HashMap<>();
        testData.put("type", "API");
        testData.put("jobId", "API");
        testData.put("requestTime", LocalDateTime.now());
        testData.put("apiCode", "KCDTEST");
        testData.put("apiUrl", "http://localhost:8080/test");
    }
}
