package com.panel.wg.client.dataaccess.repositories;

import com.panel.wg.client.dataaccess.entities.TrafficEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficJpaRepository extends JpaRepository<TrafficEntity, Long> {
}
