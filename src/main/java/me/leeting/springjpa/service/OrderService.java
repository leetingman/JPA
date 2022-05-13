package me.leeting.springjpa.service;


import lombok.RequiredArgsConstructor;
import me.leeting.springjpa.domain.Delivery;
import me.leeting.springjpa.domain.Member;
import me.leeting.springjpa.domain.Order;
import me.leeting.springjpa.domain.OrderItem;
import me.leeting.springjpa.domain.item.Item;
import me.leeting.springjpa.repository.ItemRepository;
import me.leeting.springjpa.repository.MemberRepository;
import me.leeting.springjpa.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //order
    @Transactional
    public Long order(Long memberId,Long itemId,int count){
        //entity
        Member member = memberRepository.findOne(memberId);
        Item item =itemRepository.findOne(itemId);

        //배송정보
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //orderItem
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //order
        Order order = Order.createOrder(member, delivery, orderItem);

        //save
        orderRepository.save(order);

        return order.getId();
    }

    //cancel
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);

        order.cancel();

    }

    //find
//domain model pattern
}
