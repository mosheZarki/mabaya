package com.example.demo.entities;

import com.example.demo.entities.enums.Categories;
import lombok.Data;
import javax.annotation.ManagedBean;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ManagedBean
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column
    private String title;

    @Column
    private Integer price;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Categories categorie;

    @Column
    private String serialNumber;

    // now if many sellers sell the same item, duplicates items will
    // be stored in the db with different seller, for sake of simplicity.
//    (fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

//    ,fetch = FetchType.EAGER
    @ManyToMany(mappedBy = "productList",fetch = FetchType.EAGER)
    private List<Campaign> campaignList;

//    (mappedBy = "adProducts",fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "adProducts",fetch = FetchType.EAGER)
    private List<Ad> adsList;

    public Product(){}

    public Product(String title,Integer price,Categories categorie,String serialNumber,Seller seller) {
        this.title = title;
        this.price = price;
        this.categorie = categorie;
        this.serialNumber = serialNumber;
        this.seller = seller;
    }

    // TODO : overloading
    public Product(Long id,String title,Integer price,Categories categorie,String serialNumber,Seller seller) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categorie = categorie;
        this.serialNumber = serialNumber;
        this.seller = seller;
    }

    public Categories getCategorie() {
        return categorie;
    }

    public Long getId() {
        return id;
    }

    public Seller getSeller() {
        return seller;
    }

    public Integer getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}