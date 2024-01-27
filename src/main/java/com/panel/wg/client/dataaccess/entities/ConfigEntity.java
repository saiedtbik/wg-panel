package com.panel.wg.client.dataaccess.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "t_config")
public class ConfigEntity {
    @Id
    Long id;

    String url;
}
