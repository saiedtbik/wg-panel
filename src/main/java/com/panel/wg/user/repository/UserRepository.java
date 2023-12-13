package com.panel.wg.user.repository;


import com.panel.wg.user.domain.dtoes.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByApiKey(String apiKey);

    @Query(value = "select new com.panel.wg.user.domain.dtoes.UserDto(u.fullName, u.apiKey, u.enabled, c.clientId, c.status, u.createOn) from ClientEntity c inner join c.userEntity u where u.apiKey=:username")
    Optional<UserDto> findClientUserInfo(@Param("username") String username);

    @Query(value = "select new com.panel.wg.user.domain.dtoes.UserDto(u.fullName, u.apiKey, u.enabled, c.clientId, c.status, u.createOn) from ClientEntity c inner join c.userEntity u order by u.createOn asc ")
    List<UserDto> findAllClientUsersInfo();

    @Query(value = "select new com.panel.wg.user.domain.dtoes.UserDto(u.fullName, u.apiKey, u.enabled, c.clientId, c.status, u.createOn) from ClientEntity c inner join c.userEntity u where c.clientId=:clientId")
    Optional<UserDto> existsByClientId(@Param("clientId") String clientId);
}
