package com.MicroServiceProject.OrderService.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroServiceProject.OrderService.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
}
