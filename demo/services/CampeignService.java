package com.example.demo.services;

import com.example.demo.entities.Campaign;
import com.example.demo.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.ManagedBean;

import static com.example.demo.entities.enums.CampaignStatuses.DELETED;

@ManagedBean
@Component
public class CampeignService {

    private CampaignRepository campaignRepository;
    private AdsService adsService;

    @Autowired
    public CampeignService(CampaignRepository campaignRepository, AdsService adsService) {
        this.campaignRepository = campaignRepository;
        this.adsService = adsService;
    }

    @Transactional
    public Campaign createNewCampaign(Campaign campaignToSave) {
        campaignRepository.save(campaignToSave);
        adsService.createAdsByCampaign(campaignToSave);

        return campaignToSave;
    }

    @Transactional
    public void activateCampaign(Long campaignId)
    {
        campaignRepository.activateById(campaignId);
        adsService.createAdsByCampaign(campaignRepository.findById(campaignId).get());
    }

    @Transactional
    public void deActivateCampaign(Long campeignId) {
        Campaign campaign = campaignRepository.findById(campeignId).orElse(null);

        if (campaign != null) {
            campaign.setCampaignStatus(DELETED);
            campaignRepository.save(campaign);

            // remove all ads,list of prod is still in camp in case of reActivating in the future.
           adsService.deleteAdsByCampaignId(campeignId);
        }
    }
}
