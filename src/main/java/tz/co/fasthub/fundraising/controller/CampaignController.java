package tz.co.fasthub.fundraising.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tz.co.fasthub.fundraising.model.Campaign;
import tz.co.fasthub.fundraising.service.CampaignService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class CampaignController {

    private static String UPLOADED_FOLDER = "F://UPLOADS//";

    private CampaignService campaignService;

    @Autowired
    public void setCampaignService(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

//    List all campaigns.
    @RequestMapping(value = "/campaigns", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("campaigns", campaignService.listAllCampaigns());
        System.out.println("Returning rcampaigns:");
        return "campaigns";
    }

//    View a specific campaign by its id.
    @RequestMapping("campaign/{id}")
    public String showCampaign(@PathVariable Integer id, Model model) {
        model.addAttribute("campaign", campaignService.getCampaignById(id));
        return "campaignshow";
    }

    // Edit Campaign
    @RequestMapping("campaign/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("campaign", campaignService.getCampaignById(id));
        return "campaignform";
    }

//    New campaign.
    @RequestMapping("campaign/new")
    public String newCampaign(Model model) {
        model.addAttribute("campaign", new Campaign());
        return "campaignform";
    }

//    Save campaign to database.
    @RequestMapping(value = "campaign", method = RequestMethod.POST)
    public String saveCampaign(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Campaign campaign) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("flash.message", "Please select a file to Upload");
            return "campaign";
        }

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        campaignService.saveCampaign(campaign);
        return "redirect:/campaign/" + campaign.getId();
    }

//    Delete campaign by its id.
    @RequestMapping("campaign/delete/{id}")
    public String delete(@PathVariable Integer id) {
        campaignService.deleteCampaign(id);
        return "redirect:/campaigns";
    }

}
