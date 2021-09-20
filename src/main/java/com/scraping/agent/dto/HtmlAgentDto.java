package com.scraping.agent.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class HtmlAgentDto {
    // HTML 스크래핑 관련
    private String type;                // api 인지 html인지
    private String requestTime;         // 요청일시 YYYYmmDDHHMMssmm
    private int retryCount;          // Queue 에서 주는게 맞을까??
    private String agentId;             // scarping worker의 id
    private String targetUrl;

    @Builder
    public HtmlAgentDto(String type, String requestTime, int retryCount, String agentId, String targetUrl) {
        this.type = type;
        this.requestTime = requestTime;
        this.retryCount = retryCount;
        this.agentId = agentId;
        this.targetUrl = targetUrl;
    }
}
