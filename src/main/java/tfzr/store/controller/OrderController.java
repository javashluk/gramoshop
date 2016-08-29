package tfzr.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tfzr.store.common.AbstractCommon;
import tfzr.store.model.Cart;
import tfzr.store.model.ProductOrder;
import tfzr.store.repository.OrderRepository;
import tfzr.store.service.OrderService;
import tfzr.store.service.ShoppingCartService;

@Controller
public class OrderController extends AbstractCommon{

	private final OrderService orderService;
	private final OrderRepository orderRepository;
	private final ShoppingCartService shoppingCartService;
	
	@Inject
	public OrderController(OrderService orderService, OrderRepository orderRepository, ShoppingCartService shoppingCartService) {
		this.orderService = orderService;
		this.orderRepository = orderRepository;
		this.shoppingCartService = shoppingCartService;
	}
	
	@RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
	public String showOrders(Model model) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("user", getCurrentUser());
		model.addAttribute("orders", orderService.findAll());
		return "orders";
	}
	
	@RequestMapping(value = "/public/orders/buy", method= RequestMethod.GET)
	public String buyProducts(){
		Set<Cart> cartsByUser = shoppingCartService.findCartByUser(getCurrentUser());
		List<ProductOrder> productOrders = convertToProductOrders(cartsByUser);
		orderRepository.save(productOrders);
		shoppingCartService.deleteCartForSpecificUser(getCurrentUser().getId());
		return "redirect:/public/cart";
	}
	
	@RequestMapping(value = "/admin/orders/delete", method= RequestMethod.GET)
	public String deleteReport(){
		orderRepository.delete(orderRepository.findAll());
		return "orders";
	}
	
	@RequestMapping(value = "/admin/orders/delete/{orderId}", method= RequestMethod.GET)
	public String deleteSpecificOrder(@PathVariable Integer orderId){
		orderRepository.delete(orderRepository.findOne(orderId));
		return "redirect:/admin/orders";
	}

	private List<ProductOrder> convertToProductOrders(Set<Cart> cartsByUser) {
		List<ProductOrder> productOrders = new ArrayList<ProductOrder>();
		cartsByUser.stream().forEach(c->{
			ProductOrder order = new ProductOrder();
			order.setProduct(c.getProduct());
			order.setQuantity(c.getQuantity());
			order.setUser(getCurrentUser());
			productOrders.add(order);
		});
		return productOrders;
	}
}
