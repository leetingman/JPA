package me.leeting.springjpa.domain.item;


import lombok.Getter;
import lombok.Setter;
import me.leeting.springjpa.domain.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name="item_id")
    private Long Id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child =new ArrayList<>();


}
