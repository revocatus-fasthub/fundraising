package tz.co.fasthub.fundraising.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tz.co.fasthub.fundraising.model.User;
import tz.co.fasthub.fundraising.repository.UserRepository;

/**
 * Created by naaminicharles on 9/21/17.
 */
@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository administratorRepository) {
        this.userRepository = administratorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = userRepository.findByUsername(username);
            if (user == null) {
                logger.debug("user not found with the provided username");
                throw new UsernameNotFoundException("User not found");
            }
            logger.debug(" user from username " + user.toString());
            return user;
  }

}
