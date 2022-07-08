package me.leeting.springjpa.repository.order.query;

import lombok.Data;
import me.leeting.springjpa.domain.Address;
import me.leeting.springjpa.domain.OrderStatus;

import java.util.List;

@Data
public class OrderQueryDto {
    private Long orderId;
    private String name;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderStatus = orderStatus;
        this.address = address;
    }


}
