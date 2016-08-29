package tfzr.store.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import tfzr.store.model.ProductOrder;
import tfzr.store.repository.OrderRepository;
import tfzr.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	
	@Inject
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<ProductOrder> findAll() {
		return orderRepository.findAll();
	}
}
