package com.panel.wg.client.applicationservice.mapper;

import com.github.mfathi91.time.PersianDate;
import com.panel.wg.client.dataaccess.entities.TrafficEntity;
import com.panel.wg.client.domain.dtoes.TrafficDto;
import com.panel.wg.client.domain.entities.Traffic;
import com.panel.wg.common.domain.tools.validators.Validator;


public class TrafficMapper {

    public static Traffic toTraffic(TrafficEntity entity) {
        if (entity == null) {
            return null;
        }
        return Traffic.builder()
                .id(entity.getId())
                .capacity(entity.getCapacity())
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
                .capacity(traffic.getCapacity())
                .expirationDate(PersianDate.fromGregorian(traffic.getExpirationDate()).format(Validator.DATE_TIME_FORMATTER))
                .status(traffic.getStatus())
                .createAt(traffic.getCreateAt())
                .transferRx(traffic.getTransferRx())
                .transferTx(traffic.getTransferTx())
                .build();
        return dto;
    }

}
