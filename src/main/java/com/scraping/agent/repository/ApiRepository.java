package com.scraping.agent.repository;

import com.scraping.agent.vo.ApiCodeVO;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository {

    public ApiCodeVO getApiCodeInfo(String apiCode);

}
