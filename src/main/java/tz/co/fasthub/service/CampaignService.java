package tz.co.fasthub.service;


import tz.co.fasthub.model.Campaign;

/**
 * Created by Revocatus Nyaindi on 9/12/2017.
 */

public interface CampaignService {

    Iterable<Campaign> listAllCampaigns();

    Campaign getCampaignById(Integer id);

    Campaign saveCampaign(Campaign campaign);

    void deleteCampaign(Integer id);

}
