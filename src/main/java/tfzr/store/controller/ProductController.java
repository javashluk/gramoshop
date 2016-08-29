package tfzr.store.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tfzr.store.common.AbstractCommon;
import tfzr.store.model.Genre;
import tfzr.store.model.Product;
import tfzr.store.model.ProductDto;
import tfzr.store.service.GenreService;
import tfzr.store.service.ProductService;
import tfzr.store.service.ShoppingCartService;

@Controller
public class ProductController extends AbstractCommon{

	final private GenreService genreService;
	
	final private ShoppingCartService shoppingCartService;

	@Inject
	public ProductController(ProductService productService,
							 GenreService genreService, ShoppingCartService shoppingCartService) {
		super();
		this.productService = productService;
		this.genreService = genreService;
		this.shoppingCartService = shoppingCartService;
	}

	@RequestMapping(value = "/public/index", method = RequestMethod.GET)
	public String userIndex(Model model){
		model.addAttribute("genres", genreService.findAll());
		model.addAttribute("artists", findAllArtists());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("count", shoppingCartService.countCartsByUser(getCurrentUser()));
		model.addAttribute("addToCart", new ProductDto());
		return "userIndex";
	}
	
	@RequestMapping(value = "/public/products/genre/{genreId}", method = RequestMethod.GET)
	public String productsByGenre(Model model, @PathVariable Integer genreId){
		model.addAttribute("genres", genreService.findAll());
		model.addAttribute("artists", findAllArtists());
		model.addAttribute("products", productService.findByGenre(genreId));
		model.addAttribute("count", shoppingCartService.countCartsByUser(getCurrentUser()));
		model.addAttribute("addToCart", new ProductDto());
		return "productsList";
	}
	
	@RequestMapping(value = "/public/products/artist/{artist}", method = RequestMethod.GET)
	public String productsByArtist(Model model, @PathVariable String artist){
		model.addAttribute("genres", genreService.findAll());
		model.addAttribute("artists", findAllArtists());
		model.addAttribute("products", productService.findByArtist(artist));
		model.addAttribute("count", shoppingCartService.countCartsByUser(getCurrentUser()));
		model.addAttribute("addToCart", new ProductDto());
		return "productsList";
	}
	
	@RequestMapping(value = "/admin/products", method = RequestMethod.GET)
	public String adminProducts(Model model){
		model.addAttribute("addProduct", new Product());
		model.addAttribute("products", productService.findAll());
		return "products";
	}
	
	@RequestMapping(value = "/admin/product/delete/{productId}", method = RequestMethod.GET)
	public String deleteGenre(@PathVariable Integer productId){
		productService.delete(productId);
		return "redirect:/admin/products";
	}
	
	@RequestMapping(value = "/admin/product/edit/{productId}", method = RequestMethod.GET)
	public String editGenre(@PathVariable Integer productId, Model model){
		model.addAttribute("productToSave", new Genre());
		model.addAttribute("genres", genreService.findAll());
		model.addAttribute("product", productService.findOne(productId));
		return "editProduct";
	}
	
	@RequestMapping(value = "/admin/product/save", method = RequestMethod.POST)
	public String saveGenre(@ModelAttribute("productToSave") Product product){
		productService.save(product);
		return "redirect:/admin/products";
	}
	
	@RequestMapping(value = "/admin/product/add", method = RequestMethod.GET)
	public String addGenre(Model model){
		model.addAttribute("genres", genreService.findAll());
		model.addAttribute("product", new Product());
		return "editProduct";
	}
}
