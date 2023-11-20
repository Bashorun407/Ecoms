package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class PaymentEntity extends BaseInfo implements Serializable {

    private static final Long serialVersionUID = 1L;

    private boolean authorized;
    private String message;
    @OneToOne(mappedBy = "payment")
    private OrderEntity order;

    /**
     * Overriding the equals method of this class
     * @param obj an object of any class that will be compared with an object of this class
     * @return returns a boolean value
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        return public_id != null && public_id.equals(((PaymentEntity) obj).public_id);
    }

    /**
     * Overriding the hashCode method of this class
     * @return returns an integer value
     */

    @Override
    public int hashCode(){
        return Objects.hashCode(authorized);
    }
}
