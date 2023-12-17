package com.akinnova.Ecoms.repository;

import com.akinnova.Ecoms.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    @Query("SELECT c FROM CartEntity c JOIN c.userEntity u WHERE u.id =: userId")
    Optional<CartEntity> findByUserId(@Param("userId") UUID userId);
}
