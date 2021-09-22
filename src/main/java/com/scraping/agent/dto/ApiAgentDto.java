package com.scraping.agent.dto;

import lombok.*;

/**
 * REST API Scraping Request DTO
 */
@Data
@AllArgsConstructor
@Builder
public class ApiAgentDto {
    private String type;               
    private String jobId;              
    private String requestTime;        
    private String apiCode;             
    private String apiUrl;              
}
