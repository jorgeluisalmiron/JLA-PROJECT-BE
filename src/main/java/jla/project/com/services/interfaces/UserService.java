package jla.project.com.services.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jla.project.com.model.User;


public interface UserService {
	
	User findById(long id);
	
	User findByUsername(String username) throws UsernameNotFoundException;
	
	void save(User User);
	
	void update(User User);
	
	void delete(String username);

	List<User> findAllUsers(); 
	
	//boolean isUserSSOUnique(Long id, String sso);
	
	

}
