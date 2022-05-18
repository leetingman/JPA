package me.leeting.springjpa.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "please input user name")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
