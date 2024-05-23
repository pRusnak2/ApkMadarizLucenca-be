package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private LoginRepository loginRepository;

    public Order createOrder(Long customerId, Order order) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Zákazník s daným ID neexistuje.");
        }

        CustomerEntity customer = optionalCustomer.get();
        List<FoodEntity> foods = order.getFoodIds().stream()
                .map(foodId -> foodRepository.findById(foodId).orElseThrow(() -> new IllegalArgumentException("Jedlo s ID " + foodId + " neexistuje.")))
                .collect(Collectors.toList());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customer);
        orderEntity.setStatus(order.getStatus());
        orderEntity.setOrderTime(new Timestamp(order.getOrderTime().getTime()));
        orderEntity.setDeliveryTime(new Timestamp(order.getDeliveryTime().getTime()));
        orderEntity.setFoods(foods);

        orderRepository.save(orderEntity);

        order.setOrderId(orderEntity.getOrderId());
        order.setFoodNames(foods.stream().map(FoodEntity::getName).collect(Collectors.toList())); // Pridajte tento riadok
        return order;
    }


    public Order orderById(Long id) {
        Optional<OrderEntity> opt = orderRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        OrderEntity entity = opt.get();
        Order objednavka = new Order();
        objednavka.setStatus(entity.getStatus());
        objednavka.setOrderId(entity.getOrderId());
        objednavka.setOrderTime(entity.getOrderTime());
        objednavka.setDeliveryTime(entity.getDeliveryTime());
        objednavka.setFoodIds(entity.getFoods().stream().map(FoodEntity::getFoodId).collect(Collectors.toList()));
        objednavka.setFoodNames(entity.getFoods().stream().map(FoodEntity::getName).collect(Collectors.toList())); // Pridajte tento riadok
        return objednavka;
    }

    public Long getCustomerIdByName(String userName) {

        Optional<CustomerEntity> optionalCustomer = Optional.ofNullable(loginRepository.findByUsername(userName).get().getCustomer());

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get().getCustomerId();
        } else {
            throw new IllegalArgumentException("Customer not found with username: " + userName);
        }
    }

    public Long getRestaurantIdByUsername(String username) {
        Optional<LoginEntity> optionalLogin = loginRepository.findByUsername(username);

        if (optionalLogin.isPresent()) {
            LoginEntity loginEntity = optionalLogin.get();
            RestaurantEntity restaurantEntity = loginEntity.getRestaurant();
            if (restaurantEntity != null) {
                return restaurantEntity.getRestaurantId();
            } else {
                throw new IllegalArgumentException("Restaurant not found for username: " + username);
            }
        } else {
            throw new IllegalArgumentException("Login not found for username: " + username);
        }
    }

    public List<Order> getOrdersForCustomer(Long customerId) {
        List<OrderEntity> orderEntities = orderRepository.findByCustomerCustomerId(customerId);
        return orderEntities.stream().map(entity -> {
            Order order = new Order();
            order.setOrderId(entity.getOrderId());
            order.setStatus(entity.getStatus());
            order.setOrderTime(entity.getOrderTime());
            order.setDeliveryTime(entity.getDeliveryTime());
            order.setFoodIds(entity.getFoods().stream().map(FoodEntity::getFoodId).collect(Collectors.toList()));
            order.setFoodNames(entity.getFoods().stream().map(FoodEntity::getName).collect(Collectors.toList())); // Pridajte tento riadok
            return order;
        }).collect(Collectors.toList());
    }

    public List<Order> getOrdersForRestaurant(Long restaurantId) {
        List<OrderEntity> orderEntities = orderRepository.findByFoods_RestaurantId(restaurantId);
        return orderEntities.stream().map(entity -> {
            Order order = new Order();
            order.setOrderId(entity.getOrderId());
            order.setStatus(entity.getStatus());
            order.setOrderTime(entity.getOrderTime());
            order.setDeliveryTime(entity.getDeliveryTime());
            order.setFoodIds(entity.getFoods().stream().map(FoodEntity::getFoodId).collect(Collectors.toList()));
            order.setFoodNames(entity.getFoods().stream().map(FoodEntity::getName).collect(Collectors.toList()));
            return order;
        }).collect(Collectors.toList());
    }



    public Order updateOrderStatus(Long orderId, String newStatus) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Objednávka s daným ID neexistuje.");
        }

        OrderEntity orderEntity = optionalOrder.get();
        orderEntity.setStatus(newStatus);
        orderRepository.save(orderEntity);

        Order order = new Order();
        order.setOrderId(orderEntity.getOrderId());
        order.setStatus(orderEntity.getStatus());
        order.setOrderTime(orderEntity.getOrderTime());
        order.setDeliveryTime(orderEntity.getDeliveryTime());
        order.setFoodIds(orderEntity.getFoods().stream().map(FoodEntity::getFoodId).collect(Collectors.toList()));
        order.setFoodNames(orderEntity.getFoods().stream().map(FoodEntity::getName).collect(Collectors.toList()));
        return order;
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Objednávka s daným ID neexistuje.");
        }
        orderRepository.deleteById(orderId);
    }
}
