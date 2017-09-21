package tz.co.fasthub.fundraising.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tz.co.fasthub.fundraising.model.Campaign;

/**
 * Created by Revocatus Nyaindi on 9/12/2017.
 */
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

}
