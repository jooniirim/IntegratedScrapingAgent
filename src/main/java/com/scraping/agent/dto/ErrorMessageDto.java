package com.scraping.agent.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessageDto {
    private String agentType;
    private String workId;
    private String accountId;
    private String apiCode;
    private String agentId;
    private String errorTime;
    private String errorType;

    @Builder
    public ErrorMessageDto(String agentType, String workId, String accountId, String apiCode, String agentId, String errorTime, String errorType) {
        this.agentType = agentType;
        this.workId = workId;
        this.accountId = accountId;
        this.apiCode = apiCode;
        this.agentId = agentId;
        this.errorTime = errorTime;
        this.errorType = errorType;
    }
}
