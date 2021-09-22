package com.scraping.agent.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class ApiAgentDto {
    private String type;                // api 인지 html인지
    private String jobId;               // 작업 고유 ID
    private String requestTime;         // 요청일시 YYYYmmDDHHMMssmm
    //API 정보 관련
    private String apiCode;             // 외부 API 소속 Code
    private String apiUrl;              // 호출 URL

    @Builder
    public ApiAgentDto(String type, String jobId, String requestTime, String apiCode, String apiUrl) {
        this.type = type;
        this.jobId = jobId;
        this.requestTime = requestTime;
        this.apiCode = apiCode;
        this.apiUrl = apiUrl;
    }
}
