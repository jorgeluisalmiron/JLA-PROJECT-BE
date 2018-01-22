package jla.project.com.services;

import java.util.List;

import jla.project.com.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jla.project.com.dao.UserDao;
import jla.project.com.model.User;

@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;

	
	
	
	public User findById(long id) {
		return dao.findOne(id);
	}

	 	 
	public User findByUsername(String username) throws UsernameNotFoundException{
		User user = dao.findByUsername(username);
		if (user==null)
		{
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return user;
	}

	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	public void update(User user) {
		User entity = dao.findOne(user.getId());
		if(entity!=null){
			entity.setUsername(user.getUsername());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
		/*	entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());*/
		}
	}

	
	public void delete(String username) {
		User user = dao.findByUsername(username);
		if (user!=null)
		{
			dao.delete(user);
		}
	}

	public List<User> findAllUsers() {
		return dao.findAll();
	}

	



}
