package me.leeting.springjpa.repository;

import lombok.Getter;
import lombok.Setter;
import me.leeting.springjpa.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;//ORDER , CANCEL
}

