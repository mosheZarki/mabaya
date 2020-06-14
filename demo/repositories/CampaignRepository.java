package com.example.demo.repositories;

import com.example.demo.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Campaign c set c.campaignStatus = 0 where c.id = :campaignId ")
    public void activateById(Long campaignId);
}
