package tfzr.store.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tfzr.store.model.Cart;
import tfzr.store.model.User;
import tfzr.store.repository.ShoppingCartRepository;
import tfzr.store.service.ShoppingCartService;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingCartRepository shoppingCartRepository;

	@Inject
	public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
		this.shoppingCartRepository = shoppingCartRepository;
	}

	@Override
	public List<Cart> findAll() {
		return shoppingCartRepository.findAll();
	}

	@Override
	public Cart create(Cart cart) {
		if (checkExistance(cart)) {
			Cart oldCart = shoppingCartRepository.findUserProductSizeInCart(
					cart.getUser(), cart.getProduct());
			shoppingCartRepository.updateQuantityInCart(cart.getProduct(),
					cart.getUser(), oldCart.getQuantity() + cart.getQuantity());
			return oldCart;
		} else {
			return shoppingCartRepository.save(cart);
		}
	}

	@Override
	public void delete(Integer id) {
		shoppingCartRepository.delete(id);
	}

	@Override
	public Set<Cart> findCartByUser(User user) {
		return shoppingCartRepository.findByUser(user);
	}

	@Override
	public Integer countCartsByUser(User user) {
		return shoppingCartRepository.countCartsByUser(user);
	}

	@Override
	public void deleteCartForSpecificUser(Integer userId) {
		shoppingCartRepository.deleteProductsForSpecificUser(userId);
	}

	private boolean checkExistance(Cart cart) {
		Optional<Cart> optionalCart = Optional
				.ofNullable(shoppingCartRepository.findUserProductSizeInCart(
						cart.getUser(), cart.getProduct()));

		return (optionalCart.isPresent() == true) ? true : false;

	}
}
