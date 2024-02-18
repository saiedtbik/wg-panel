package com.panel.wg.client.domain.entities;

import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Traffic {

    Long id;

    Long capacity;

    Long tempRx;

    Long tempTx;

    LocalDate expirationDate;

    Long transferRx;

    Long transferTx;

    TrafficStatus status;

    LocalDateTime createAt;

    Long createdBy;

    public void active() {
        status = TrafficStatus.ACTIVE;
    }

    public boolean hasCapacity() {
        return (transferTx == null || transferRx == null) || (transferTx + transferRx  < capacity);
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    public void setStatus(TrafficStatus trafficStatus) {
        this.status = trafficStatus;
    }
}
