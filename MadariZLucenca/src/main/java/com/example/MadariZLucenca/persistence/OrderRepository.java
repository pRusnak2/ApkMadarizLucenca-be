package com.example.MadariZLucenca.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomerCustomerId(Long customerId);

    List<OrderEntity> findByFoods_RestaurantId(Long restaurantId);

}
