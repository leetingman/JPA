package me.leeting.springjpa.service;


import lombok.RequiredArgsConstructor;
import me.leeting.springjpa.domain.item.Book;
import me.leeting.springjpa.domain.item.Item;
import me.leeting.springjpa.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    /**
     * spring Transactional 에 의해 commit 된디
     * jpa 는 flush 를 날림
     * 트랜잭션 커밋시점에 영속성 엔티티 에서 변경된 값을 찾음
     * 더티checking 권장 사항
     * @param itemId
     * @param param
     */
    @Transactional
    public void updateItem(Long itemId,String name,int price ,int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);//영속성 상
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

        //update 해야될것 이 많으면 Dto 생성
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findItem(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
