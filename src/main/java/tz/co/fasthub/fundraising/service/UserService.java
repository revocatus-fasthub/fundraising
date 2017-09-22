package tz.co.fasthub.fundraising.service;


import tz.co.fasthub.fundraising.model.User;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);


    User findUserByUsername(String username);
}
