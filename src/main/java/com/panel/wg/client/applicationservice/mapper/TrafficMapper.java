package com.panel.wg.client.applicationservice.mapper;

import com.panel.wg.client.dataaccess.entities.TrafficEntity;
import com.panel.wg.client.domain.entities.Traffic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


public class TrafficMapper {

    static Traffic toTraffic(TrafficEntity entity) {
        if(entity == null) {
            return null;
        }
        return Traffic.builder()
                .id(entity.getId())
                .client(ClientDataMapper.toClient(entity.getClient()))
                .capacity(entity.getCapacity())
                .transferRx(entity.getTransferRx())
                .transferTx(entity.getTransferTx())
                .createdBy(entity.getCreatedBy())
                .createAt(entity.getCreateAt())
                .expirationDate(entity.getExpirationDate())
                .build();
    }

    static TrafficEntity toEntity(Traffic traffic) {
        if(traffic == null) {
            return null;
        }
        TrafficEntity trafficEntity = new TrafficEntity();
        trafficEntity.setId(trafficEntity.getId());
        trafficEntity.setClient(ClientDataMapper.toEntity(traffic.getClient()));
        trafficEntity.setCapacity(trafficEntity.getCapacity());
        trafficEntity.setTransferRx(trafficEntity.getTransferRx());
        trafficEntity.setTransferTx(trafficEntity.getTransferTx());
        trafficEntity.setCreatedBy(trafficEntity.getCreatedBy());
        trafficEntity.setCreateAt(trafficEntity.getCreateAt());
        trafficEntity.setExpirationDate(trafficEntity.getExpirationDate());
        return trafficEntity;
    }

}
