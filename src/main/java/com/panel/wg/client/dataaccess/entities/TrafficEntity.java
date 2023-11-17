package com.panel.wg.client.dataaccess.entities;

import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_traffic")
public class TrafficEntity {
    @Id
    @GeneratedValue
    Long id;

    Long capacity;

    LocalDate expirationDate;

    Long transferRx;

    Long transferTx;

//    @Enumerated(EnumType.STRING)
    TrafficStatus status;

    LocalDateTime createAt;

    Long createdBy;

}
