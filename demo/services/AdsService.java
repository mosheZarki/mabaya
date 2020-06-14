package com.example.demo.services;

import com.example.demo.entities.Ad;
import com.example.demo.entities.Campaign;
import com.example.demo.entities.Product;
import com.example.demo.entities.enums.Categories;
import com.example.demo.repositories.AdsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.ManagedBean;
import java.util.*;

@ManagedBean
@Component
@Slf4j
public class AdsService {

    private final AdsRepository repository;

    @Autowired
    public AdsService(AdsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Ad> createAdsByCampaign(Campaign campaign) {
        List<Ad> toSave = new ArrayList<>();
        Map<Categories, List<Product>> map = mapProductsByCategories(campaign.getProductList());
        for (Categories category : map.keySet()) {
            toSave.add(new Ad(campaign, category, map.get(category)));
        }

        return repository.saveAll(toSave);
    }

    @Transactional
    public void deleteAdsByCampaignId(Long campaignId) {
        repository.deleteAll(repository.findAllByCampaignId(campaignId));
    }

    public Ad getAdByCategory(Categories category) {
        return repository.getAdByCategorieAndHighestBid(category);
    }

    private Map<Categories, List<Product>> mapProductsByCategories(List<Product> products) {
        Map<Categories, List<Product>> mappedProducts = new HashMap<>();
        List<Product> listToAdd = new ArrayList<>();
        for (Categories category : Categories.values()) {
            listToAdd = new ArrayList<>();
            for (Product curr : products) {
                if (curr.getCategorie() == category) {
                    listToAdd.add(curr);
                }
            }

            if (!listToAdd.isEmpty()) {
                mappedProducts.put(category, listToAdd);
            }
        }

        return mappedProducts;
    }

    public Ad findAdOfHighestBid() {
        return repository.getByMaxBid().get(0);
    }

    public List<Ad> getAllAds() {
        return repository.findAll();
    }
}
