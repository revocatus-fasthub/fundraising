package tz.co.fasthub.fundraising.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tz.co.fasthub.fundraising.model.Campaign;
import tz.co.fasthub.fundraising.model.User;
import tz.co.fasthub.fundraising.service.CampaignService;
import tz.co.fasthub.fundraising.service.UserService;
import tz.co.fasthub.fundraising.validator.UserValidator;

import javax.validation.Valid;

@Controller
public class LoginController {

	private final UserService userService;

    private final CampaignService campaignService;
    private final UserValidator userValidator;

    @Autowired
    public LoginController(UserService userService, CampaignService campaignService, UserValidator userValidator) {
        this.userService = userService;
        this.campaignService = campaignService;
        this.userValidator = userValidator;
    }


	@RequestMapping(value="/registration/new", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("user", new User());

		return "registration";
	}
	
	@RequestMapping(value = "/process/registration", method = RequestMethod.POST)
	public String registration(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
//		User userExists = userService.findUserByEmail(user.getEmail());
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("flash.message.userFail", "Failed. Error during registration");
			return "registration";
		}

		userService.saveUser(user);
		redirectAttributes.addFlashAttribute("successMessage", "User has been registered successfully.");
		model.addAttribute("user", new User());

		return "redirect:/fund/home";
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public String home(@ModelAttribute("administrator") User administrator, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		administrator = userService.findUserByEmail(auth.getName());
		model.addAttribute("userName", "Welcome " + administrator.getName() + " " + administrator.getLastName() + " (" + administrator.getEmail() + ")");
   //     model.addAttribute("userName", "Welcome " + administrator);

        model.addAttribute("campaign", new Campaign());

		return "admin/home";
	}

	@RequestMapping(value = "/campaigns", method = RequestMethod.POST)
	public String saveCampaign(Campaign campaign, Model model) {
		campaignService.saveCampaign(campaign);
		model.addAttribute("campaigns", campaignService.listAllCampaigns());

		return "campaigns";
	}

}
