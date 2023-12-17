package com.panel.wg.client.domain.dtoes;

import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TrafficDto(
    String capacity,
    String expirationDate,
    String transferRx,
    String transferTx,
    TrafficStatus status,
    String createAt
){
}
