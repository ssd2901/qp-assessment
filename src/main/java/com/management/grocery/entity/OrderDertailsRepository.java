package com.management.grocery.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDertailsRepository extends JpaRepository<OrderDetails, Long>  {
}
