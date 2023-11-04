package com.panel.wg.common.applicationservice;

public interface CommandHandler<T> {
    void handle(T command);
}
