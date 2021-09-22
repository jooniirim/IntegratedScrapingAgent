package com.scraping.agent.exception;

/**
 * 인증 정보 오류 Exception
 */
class AuthenticationException extends Exception {
    /**
     * 인증 정보 사용 불가 혹은 만료 시 Exception
     * @param message
     */
    public AuthenticationException(String message) {
        super(message);
    }
}
