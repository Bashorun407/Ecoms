package com.akinnova.Ecoms.repository;

import com.akinnova.Ecoms.dto.requestDto.NewOrder;
import com.akinnova.Ecoms.entity.CartEntity;
import com.akinnova.Ecoms.entity.ItemEntity;
import com.akinnova.Ecoms.entity.OrderEntity;
import com.akinnova.Ecoms.entity.OrderItemEntity;
import com.akinnova.Ecoms.enums.StatusEnum;
import com.akinnova.Ecoms.exception.ApiException;
import com.akinnova.Ecoms.service.ItemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExtension{

    @PersistenceContext
    private EntityManager entityManager;

    private ItemRepository itemRepository;
    private AddressRepository addressRepository;
    private CartRepository cartRepository;
    private OrderItemRepository orderItemRepository;
    private ItemService itemService;


    public OrderRepositoryImpl(EntityManager entityManager, ItemRepository itemRepository, AddressRepository addressRepository,
                               CartRepository cartRepository, OrderItemRepository orderItemRepository, ItemService itemService) {
        this.entityManager = entityManager;
        this.itemRepository = itemRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemService = itemService;
    }


    @Override
    public Optional<OrderEntity> insert(NewOrder m) {
        // Items are already in cart and saved in db when user places the order
        // Here, you can also populate the other Order details like address, card etc.

        List<ItemEntity> dbItems = itemRepository.findByUserId(m.getUser().getId()).stream().toList();
        List<ItemEntity> items = StreamSupport.stream(dbItems.spliterator(), false).collect(toList());

        if (items.size() < 1) {
//            throw new ResourceNotFoundException(String
//                    .format("There is no item found in customer's (ID: %s) cart.", m.getCustomerId()));

            throw new ApiException("There is no item found in customer's (ID: %s) cart.");
        }
        BigDecimal total = BigDecimal.ZERO;
        for (ItemEntity i : items) {
            total = (BigDecimal.valueOf(i.getQuantity()).multiply(i.getPrice())).add(total);
        }
        Timestamp orderDate = Timestamp.from(Instant.now());

        entityManager.createNativeQuery("""
        INSERT INTO OrderEntity (address_id, card_id, customer_id, order_date, total, status)
        VALUES(?, ?, ?, ?, ?, ?)
        """)
                .setParameter(1, m.getAddress().getId())
                .setParameter(2, m.getCard().getId())
                .setParameter(3, m.getUser().getId())
                .setParameter(4, orderDate)
                .setParameter(5, total)
                .setParameter(6, StatusEnum.NOT_DELIVERED)
                .executeUpdate();
        Optional<CartEntity> oCart = cartRepository.findByUserId(m.getUser().getId());

        CartEntity cart = oCart.orElseThrow(() -> new ApiException(String.format("Cart not found for given customer (ID: %s)", m.getUser().getId())));
        itemRepository.deleteCartItemJoinById(cart.getItems().stream().map(i -> i.getId()).collect(toList()), cart.getId());

        OrderEntity entity = (OrderEntity) entityManager.createNativeQuery("""
        SELECT o.* FROM OrderEntity o WHERE o.customer_id = ? AND o.order_date >= ?
        """, OrderEntity.class)
                .setParameter(1, m.getUser().getId())
                .setParameter(2, OffsetDateTime.ofInstant(orderDate.toInstant(), ZoneId.of("Z")).truncatedTo(
                        ChronoUnit.MICROS))
                .getSingleResult();

        orderItemRepository.saveAll(cart.getItems().stream().map(i -> new OrderItemEntity()
                .setOrderId(entity.getId()).setItemId(i.getId())).collect(toList()));

        return Optional.of(entity);
    }
}
