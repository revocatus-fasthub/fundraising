package tz.co.fasthub.fundraising.service;


import tz.co.fasthub.fundraising.model.Campaign;
import tz.co.fasthub.fundraising.model.User;

/**
 * Created by Revocatus Nyaindi on 9/12/2017.
 */

public interface CampaignService {

    Iterable<Campaign> listAllCampaigns();

    Campaign getCampaignByUserId(Integer userId);

    Campaign saveCampaignByUserId(Campaign campaign, User userId);

    void deleteCampaign(Integer id);

}
