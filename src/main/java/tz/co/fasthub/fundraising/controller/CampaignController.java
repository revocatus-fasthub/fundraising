package tz.co.fasthub.fundraising.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tz.co.fasthub.fundraising.model.Campaign;
import tz.co.fasthub.fundraising.model.User;
import tz.co.fasthub.fundraising.service.CampaignService;
import tz.co.fasthub.fundraising.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class CampaignController {

    private static String UPLOADED_FOLDER = "F://UPLOADS//";

    private CampaignService campaignService;
    private final UserService userService;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public CampaignController(UserService userService) {
        this.userService = userService;
    }

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

//    View a specific campaign by its userId.
    @RequestMapping("campaign/{userId}")
    public String showCampaign(@PathVariable Integer userId, Model model) {
        model.addAttribute("campaign", campaignService.getCampaignByUserId(userId));
        return "campaignshow";
    }

    // Edit existing Campaign
    @RequestMapping("campaign/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("campaign", campaignService.getCampaignByUserId  (id));
        return "campaignform";
    }

//    create New campaign.
    @RequestMapping("/campaign/create/{userId}")
    public String newCampaign(@PathVariable Long userId, User user, Model model) {
        user = userService.getUserById(userId);
//        String authName = authentication.getName();
//        User authName = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name = authName.getUsername(); //get logged in username
//        if (name.equals(user.getUsername())){
        model.addAttribute("campaign", new Campaign());
//        model.addAttribute("user", name);
            return "campaignform";
//        }
//        return "redirect:/fund/home";
    }

//    Save campaign to database.
    @RequestMapping(value = "/campaign/{userId}", method = RequestMethod.POST)
    public String saveCampaign(@PathVariable Long userId, @Valid Campaign campaign, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

     User id = userService.getUserById(userId);

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

        campaignService.saveCampaignByUserId(campaign, id);

        return "redirect:/campaign/" +id+ campaign.getId();
    }

//    Delete campaign by its id.
    @RequestMapping("campaign/delete/{id}")
    public String delete(@PathVariable Integer id) {
        campaignService.deleteCampaign(id);
        return "redirect:/campaigns";
    }

}
