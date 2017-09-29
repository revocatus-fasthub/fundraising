package tz.co.fasthub.fundraising.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tz.co.fasthub.fundraising.model.User;
import tz.co.fasthub.fundraising.service.UserService;

/**
 * Created by naaminicharles on 9/27/17.
 */
@RestController
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
/*        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);*/

        return userService.saveUser(user);
    }



    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> viewAllUsers(){
        return userService.findAllUsers();
    }


}
