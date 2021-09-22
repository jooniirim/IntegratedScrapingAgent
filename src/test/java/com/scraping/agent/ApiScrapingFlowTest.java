package com.scraping.agent;

import com.scraping.agent.job.JobLauncherService;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiScrapingFlowTest {

    @Autowired
    JobLauncherService jobLauncherService;

    @Test
    public void testApiJobRequest() throws Exception{


        //jobLauncherService.executeJob("API", );
    }
}
