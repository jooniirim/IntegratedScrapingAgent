package com.scraping.agent.service;

import com.scraping.agent.repository.impl.HtmlRepositoryImpl;
import com.scraping.agent.vo.HtmlAccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class HtmlService {
    @Autowired
    private HtmlRepositoryImpl htmlRepository;

    public Map<String, Object> getAccountInfo(String accountId) throws Exception{
        Map<String, Object> account = new HashMap<>();
        HtmlAccountVO accountInfo = htmlRepository.getHtmlAccountInfo(accountId);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        if("N".equals(accountInfo.getUseYN()) || formatedNow.compareTo(accountInfo.getExpireDate()) < 0){
            throw new AuthenticationException("사용 불가한 계정, 계정 정보 확인 필요");
        }else {
            account.put("accountId", accountInfo.getAccountId());
            account.put("accountPassword", accountInfo.getAccountPassword());
            return account;

        }

    }


}
