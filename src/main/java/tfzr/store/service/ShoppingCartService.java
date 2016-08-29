package tfzr.store.service;

import java.util.List;
import java.util.Set;

import tfzr.store.model.Cart;
import tfzr.store.model.User;

public interface ShoppingCartService {

	public List<Cart> findAll();
	
	public Integer countCartsByUser(User user);
	
	public Set<Cart> findCartByUser(User user);
	
	public Cart create(Cart cart);
	
	public void deleteCartForSpecificUser(Integer userId);
	
	public void delete(Integer id);
}
