package tz.co.fasthub.fundraising.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tz.co.fasthub.fundraising.model.User;
import tz.co.fasthub.fundraising.repository.RoleRepository;
import tz.co.fasthub.fundraising.repository.UserRepository;
import tz.co.fasthub.fundraising.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCpassword(bCryptPasswordEncoder.encode(user.getCpassword()));
        user.setActive(1);
        user.setRole("ROLE_USER");
/*        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));*/
		userRepository.save(user);
		return user;
	}

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public String getUserIdByUsername(String username) {
        User userUsername = userRepository.findByUsername(username);
        String hisId = String.valueOf(userUsername.getId());
        return hisId;
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

}
