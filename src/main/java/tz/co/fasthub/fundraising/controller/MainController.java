package tz.co.fasthub.fundraising.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by naaminicharles on 9/21/17.
 */
@Controller
public class MainController {

    @RequestMapping("/fund/home")
    public String index(){
        return "index";
    }

    /**
     * path to display admin login page.
     */

    @RequestMapping(value={"/fund/login"}, method = RequestMethod.GET)
    public String login(){
        return "adminLogin";
    }

    /**
     * Login to Administrator Page process.
     */

    @RequestMapping("/login")
    public String getLoginForm(Model model, String error, String logout, RedirectAttributes redirectAttributes) {
        if (error != null) {
            model.addAttribute("message", "Invalid username of password, try again !");
            return "adminLogin";

        } else if (logout != null) {
            redirectAttributes.addFlashAttribute("flash.message.logout", "You've been logged out successfully");
            return "adminLogin";
        }

        return "redirect:/fund/home";
    }

    /**
     * logout from Administrator Page process.
     */

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        redirectAttributes.addFlashAttribute("flash.message.user","Successfully logged out");
        return "redirect:/fund/login";
    }


}
