package com.panel.wg.client.scheduler;

import com.panel.wg.client.applicationservice.commnadHandlers.CheckTrafficHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CheckCurrentTrafficScheduler {
    private final CheckTrafficHandler checkTrafficHandler;

    @Scheduled(fixedRate = 300000)
    public void scheduleTask() {
        checkTrafficHandler.handle();
    }
}
