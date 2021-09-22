package com.scraping.agent.messagesystem;

/**
 * Message System 별 설정 가능한 Producer 인터페이스
 * @param <T1>
 * @param <T2>
 */
public interface Producer<T1, T2> {
    void requestLogSend(T1 t);

    void completeLogSend(T1 t);

    void accountErrorLogSend(T1 t);

    void errorLogSend(T1 t);
}
