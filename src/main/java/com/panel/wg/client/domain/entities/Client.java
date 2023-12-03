package com.panel.wg.client.domain.entities;

import com.panel.wg.client.domain.exceptions.ClientError;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.user.domain.entities.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    String clientId;
    String clientName;
    String address;
    String publicKey;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime latestHandshakeAt;
    ClientStatus status;
    List<Traffic> trafficList = new ArrayList<>();
    User user;

    public void disableClient() {
        status = ClientStatus.DISABLED;
    }

    public void enableClient() {
        status = ClientStatus.ACTIVE;
    }

    public void addTraffic(Traffic traffic) {
        trafficList.add(traffic);
    }

    public boolean changeTrafficIfNeeded() {
        boolean changed = false;
        Optional<Traffic> currentTraffic = getCurrentTraffic();
        if (currentTraffic.isPresent()) {
            if (!currentTraffic.get().hasCapacity()) {
                changeCurrentTrafficFor(TrafficStatus.NO_CAPACITY);
                changed = true;
            } else if (currentTraffic.get().isExpired()) {
                changeCurrentTrafficFor(TrafficStatus.EXPIRED);
                changed = true;
            }
        }
        return changed;
    }

    private void changeCurrentTrafficFor(TrafficStatus trafficStatus) {
        Optional<Traffic> currentTraffic = getCurrentTraffic();
        if(currentTraffic.isPresent()) {
            currentTraffic.get().setStatus(trafficStatus);
            Traffic traffic = trafficList.stream()
                    .filter(t -> t.status.equals(TrafficStatus.CREATED) && t != currentTraffic.get())
                    .sorted(Comparator.comparing(t -> t.createAt))
                    .findFirst()
                    .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NO_TRAFFIC));
            traffic.status = TrafficStatus.ACTIVE;
        }
    }

    public void setActiveTraffic() {
        Traffic traffic = trafficList.stream()
                .filter(t -> t.status.equals(TrafficStatus.CREATED))
                .sorted(Comparator.comparing(t -> t.createAt))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NO_TRAFFIC));
        traffic.status = TrafficStatus.ACTIVE;

    }

    public void updateCurrentTrafficTransfer(Long updatedTransferRx, Long updatedTransferTx) {
        Optional<Traffic> currentTraffic = getCurrentTraffic();
        if (currentTraffic.isPresent()) {
            currentTraffic.get().transferTx = updatedTransferTx;
            currentTraffic.get().transferRx = updatedTransferRx;
        }
    }

    public void setExpiredOutDatedTraffic() {
        trafficList.stream()
                .filter(t -> t.status != TrafficStatus.EXPIRED && t.status != TrafficStatus.NO_CAPACITY)
                .filter(t -> t.expirationDate.isBefore(LocalDate.now()))
                .forEach(t -> t.status = TrafficStatus.EXPIRED);
    }

    public Optional<Traffic> getCurrentTraffic() {
        return trafficList.stream()
                .filter(t -> t.status == TrafficStatus.ACTIVE)
                .findFirst();
    }

}
