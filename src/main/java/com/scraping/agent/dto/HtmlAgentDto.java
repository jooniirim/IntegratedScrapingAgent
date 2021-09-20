package com.scraping.agent.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class HtmlAgentDto {
    private String type;                // api 인지 html인지
    private String jobId;               // 작업 고유 ID
    private String requestTime;         // 요청일시 YYYYmmDDHHMMssmm
    private String agentId;             // scarping worker의 id
    private String targetUrl;           // 스크래핑 대상 URL
    private String accountId;           // 스크래핑 대상 계정 정보

    @Builder
    public HtmlAgentDto(String type, String jobId, String requestTime, String agentId, String targetUrl, String accountId) {
        this.type = type;
        this.jobId = jobId;
        this.requestTime = requestTime;
        this.agentId = agentId;
        this.targetUrl = targetUrl;
        this.accountId = accountId;
    }
}
