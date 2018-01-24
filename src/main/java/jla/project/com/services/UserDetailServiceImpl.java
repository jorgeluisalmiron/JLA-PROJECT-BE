package jla.project.com.services;

import java.util.ArrayList;
import java.util.Collection;

import jla.project.com.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jla.project.com.model.User;

/**
 * Authenticate a user from the database.
 */
@Service
public class UserDetailServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

  //  private final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
 //       log.debug("Authenticating {}", login);

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        } 
       /* if (!user.getEnabled()) {
            throw new UserNotEnabledException("User " + login + " was not enabled");
        }
*/
       /* Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (UserProfile authority : user.getUserProfiles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getType());
            grantedAuthorities.add(grantedAuthority);
        }
*/
       Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
       GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
       grantedAuthorities.add(grantedAuthority);
      
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
				 true, true, true, true, grantedAuthorities);
	}

	
	
}
