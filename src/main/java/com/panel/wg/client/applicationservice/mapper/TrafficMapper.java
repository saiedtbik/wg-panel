package com.panel.wg.client.applicationservice.mapper;

import com.github.mfathi91.time.PersianDate;
import com.panel.wg.client.dataaccess.entities.TrafficEntity;
import com.panel.wg.client.domain.dtoes.TrafficDto;
import com.panel.wg.client.domain.entities.Traffic;
import com.panel.wg.common.domain.tools.validators.Validator;

import java.math.BigDecimal;


public class TrafficMapper {

    public static Traffic toTraffic(TrafficEntity entity) {
        if (entity == null) {
            return null;
        }
        return Traffic.builder()
                .id(entity.getId())
                .capacity(entity.getCapacity())
                .tempRx(entity.getTempRx())
                .tempTx(entity.getTempTx())
                .transferRx(entity.getTransferRx())
                .transferTx(entity.getTransferTx())
                .createdBy(entity.getCreatedBy())
                .createAt(entity.getCreateAt())
                .expirationDate(entity.getExpirationDate())
                .status(entity.getStatus())
                .build();
    }

    public static TrafficEntity toEntity(Traffic traffic) {
        if (traffic == null) {
            return null;
        }
        TrafficEntity trafficEntity = new TrafficEntity();
        trafficEntity.setId(traffic.getId());
        trafficEntity.setCapacity(traffic.getCapacity());
        trafficEntity.setTempTx(traffic.getTempTx());
        trafficEntity.setTempRx(traffic.getTempRx());
        trafficEntity.setTransferRx(traffic.getTransferRx());
        trafficEntity.setTransferTx(traffic.getTransferTx());
        trafficEntity.setCreatedBy(traffic.getCreatedBy());
        trafficEntity.setCreateAt(traffic.getCreateAt());
        trafficEntity.setExpirationDate(traffic.getExpirationDate());
        trafficEntity.setStatus(traffic.getStatus());

        return trafficEntity;
    }

    public static TrafficDto toDto(Traffic traffic) {
        if (traffic == null) {
            return null;
        }

        TrafficDto dto = TrafficDto.builder()
                .id(traffic.getId())
                .capacity(BigDecimal.valueOf(traffic.getCapacity()).divide(new BigDecimal("1000000000")).toString())
                .capacityView(traffic.getCapacity() == null ? null : " (GB) " + BigDecimal.valueOf(traffic.getCapacity()).divide(new BigDecimal("1000000000")))
                .expirationDateView(PersianDate.fromGregorian(traffic.getExpirationDate()).format(Validator.DATE_TIME_FORMATTER_With_LINE))
                .expirationDate(PersianDate.fromGregorian(traffic.getExpirationDate()).format(Validator.DATE_TIME_FORMATTER))
                .status(traffic.getStatus())
                .createAt(PersianDate.fromGregorian(traffic.getCreateAt().toLocalDate()).format(Validator.DATE_TIME_FORMATTER_With_LINE))
                .transferRx(traffic.getTransferRx() == null ? null : " (GB) " +BigDecimal.valueOf(traffic.getTransferRx()).divide(new BigDecimal("1000000000")))
                .transferTx(traffic.getTransferTx() == null ? null : " (GB) " +BigDecimal.valueOf(traffic.getTransferTx()).divide(new BigDecimal("1000000000")))
                .build();
        return dto;
    }

}
