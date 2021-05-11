package com.members.members.model;

import javax.annotation.processing.Generated;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class Member implements Serializable {

    private static final String IMAGE_URL = "https://bootdey.com/img/Content/avatar/avatar";
    private static final Integer[] NUMBER = {1,2,3,4,5,6,7,8};
    private String id;
    private String name;
    private String email;
    private String phone;
    private String imageUrl;

    public Member() {}

    public Member(String name, String email, String phone ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        setImageUrl();
        createId();
    }

    public void createId(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl() {
        Random rnd = new Random();
        int max = 9;
        int number = rnd.nextInt(max);

       this.imageUrl = MessageFormat.format("{0}{1}.png",IMAGE_URL,NUMBER[number]);
    }

    @Override
    public String toString(){
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
