package com.panel.wg.client.dataaccess.repositories;

import com.panel.wg.client.dataaccess.entities.TrafficEntity;
import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficJpaRepository extends JpaRepository<TrafficEntity, Long> {
    List<TrafficEntity> findTrafficEntitiesByStatus(TrafficStatus status);
}
