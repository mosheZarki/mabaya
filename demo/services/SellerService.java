package com.example.demo.services;

import com.example.demo.Exceptions.EntityNotFoundException;
import com.example.demo.entities.Seller;
import com.example.demo.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.ManagedBean;

@ManagedBean
@Component
public class SellerService {

    private final SellerRepository repository;

    @Autowired
    public SellerService(SellerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Seller createSeller(Seller seller){
        return repository.save(seller);
    }

    public Seller getSellerById(Long sellerId) throws EntityNotFoundException
    {
        return repository.findById(sellerId).orElseThrow(() ->new EntityNotFoundException(String.format("seller with ID : {} doesnt exists",sellerId)));
    }

    public Seller getSellerByName(String name)
    {
        return repository.getByFullName(name);
    }
}
