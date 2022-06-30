package me.leeting.springjpa.service;


import me.leeting.springjpa.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    EntityManager em;


    //public List<Item> findItems(){
    //        return itemRepository.findAll();
    //    }

    @Test
    public void createItemTest() throws Exception{
        //Given
//        item.setName("213");/**/

        //When

        //Then
    }

    @Test
    public void findListTest() throws Exception{
        //Given
        List<Item> list =itemService.findItems();
        //When

        //Then
        System.out.println(list);
    }




}
