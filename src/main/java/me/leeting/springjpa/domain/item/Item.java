package me.leeting.springjpa.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name="item_id")
    private Long Id;

    private String name;
    private int price;
    private int stockQuantity;
}
