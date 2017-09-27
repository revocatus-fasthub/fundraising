package tz.co.fasthub.fundraising.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tz.co.fasthub.fundraising.model.User;
import tz.co.fasthub.fundraising.service.UserService;

import java.util.List;

/**
 * Created by naaminicharles on 9/27/17.
 */
@Controller
public class JsonController {

    private static final Logger log = LoggerFactory.getLogger(JsonController.class);


    private final UserService userService;

    @Autowired
    public JsonController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody
    List<User> viewAllUsers(){
        return userService.findAllUsers();
    }


}
