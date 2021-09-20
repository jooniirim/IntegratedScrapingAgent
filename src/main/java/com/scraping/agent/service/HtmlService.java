package com.scraping.agent.service;

import com.scraping.agent.repository.impl.HtmlRepositoryImpl;
import com.scraping.agent.vo.HtmlAccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class HtmlService {
    @Autowired
    private HtmlRepositoryImpl htmlRepository;

    public Map<String, Object> getAccountInfo(String accountId){
        Map<String, Object> account = new HashMap<>();
        HtmlAccountVO accountInfo = htmlRepository.getHtmlAccountInfo(accountId);

        account.put("accountId", accountInfo.getAccountId());
        account.put("accountPassword", accountInfo.getAccountPassword());

        //TODO useYN이 N이거나 expireDate가 지난 시점인 경우 오류 처리 필요

        return account;
    }

}
