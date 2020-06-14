package com.example.demo.repositories;

import com.example.demo.entities.Ad;
import com.example.demo.entities.Campaign;
import com.example.demo.entities.enums.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Component
public interface AdsRepository extends JpaRepository<Ad, Long> {

    @Query("select a from Ad a where a.categorie = :categorie and a.bid = (select max(ad.bid) from Ad ad where ad.categorie = :categorie )")
    public Ad getAdByCategorieAndHighestBid(Categories categorie);

    @Query("select ad from Ad ad where ad.bid in (Select MAX (a.bid) from Ad a)")
    public List<Ad> getByMaxBid();

    public List<Ad> findAllByCampaignId(Long campeignId);


}
