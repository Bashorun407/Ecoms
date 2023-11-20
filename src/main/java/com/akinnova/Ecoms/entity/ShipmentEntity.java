package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "shipment")
public class ShipmentEntity extends BaseInfo {

    private static final Long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;

    @Column(name = "estimated_delivery_date")
    private LocalDateTime estDeliveryDate;
    private String carrier;

}
