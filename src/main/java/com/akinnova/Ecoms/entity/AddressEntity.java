package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "address")
public class AddressEntity implements Serializable {

    @Id
    private UUID id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String residency;
    private String number;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private String pinCode;

}
