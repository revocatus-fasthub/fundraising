package tz.co.fasthub.fundraising.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tz.co.fasthub.fundraising.model.Administrator;
import tz.co.fasthub.fundraising.repository.AdministratorRepository;

/**
 * Created by naaminicharles on 9/21/17.
 */
@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    private final AdministratorRepository administratorRepository;

    @Autowired
    public UserDetailsServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Administrator administrator = administratorRepository.findByUsername(username);
            if (administrator == null) {
                logger.debug("administrator not found with the provided username");
                throw new UsernameNotFoundException("User not found");
            }
            logger.debug(" administrator from username " + administrator.toString());
            return administrator;
  }

}
