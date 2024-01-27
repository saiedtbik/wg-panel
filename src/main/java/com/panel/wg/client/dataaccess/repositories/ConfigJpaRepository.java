package com.panel.wg.client.dataaccess.repositories;

import com.panel.wg.client.dataaccess.entities.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigJpaRepository extends JpaRepository<ConfigEntity, Long> {
}
