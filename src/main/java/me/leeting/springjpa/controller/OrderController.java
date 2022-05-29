package me.leeting.springjpa.controller;

import lombok.RequiredArgsConstructor;
import me.leeting.springjpa.domain.Member;
import me.leeting.springjpa.domain.Order;
import me.leeting.springjpa.domain.item.Item;
import me.leeting.springjpa.repository.OrderSearch;
import me.leeting.springjpa.service.ItemService;
import me.leeting.springjpa.service.MemberService;
import me.leeting.springjpa.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model)
    {
        List<Member> members=memberService.findMembers();
        List<Item> items= itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String orderItem(@RequestParam("memberId") Long memberId,
                            @RequestParam("itemId") Long itemId,
                            @RequestParam("count") int count
                            ){
        orderService.order(memberId,itemId,count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch,Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);

        return "/order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/order";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String editOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "redirect:/order";
    }

}
