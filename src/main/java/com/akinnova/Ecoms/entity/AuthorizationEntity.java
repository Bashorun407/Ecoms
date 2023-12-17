package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "authorization")
public class AuthorizationEntity implements Serializable {

    @Id
    private UUID id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    private boolean authorized;
    @CreationTimestamp
    private LocalDateTime time;
    private String message;
    private String error;

}
