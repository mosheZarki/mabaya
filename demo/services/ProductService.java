package com.example.demo.services;

import com.example.demo.entities.Ad;
import com.example.demo.entities.Product;
import com.example.demo.entities.enums.Categories;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.views.ProductView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.ManagedBean;
import java.util.List;

@Slf4j
@ManagedBean
@Component
public class ProductService {

    private ProductRepository repository;
    private AdsService adsService;

    @Autowired
    public ProductService(ProductRepository repository, AdsService adsService) {
        this.repository = repository;
        this.adsService = adsService;
    }

    @Transactional
    public Product createNewProduct(Product product)
    {
        return this.repository.save(product);
    }

    public Product getProductToPromoteByCategory(Categories category)
    {
        Ad ad = adsService.getAdByCategory(category);
        return  ad != null ? buildProductView(ad.getAdProducts().get(0)) : buildProductView(adsService.findAdOfHighestBid().getAdProducts().get(0));
    }

    public List<Product> getAllProductsOfSellerById(Long sellerId)
    {
        return repository.findAllBySellerId(sellerId);
    }

    public Product buildProductView(Product product)
    {
        return new Product(product.getId(),product.getTitle(),product.getPrice(),product.getCategorie(),product.getSerialNumber(),product.getSeller());
    }
}
