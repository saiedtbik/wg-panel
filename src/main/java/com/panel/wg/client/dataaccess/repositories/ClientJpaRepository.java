package com.panel.wg.client.dataaccess.repositories;

import com.panel.wg.client.dataaccess.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientJpaRepository extends JpaRepository<ClientEntity, String> {
}
