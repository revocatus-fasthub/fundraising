package tz.co.fasthub.fundraising.service;


import tz.co.fasthub.fundraising.model.User;

import java.util.List;

public interface UserService {
	User findUserByEmail(String email);

	User saveUser(User user);

    User findUserByUsername(String username);

    User getUserById(Long userId);

    List<User> findAllUsers();
}
