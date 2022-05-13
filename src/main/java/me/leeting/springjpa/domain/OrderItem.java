package me.leeting.springjpa.domain;


import lombok.Getter;
import lombok.Setter;
import me.leeting.springjpa.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;
    private int count;

    public static OrderItem createOrderItem(Item item ,int orderPrice,int count){
        OrderItem orderItem=new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);//아이템 재고 수량 생성시 재고수량 감소
       return orderItem;
    }
    public void cancel() {
        getItem().addStock(count);//재고구 수량 복9
    }
    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }
}
