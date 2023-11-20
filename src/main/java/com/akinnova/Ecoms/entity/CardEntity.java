package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "card")
public class CardEntity extends BaseInfo implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String number;
    private String expires;
    private String cvv;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "public_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card", orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();

    /**
     * helper methods
     * @param order an object of the order class
     */

    public void addOrder(OrderEntity order){
        this.orders.add(order);
        order.setCard(this);
    }

    /**
     * Helper method to remove an order
     * @param order an object of the order class
     */
    public void removeOrder(OrderEntity order){
        order.setCard(null);
        this.orders.remove(order);
    }

    /**
     * Helper method to remove multiple orders
     */
    public void removeOrders(){
        Iterator<OrderEntity> iterator = this.orders.iterator();

        while(iterator.hasNext()){
            OrderEntity order = iterator.next();
            order.setCard(null);
            iterator.remove();
        }
    }

}
