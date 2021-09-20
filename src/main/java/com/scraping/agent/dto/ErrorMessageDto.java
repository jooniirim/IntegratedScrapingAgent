package com.scraping.agent.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
public class ErrorMessageDto {
    String agentType;
    String workId;
    String agentId;
    String errorTime;
    String errorType;

    @Builder
    public ErrorMessageDto(String agentType, String workId, String agentId, String errorTime, String errorType) {
        this.agentType = agentType;
        this.workId = workId;
        this.agentId = agentId;
        this.errorTime = errorTime;
        this.errorType = errorType;
    }
}
