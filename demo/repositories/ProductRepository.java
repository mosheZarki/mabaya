package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.ManagedBean;
import java.util.List;

@Repository
@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllBySellerId(Long sellerId);
}
