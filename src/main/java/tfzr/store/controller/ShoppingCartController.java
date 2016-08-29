package tfzr.store.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tfzr.store.common.AbstractCommon;
import tfzr.store.model.Cart;
import tfzr.store.model.Product;
import tfzr.store.model.ProductDto;
import tfzr.store.model.ProductOrder;
import tfzr.store.service.GenreService;
import tfzr.store.service.ShoppingCartService;

@Controller
public class ShoppingCartController extends AbstractCommon {

	final private ShoppingCartService shoppingCartService;
	final private GenreService genreService;

	@Inject
	public ShoppingCartController(ShoppingCartService shoppingCartService,
			GenreService genreService) {
		super();
		this.shoppingCartService = shoppingCartService;
		this.genreService = genreService;
	}

	@RequestMapping(value = "/public/shoppingcarts", method = RequestMethod.POST)
	public String addToCart(@ModelAttribute("addToCart") ProductDto product) {
		Cart cart = new Cart();
		cart.setProduct(convertFromDto(product));
		cart.setUser(getCurrentUser());
		cart.setQuantity(product.getQuantity());
		shoppingCartService.create(cart);
		return "redirect:/public/index";
	}

	@RequestMapping(value = "/public/cart", method = RequestMethod.GET)
	public String showCart(Model model) {
		model.addAttribute("genre", genreService.findAll());
		model.addAttribute("artists", findAllArtists());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("carts",
				shoppingCartService.findCartByUser(getCurrentUser()));
		model.addAttribute("user", getCurrentUser());
		model.addAttribute("count",
				shoppingCartService.countCartsByUser(getCurrentUser()));
		model.addAttribute("order", new ProductOrder());
		return "cart";
	}
	
	@RequestMapping(value = "/public/shoppingcarts/delete/{id}", method = RequestMethod.GET)
	public String deleteProductFromCart(@PathVariable Integer id) {
		shoppingCartService.delete(id);
		return "redirect:/public/cart";
	}
	
	@RequestMapping(value = "/public/remove/{id}", method = RequestMethod.GET)
	public String deleteOneProductFromCart(@PathVariable Integer id) {
		shoppingCartService.delete(id);
		return "redirect:/public/cart";
	}
	
	@RequestMapping(value = "/public/shoppingcarts/deleteall/{id}", method = RequestMethod.GET)
	public String deleteAllProductsFromCart(@PathVariable Integer id) {
		shoppingCartService.deleteCartForSpecificUser(id);
		return "redirect:/public/cart";
	}

	private Product convertFromDto(ProductDto productDto) {
		Product product = new Product();
		product.setArtist(productDto.getArtist());
		product.setGenre(product.getGenre());
		product.setDescription(productDto.getDescription());
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		return product;
	}
}
