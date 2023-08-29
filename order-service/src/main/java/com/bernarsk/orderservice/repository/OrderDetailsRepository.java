package com.bernarsk.orderservice.repository;

import com.bernarsk.orderservice.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
