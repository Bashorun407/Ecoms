package com.akinnova.Ecoms.repository;

import com.akinnova.Ecoms.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("SELECT o FROM OrderEntity o JOIN UserEntity u WHERE u.public_id =: userId")
    Optional<List<OrderEntity>> findByUserId(@Param("userId") UUID userId);
}
