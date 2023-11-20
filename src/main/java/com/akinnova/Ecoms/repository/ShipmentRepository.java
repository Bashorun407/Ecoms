package com.akinnova.Ecoms.repository;

import com.akinnova.Ecoms.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentEntity, UUID> {

}
