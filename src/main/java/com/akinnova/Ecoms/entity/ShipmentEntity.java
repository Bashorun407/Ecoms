package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "shipment")
public class ShipmentEntity extends BaseInfo {

    private static final Long serialVersionUID = 1L;

    @Column(name = "estimated_delivery_date")
    private LocalDateTime estDeliveryDate;
    private String carrier;

}
