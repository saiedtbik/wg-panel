package com.panel.wg.client.dataaccess.entities;

import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_traffic")
public class TrafficEntity {
    @Id
    Long id;

    @ManyToOne
    ClientEntity client;

    Long capacity;

    LocalDate expirationDate;

    Long transferRx;

    Long transferTx;

    TrafficStatus status;

    LocalDateTime createAt;

    Long createdBy;
}
