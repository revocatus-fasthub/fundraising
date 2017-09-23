package tz.co.fasthub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tz.co.fasthub.model.Campaign;
import tz.co.fasthub.model.User;
import tz.co.fasthub.service.CampaignService;
import tz.co.fasthub.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

	//Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "F://UPLOADS//";

	@Autowired
	private UserService userService;

	@Autowired
	private CampaignService campaignService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("user", new User());

		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",	"There is already a user registered with the email provided");
		}

		if (bindingResult.hasErrors()) {

			return "registration";
		}

		userService.saveUser(user);
		redirectAttributes.addFlashAttribute("successMessage", "User has been registered successfully.");
		model.addAttribute("user", new User());

		return "redirect:/registration";
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public String home(@ModelAttribute("user") User user, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByEmail(auth.getName());
		model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		model.addAttribute("campaign", new Campaign());

		return "admin/home";
	}

	@RequestMapping(value = "/campaignshow", method = RequestMethod.POST)
	public String saveCampaign(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Campaign campaign, Model model) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "admin/home";
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
		model.addAttribute("campaignshow", campaignService.listAllCampaigns());

		return "campaignshow";
	}


}
