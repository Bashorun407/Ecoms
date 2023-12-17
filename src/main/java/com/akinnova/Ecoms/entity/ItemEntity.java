package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity extends BaseInfo implements Serializable {

    private static final Long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    private BigDecimal price;
    private int quantity;

    @ManyToMany(mappedBy = "items")
    private Set<CartEntity> carts;

    @ManyToMany(mappedBy = "items")
    private Set<OrderEntity> orders;

    /**
     * Override the equals and hashCode methods of this class
     * @param obj an object of any class that will be compared with an object of this class
     * @return returns a boolean value
     */

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        return id != null && id.equals(((ItemEntity) obj).id);
    }

    /**
     * Helper method to override hash value of this class
     * @return returns integer value
     */
    @Override
    public int hashCode(){
        return Objects.hash(product, quantity, price);
    }

}
