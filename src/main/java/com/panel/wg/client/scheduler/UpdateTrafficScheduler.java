package com.panel.wg.client.scheduler;

import com.panel.wg.client.applicationservice.commnadHandlers.UpdateActiveClientsTransferTrafficHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateTrafficScheduler {
    private final UpdateActiveClientsTransferTrafficHandler updateActiveClientsTransferTrafficHandler;

    @Scheduled(cron = "0 * 19 * * ?")
    public void scheduleTask() {
        updateActiveClientsTransferTrafficHandler.handle();
    }
}
