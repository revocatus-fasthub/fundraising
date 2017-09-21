package tz.co.fasthub.fundraising.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.co.fasthub.fundraising.model.Campaign;
import tz.co.fasthub.fundraising.repository.CampaignRepository;
import tz.co.fasthub.fundraising.service.CampaignService;

/**
 * Created by Revocatus Nyaindi on 9/12/2017.
 */
@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;

    @Autowired
    public void setCampaignRepository(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Iterable<Campaign> listAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Campaign getCampaignById(Integer id) {
        return campaignRepository.findOne(id);
    }

    @Override
    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public void deleteCampaign(Integer id) {
        campaignRepository.delete(id);
    }

}
