package com.scraping.agent.messagesystem;

public interface Producer<T1, T2> {
    void requestLogSend(T1 t);

    void completeLogSend(T1 t);

    void accountErrorLogSend(T1 t);

    void errorLogSend(T1 t);
}
