package com.panel.wg.client.domain.dtoes;

import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record TrafficDto(
    Long capacity,
    String expirationDate,
    Long transferRx,
    Long transferTx,
    TrafficStatus status,
    LocalDateTime createAt
){
}
