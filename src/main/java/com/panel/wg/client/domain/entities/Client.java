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
    Traffic currentTraffic;
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

    public void changeTrafficIfNeeded() {
        if (!currentTraffic.hasCapacity()) {
            changeCurrentTrafficFor(TrafficStatus.NO_CAPACITY);
        } else if (currentTraffic.isExpired()) {
            changeCurrentTrafficFor(TrafficStatus.EXPIRED);
        }
    }

    private void  changeCurrentTrafficFor(TrafficStatus trafficStatus) {
        currentTraffic.setStatus(trafficStatus);
        Traffic traffic = trafficList.stream()
                .filter(t -> t.status.equals(TrafficStatus.CREATED) && t != currentTraffic)
                .sorted(Comparator.comparing(t -> t.createAt))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NO_TRAFFIC));
        traffic.status = TrafficStatus.ACTIVE;
        this.currentTraffic = traffic;
    }

    public void setCurrentTraffic() {
        Traffic traffic = trafficList.stream()
                .filter(t -> t.status.equals(TrafficStatus.CREATED))
                .sorted(Comparator.comparing(t -> t.createAt))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NO_TRAFFIC));
        traffic.status = TrafficStatus.ACTIVE;
        this.currentTraffic = traffic;
    }

    public void updateCurrentTrafficTransfer(Long updatedTransferRx, Long updatedTransferTx) {
        currentTraffic.transferTx = updatedTransferTx;
        currentTraffic.transferRx = updatedTransferRx;

    }

    public void setExpiredOutDatedTraffic() {
        trafficList.stream()
                .filter(t -> t.status != TrafficStatus.EXPIRED && t.status != TrafficStatus.NO_CAPACITY)
                .filter(t -> t.expirationDate.isBefore(LocalDate.now()))
                .forEach(t -> t.status = TrafficStatus.EXPIRED);
    }

}
