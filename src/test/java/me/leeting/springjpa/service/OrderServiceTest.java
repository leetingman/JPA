package me.leeting.springjpa.service;


import me.leeting.springjpa.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EntityManager em;


    @Test
    public void orderTest() throws Exception{
        //Given

        //When

        //Then
    }

}