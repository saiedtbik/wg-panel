package com.panel.wg.client.dataaccess.entities;

import com.panel.wg.client.domain.valueObjects.ClientStatus;
import com.panel.wg.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "t_client")
public class ClientEntity {
    @Id
    String clientId;
    String clientName;
    String address;
    String publicKey;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime latestHandshakeAt;

    @Enumerated(EnumType.STRING)
    ClientStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_Id")
    List<TrafficEntity> trafficList = new ArrayList<>();

    @OneToOne
    TrafficEntity currentTraffic;

    @OneToOne
    UserEntity userEntity;
}
