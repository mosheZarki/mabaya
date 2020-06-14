package com.example.demo.entities;

import com.example.demo.entities.enums.Categories;
import javax.annotation.ManagedBean;
import javax.persistence.*;
import java.util.List;

@Entity
@ManagedBean
@Table(name = "AD")
public class Ad {

    @Id
    @GeneratedValue
    @Column(name = "ad_id")
    private Long id;

    @Column
    private Integer bid;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Categories categorie;

//    (fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

//    (fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ad_to_product",
            joinColumns = @JoinColumn(name = "ad_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> adProducts;

    public List<Product> getAdProducts() {
        return adProducts;
    }

    public Ad(Campaign campaign, Categories categorie, List<Product> products){
        this.bid = campaign.getBid();
        this.campaign = campaign;
        this.adProducts = products;
        this.categorie = categorie;
    }

    public Ad(){}

}
