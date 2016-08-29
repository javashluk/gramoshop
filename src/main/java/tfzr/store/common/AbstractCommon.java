package tfzr.store.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;

import tfzr.store.model.Product;
import tfzr.store.model.User;
import tfzr.store.service.ProductService;
import tfzr.store.service.UserService;

public abstract class AbstractCommon {
	
	@Inject
	public ProductService productService;
	@Inject
	public UserService userService;
	

	public Set<String> findAllArtists() {
		List<String> artists = new ArrayList<String>();
		List<Product> products = productService.findAll();
		products.stream().forEach(prod -> artists.add(prod.getArtist()));
		Set<String> distinctArtists = new HashSet<String>(artists);
		return distinctArtists;
	}
	
	public User getCurrentUser() {
		String user = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		return userService.findByUsername(user);
	}
	
}
