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

    Client client;

    Long capacity;

    LocalDate expirationDate;

    Long transferRx;

    Long transferTx;

    TrafficStatus status;

    LocalDateTime createAt;

    Long createdBy;

    public void active(){
        status = TrafficStatus.ACTIVE;
    }

}
