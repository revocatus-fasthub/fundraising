package tz.co.fasthub.fundraising.service;


import tz.co.fasthub.fundraising.model.User;

public interface UserService {
	User findUserByEmail(String email);

	User saveUser(User user);

    User findUserByUsername(String username);

    User getUserById(Long userId);

    String getUserIdByUsername(String username);

    Iterable<User> findAllUsers();
}
