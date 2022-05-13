package me.leeting.springjpa.repository;

import lombok.RequiredArgsConstructor;
import me.leeting.springjpa.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private static EntityManager em;

    public void save(Order order){
        em.persist(order);
    }
    public Order findOne(Long id){
        return em.find(Order.class,id);
    }
}
