package com.panel.wg.client.domain.entities;

import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Traffic {

    Long id;

    Long capacity;

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
      //  return transferRx + transferTx < capacity;
        return transferRx  < capacity;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    public void setStatus(TrafficStatus trafficStatus) {
        this.status = trafficStatus;
    }
}
