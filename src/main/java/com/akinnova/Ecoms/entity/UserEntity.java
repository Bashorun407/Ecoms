package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user")
public class UserEntity extends BaseInfo implements Serializable {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String email;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "active_status")
    private boolean activeStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private CartEntity cart;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
//    private List<CardEntity> cards;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
//    private List<OrderEntity> orders;

}
