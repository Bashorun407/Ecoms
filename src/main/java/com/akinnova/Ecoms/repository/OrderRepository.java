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
public interface OrderRepository extends JpaRepository<OrderEntity, UUID>, OrderRepositoryExtension {

    @Query("SELECT o FROM OrderEntity o JOIN o.user u WHERE u.id =: userId")
    Optional<List<OrderEntity>> findByUserId(@Param("userId") UUID userId);
}
