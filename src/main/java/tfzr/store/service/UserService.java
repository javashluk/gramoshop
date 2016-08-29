package tfzr.store.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import tfzr.store.model.Role;
import tfzr.store.model.User;

public interface UserService extends UserDetailsService{
	
	public User findByUsername(String username);

	public User save(User user);
	
	public void delete(Integer id);
	
	public UserDetails loadUserByUsername(String username);
	
	public List<User> findAllUnapproved(Role role);
}
