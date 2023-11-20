package com.akinnova.Ecoms.repository;

import com.akinnova.Ecoms.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
    @Query(value = "SELECT i.* FROM CartEntity AS c, ItemEntity AS i, cart_item AS ci, UserEntity AS u WHERE u.public_id =: userId " +
            "AND  c.userEntity = u.public_id AND c.public_id = ci.cart_id AND i.public_id = ci.item_id", nativeQuery = true)
    Optional<ItemEntity> findByUserId(UUID userId);

    @Modifying
    @Query(value = "DELETE FROM cart_item AS ci WHERE ci.item_id in (:item_idList) AND ci.cart_id = :cartId ", nativeQuery = true)
    void deleteCartItemJoinById(@Param("item_idList") List<UUID> item_idList, @Param("cartId") UUID cartId);
}
