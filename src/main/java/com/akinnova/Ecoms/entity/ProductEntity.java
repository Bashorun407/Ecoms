package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class ProductEntity extends BaseInfo implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String name;
    private String description;
    private BigDecimal price;
    private int count;
    private String imageUrl;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinTable(
//            name = "product_tag",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private List<TagEntity> tags = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
//    private List<ItemEntity> items = new ArrayList<>();
//
//    /**
//     * Helper method to override to add item
//     * @param item an item to be removed from product
//     */
//    public void addItem(ItemEntity item){
//        this.items.add(item);
//        item.setProduct(this);
//    }
//
//    /**
//     * Helper method to remove an item from items
//     * @param item
//     */
//    public void removeItem(ItemEntity item){
//        item.setProduct(null);
//        this.items.remove(item);
//    }
//
//    /**
//     * Helper method to remove multiple items from items list
//     */
//    public void removeItems(){
//        Iterator<ItemEntity> iterator = this.items.iterator();
//
//        while(iterator.hasNext()){
//            ItemEntity item = iterator.next();
//            item.setProduct(null);
//            iterator.remove();
//        }
//    }
}
