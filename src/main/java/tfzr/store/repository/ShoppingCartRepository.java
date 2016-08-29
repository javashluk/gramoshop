package tfzr.store.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tfzr.store.model.Cart;
import tfzr.store.model.Product;
import tfzr.store.model.User;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Cart, Integer> {

	Set<Cart> findByUser(User user);
	
	@Modifying
	@Query("DELETE FROM tfzr.store.model.Cart cart WHERE cart.user.id = ?1")
	public void deleteProductsForSpecificUser(Integer userId);
	
	Integer countCartsByUser(User user);
	
	@Modifying
	@Query("UPDATE tfzr.store.model.Cart cart SET cart.quantity = ?3 WHERE "
			+ "cart.user = ?2 AND cart.product = ?1")
	public void updateQuantityInCart(Product product, User user,
			Integer quantity);
	
	@Query("SELECT cart FROM tfzr.store.model.Cart cart WHERE cart.user = ?1 AND cart.product = ?2")
	public Cart findUserProductSizeInCart(User user, Product product );
}
