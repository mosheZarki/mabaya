package com.example.demo;

import com.example.demo.entities.Campaign;
import com.example.demo.entities.Product;
import com.example.demo.entities.Seller;
import com.example.demo.entities.enums.Categories;
import com.example.demo.repositories.CampaignRepository;
import com.example.demo.repositories.SellerRepository;
import com.example.demo.services.AdsService;
import com.example.demo.services.CampeignService;
import com.example.demo.services.ProductService;
import com.example.demo.services.SellerService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.demo.entities.enums.Categories.*;

@Configuration
@Slf4j
public class Conf {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Conf.class);

    @Bean
    CommandLineRunner initDatabase(SellerService sellerService, AdsService adsService, CampeignService campeignService, ProductService productService) {

        return args -> {
            log.info("creating new seller" + sellerService.createSeller(new Seller("moshe zarki")));
            log.info("creating new seller" + sellerService.createSeller(new Seller("bezalel cohen")));
            log.info("creating new seller" + sellerService.createSeller(new Seller("ilay zur")));
            log.info("creating new seller" + sellerService.createSeller(new Seller("itamar ahron malka")));
            log.info("creating new seller" + sellerService.createSeller(new Seller("ron gay meod")));
            log.info("Preloading " + productService.createNewProduct(new Product("Moshe-First-bmw", 100, VEHICLE, "serial",sellerService.getSellerByName("moshe zarki"))));
            log.info("Preloading " + productService.createNewProduct(new Product("Moshe-Second-mazda", 120, VEHICLE, "serial",sellerService.getSellerByName("moshe zarki"))));
            log.info("Preloading " + productService.createNewProduct(new Product("Beza-mercedes", 150, VEHICLE, "serial",sellerService.getSellerByName("bezalel cohen"))));
            log.info("Preloading " + productService.createNewProduct(new Product("itamar ahron malka-toyota", 200, VEHICLE, "serial",sellerService.getSellerByName("itamar ahron malka"))));
            log.info("Preloading " + productService.createNewProduct(new Product("Ron-flat",10000,REALESTATE, "flatSerial",sellerService.getSellerByName("ron gay meod"))));
            log.info("Preloading " + productService.createNewProduct(new Product("Ron-Second-flat",10000,REALESTATE, "flatSerial",sellerService.getSellerByName("ron gay meod"))));
            log.info("Preloading " + productService.createNewProduct(new Product("Itamar-flat",10000,REALESTATE, "flatSerial",sellerService.getSellerByName("itamar ahron malka"))));
            log.info("bla" + productService.getAllProductsOfSellerById(sellerService.getSellerByName("moshe zarki").getId()));
            log.info("creating new campaign : " + campeignService.createNewCampaign(new Campaign("moshe-campagin",150,sellerService.getSellerByName("moshe zarki"),productService.getAllProductsOfSellerById(sellerService.getSellerByName("moshe zarki").getId()))));
//            log.info("creating new campaign : " + campeignService.createNewCampaign(new Campaign("itamar ahron malka-campagin",170,sellerService.getSellerByName("itamar ahron malka"),productService.getAllProductsOfSellerById(sellerService.getSellerByName("itamar ahron malka").getId()))));
//            campeignService.deActivateCampaign(15L);
//            campeignService.activateCampaign(15L);
//            productService.getProductToPromoteByCategory(CLOTHING);
            log.info("get all ads : " + adsService.getAllAds());
        };
    }
}
