package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart")
public class CartEntity extends BaseInfo implements Serializable {

    private static final Long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "cart_item",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private Set<ItemEntity> items = new LinkedHashSet<>();

    /**
     * override equals and hashCode methods of the class
     * @param obj This is an object of any class that will be compared with this class
     * @return returns a boolean value
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        return id != null && id.equals(((CartEntity) obj).id);
    }

    /**
     * Helper method to override the hashCode of this class
     * @return integer value
     */
    @Override
    public int hashCode(){
        return Objects.hash(userEntity);
    }

}
