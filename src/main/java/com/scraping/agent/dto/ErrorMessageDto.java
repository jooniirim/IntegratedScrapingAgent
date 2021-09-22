package com.scraping.agent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Error Message Queue DTO
 */
@Data
@AllArgsConstructor
@Builder
public class ErrorMessageDto {
    private String type;           
    private String jobId;          
    private String accountId;      
    private String apiCode;        
    private String agentId;        
    private String errorTime;      
    private String errorType;      
}
