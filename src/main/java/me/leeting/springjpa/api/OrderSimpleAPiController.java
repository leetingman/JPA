package me.leeting.springjpa.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.leeting.springjpa.domain.Address;
import me.leeting.springjpa.domain.Order;
import me.leeting.springjpa.domain.OrderStatus;
import me.leeting.springjpa.repository.OrderRepository;
import me.leeting.springjpa.repository.OrderSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/api/v1/sample-orders")
    public List<Order>ordersV1(){
        List<Order> all= orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order .getMember().getName();//LAZY force init
            order.getDelivery().getAddress();
        }
        return all;
        //Hibernate5
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        List<Order> orders= orderRepository.findAllByString(new OrderSearch());

        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }

    @GetMapping ("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3(){
        List<Order> orders = orderRepository.findWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream().map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }

    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId= order.getId();
            name=order.getMember().getName();
            orderDate=order.getOrderDate();
            orderStatus=order.getStatus();
            address= order.getDelivery().getAddress();
        }
    }

}
