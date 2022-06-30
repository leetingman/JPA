package me.leeting.springjpa.service;


import me.leeting.springjpa.domain.Address;
import me.leeting.springjpa.domain.Member;
import me.leeting.springjpa.domain.Order;
import me.leeting.springjpa.domain.OrderStatus;
import me.leeting.springjpa.domain.item.Book;
import me.leeting.springjpa.exception.NotEnoughStockException;
import me.leeting.springjpa.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;


    @Test
    public void orderTest() throws Exception{
        //Given
        Member member =createMember();

        Book book = createBook("y'all", 10000, 10);
        int orderCount=2;
        //When
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //Then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상태 ", OrderStatus.ORDER,getOrder.getStatus());
        assertEquals("주문한 상품 종류 수 확인",1,getOrder.getOrderItems().size());
        assertEquals("가격",10000*orderCount,getOrder.getTotalPrice());
        assertEquals("주문수량",8,book.getStockQuantity());
    }
    // error note
    // 1 . InvalidDataAccessApiUsageException


    @Test
    public void overStockException() throws Exception{
        //Given
        Member member = createMember();
        Book book = createBook("y'all", 10000, 10);

        int orderCount=11;
        //When
        //Then
        assertThrows(NotEnoughStockException.class,()->{
            orderService.order(member.getId(),book.getId(),orderCount);
        });
        fail("재고 수량 부족 예");
    }

    @Test
    public void cancelTest() throws Exception{
        //Given
        Member member=createMember();
        Book book = createBook("JPA", 10000, 10);

        int orderCount=2;
        Long orderId=orderService.order(member.getId(),book.getId(),orderCount);

        //When
        orderService.cancelOrder(orderId);
        //Then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("Expected status CANCEL",OrderStatus.CANCEL,getOrder.getStatus());
        assertEquals("stock",10,book.getStockQuantity());

    }



    private Book createBook(String name, int price, int stockQuantity) {
        Book book=new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("user1");
        member.setAddress(new Address("seoul","sam","1234"));
        em.persist(member);
        return member;
    }

}