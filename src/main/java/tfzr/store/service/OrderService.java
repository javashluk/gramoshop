package tfzr.store.service;


import java.util.List;


import tfzr.store.model.ProductOrder;

public interface OrderService {
	List<ProductOrder> findAll();
}
