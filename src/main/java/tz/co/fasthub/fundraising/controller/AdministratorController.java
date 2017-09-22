package tz.co.fasthub.fundraising.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import tz.co.fasthub.fundraising.model.Administrator;
import tz.co.fasthub.fundraising.repository.AdministratorRepository;
import tz.co.fasthub.fundraising.service.AdministratorService;

/**
 * Created by naaminicharles on 9/21/17.
 */
@Controller
public class AdministratorController {
    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @Bean
    CommandLineRunner setupUsers(AdministratorRepository administratorRepository) {

        return (args) -> {
            Administrator admin = administratorService.findByUsername("admin");
            if (admin == null) {
                admin = new Administrator();
                admin.setUsername("admin");
                admin.setPassword("admin");
                admin.setCpassword("admin");
                admin.setRole("ROLE_ADMIN");
                administratorService.save(admin);
            }
        };
    }
}
