package com.panel.wg.client.dataaccess.repositories;

import com.panel.wg.client.dataaccess.entities.ClientEntity;
import com.panel.wg.user.domain.dtoes.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, String> {

    @Query(value = "select c from ClientEntity c inner join c.userEntity u where u.apiKey=:username")
    Optional<ClientEntity> findClientUserInfo(@Param("username") String username);
}
