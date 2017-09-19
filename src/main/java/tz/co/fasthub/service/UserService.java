package tz.co.fasthub.service;


import tz.co.fasthub.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
