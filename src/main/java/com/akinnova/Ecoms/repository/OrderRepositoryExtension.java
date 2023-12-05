package com.akinnova.Ecoms.repository;

import com.akinnova.Ecoms.dto.requestDto.NewOrder;
import com.akinnova.Ecoms.entity.OrderEntity;

import java.util.Optional;

public interface OrderRepositoryExtension {
    Optional<OrderEntity> insert(NewOrder m);
}
