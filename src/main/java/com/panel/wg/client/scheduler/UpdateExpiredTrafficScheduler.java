package com.panel.wg.client.scheduler;

import com.panel.wg.client.applicationservice.commnadHandlers.UpdateExpiredTrafficHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateExpiredTrafficScheduler {
    private final UpdateExpiredTrafficHandler updateExpiredTrafficHandler;

//    @Scheduled(cron = "@daily")
    public void scheduleTask() {
        updateExpiredTrafficHandler.handle();
    }
}
