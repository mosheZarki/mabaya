package com.example.demo.repositories;

import com.example.demo.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component
public interface SellerRepository extends JpaRepository<Seller,Long> {

    public Seller getByFullName(String fullName);
}
