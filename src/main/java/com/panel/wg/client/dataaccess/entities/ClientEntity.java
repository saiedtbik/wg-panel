package com.panel.wg.client.dataaccess.entities;

import com.panel.wg.client.domain.valueObjects.ClientStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "t_client")
public class ClientEntity {
    @Id
    String clientId;

    String clientName;
    boolean enabled;
    String address;
    String publicKey;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime latestHandshakeAt;
    ClientStatus status;
    @OneToMany
    List<TrafficEntity> trafficList;

    @OneToOne
    TrafficEntity currentTraffic;
}
