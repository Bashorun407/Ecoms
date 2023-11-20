package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_item")
public class OrderItemEntity extends BaseInfo implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Column(name = "order_id")
    private OrderEntity order;
    @Column(name = "item_id")
    private ItemEntity item;

    /**
     * Override Equals and hashCode methods of this class
     */
    @Override
    public boolean equals(Object obj){

        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        return public_id != null && public_id.equals(((OrderItemEntity) obj).public_id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(public_id, order, item);
    }


}
