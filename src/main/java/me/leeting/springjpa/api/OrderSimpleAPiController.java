package me.leeting.springjpa.api;


import lombok.RequiredArgsConstructor;
import me.leeting.springjpa.domain.Order;
import me.leeting.springjpa.repository.OrderRepository;
import me.leeting.springjpa.repository.OrderSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * x TO ONE
 * Order
 * Oredr->Member
 * Order->Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderSimpleAPiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order>ordersV1(){
        List<Order> all= orderRepository.findAllByString(new OrderSearch());
        return all;
    }
}
