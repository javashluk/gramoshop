package tfzr.store.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.TransactionalException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import tfzr.store.model.Product;
import tfzr.store.repository.ProductRepository;
import tfzr.store.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;
	
	@Inject
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		try{
			productRepository.delete(id);
		}catch(TransactionalException | DataAccessException e){
			
		}
		
	}

	@Override
	public List<Product> findByGenre(Integer genreId) {
		return productRepository.findByGenre(genreId);
	}

	@Override
	public List<Product> findByArtist(String artist) {
		return productRepository.findByArtist(artist);
	}

	@Override
	public Product findOne(Integer productId) {
		return productRepository.findOne(productId);
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}
}
