package com.example.demo.contollers;

import com.example.demo.Exceptions.EntityNotFoundException;
import com.example.demo.entities.Campaign;
import com.example.demo.entities.Product;
import com.example.demo.entities.enums.Categories;
import com.example.demo.services.CampeignService;
import com.example.demo.services.ProductService;
import com.example.demo.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final ProductService productService;
    private final CampeignService campeignService;
    private final SellerService sellerService;


    @Autowired
    public Controller(ProductService productService, CampeignService campeignService, SellerService sellerService)
    {
        this.productService = productService;
        this.campeignService = campeignService;
        this.sellerService = sellerService;
    }

    @PostMapping(path = "/CreateCampaign")
    public Campaign createCampaign(@RequestBody String name,@RequestBody Integer bid,
                                   @RequestBody Long sellerId) throws EntityNotFoundException
    {
       return campeignService.createNewCampaign(new Campaign(name,bid,sellerService.getSellerById(sellerId),productService.getAllProductsOfSellerById(sellerId)));
    }

    @GetMapping(value = "/getHighesByCategory")
    public Product getProduct(@RequestParam String category){
        return productService.getProductToPromoteByCategory(Categories.valueOf(category)) ;
    }
}
