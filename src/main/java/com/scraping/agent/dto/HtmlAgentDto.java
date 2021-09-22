package com.scraping.agent.dto;

import lombok.*;

/**
 * HTML Scraping Request DTO
 */
@Data
@AllArgsConstructor
@Builder
public class HtmlAgentDto {
    private String type;                // api 인지 html인지
    private String jobId;               // 작업 고유 ID
    private String requestTime;         // 요청일시 datetime
    private String agentId;             // scraping worker의 id
    private String targetUrl;           // 스크래핑 대상 URL
    private String accountId;           // 스크래핑 대상 계정 정보
}
