package tfzr.store.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tfzr.store.model.CurrentUser;
import tfzr.store.model.Role;
import tfzr.store.model.User;
import tfzr.store.repository.UserRepository;
import tfzr.store.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	@Inject
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		final User user = userRepository.findByUsername(username);
        
        UserDetails userDetail = new CurrentUser(user);
        
        return userDetail;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAllUnapproved(Role role) {
		return userRepository.findAllUnapproved(role);
	}
}
