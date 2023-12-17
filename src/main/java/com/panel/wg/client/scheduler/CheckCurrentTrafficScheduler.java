package com.panel.wg.client.scheduler;

import com.panel.wg.client.applicationservice.commnadHandlers.CheckTrafficHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CheckCurrentTrafficScheduler {
    private final CheckTrafficHandler checkTrafficHandler;

//    @Scheduled(initialDelay = 60000, fixedRate = 10000)
    public void scheduleTask() {
        checkTrafficHandler.handle();
    }
}
