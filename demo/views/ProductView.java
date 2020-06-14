package com.example.demo.views;

import com.example.demo.entities.enums.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import java.io.Serializable;

@Component
public class ProductView implements Serializable {

    private Long id;
    private String title;
    private Integer price;
    private Categories category;
    private String serialNumber;
    private Long sellerId;

    public ProductView(Long id, String title, Integer price, Categories category, String serialNumber, Long sellerId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.serialNumber = serialNumber;
        this.sellerId = sellerId;
    }

    public ProductView(){}
}
