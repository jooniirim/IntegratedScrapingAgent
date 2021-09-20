package com.scraping.agent.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiAgentDto {
    private String type;                // api 인지 html인지
    private String jobId;              // 작업 고유 ID
    private String requestTime;         // 요청일시 YYYYmmDDHHMMssmm
    private int retryCount;             // Queue 에서 주는게 맞을까??
    private String agentId;             // scarping worker의 id
    //API 정보 관련
    private String apiCode;             // 외부 API 소속 Code
    private String apiUrl;              // 호출 URL

    @Builder
    public ApiAgentDto(String type, String jobId, String requestTime, int retryCount, String agentId, String apiCode, String apiUrl) {
        this.type = type;
        this.jobId = jobId;
        this.requestTime = requestTime;
        this.retryCount = retryCount;
        this.agentId = agentId;
        this.apiCode = apiCode;
        this.apiUrl = apiUrl;
    }
}
