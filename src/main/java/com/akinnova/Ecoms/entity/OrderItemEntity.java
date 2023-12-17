package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private UUID orderId;
    @Column(name = "item_id")
    private UUID itemId;

    /**
     * Override Equals and hashCode methods of this class
     */
    @Override
    public boolean equals(Object obj){

        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        return id != null && id.equals(((OrderItemEntity) obj).id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, orderId, itemId);
    }

}
