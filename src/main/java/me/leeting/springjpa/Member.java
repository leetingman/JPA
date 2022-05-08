package me.leeting.springjpa;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@Builder
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
}
