package com.akinnova.Ecoms.entity;

import com.akinnova.Ecoms.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order")
public class OrderEntity extends BaseInfo implements Serializable {

    private static final Long serialVersionUID = 1L;

    private BigDecimal total;
    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "public_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "public_id")
    private PaymentEntity payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id", referencedColumnName = "public_id")
    private ShipmentEntity shipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", referencedColumnName = "public_id")
    private CardEntity card;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "orders_items",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "public_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "public_id")
    )
    private Set<ItemEntity> items;

    @OneToOne(mappedBy = "order")
    private AuthorizationEntity authorization;

    /**
     * Overriding the equals and hashCode method
     * @param obj an object of any class that will be compared an object of any class
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        return public_id != null && public_id.equals(((OrderEntity) obj).public_id);
    }

    /**
     * Overriding the hashCode method of this class
     * @return returns an integer value
     */
    @Override
    public int hashCode(){
        return Objects.hashCode(user);
    }
}
