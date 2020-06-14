package com.example.demo.entities;

import com.example.demo.entities.enums.CampaignStatuses;
import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.ManagedBean;
import javax.persistence.*;

import java.util.List;

import static com.example.demo.entities.enums.CampaignStatuses.ACTIVE;

@Entity
@ManagedBean
@Table(name = "CAMPAIGN")
public class Campaign {

    @Id
    @GeneratedValue
    @Column(name = "campaign_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column
    // for default value
    private CampaignStatuses campaignStatus = ACTIVE;

    @Column
    private String campaignName;

    @Column
    private Integer bid;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

//    (fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "campaign_to_product",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;

    public Campaign(String campaignName, Integer bid,Seller seller,List<Product> productList){
        this.campaignName = campaignName;
        this.bid = bid;
        this.seller = seller;
        this.productList = productList;
    }

    public Integer getBid() {
        return bid;
    }

    public Long getId() {
        return id;
    }

    public Seller getSeller() {
        return seller;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public CampaignStatuses getCampaignStatus() {
        return campaignStatus;
    }

    public boolean isActive()
    {
        return this.campaignStatus == ACTIVE;
    }

    public void setCampaignStatus(CampaignStatuses status)
    {
        this.campaignStatus = status;
    }

    public Campaign(){}
}
