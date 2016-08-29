package tfzr.store.service;

import java.util.List;

import tfzr.store.model.Product;

public interface ProductService{

	public List<Product> findAll();
	
	public Product findOne(Integer productId);
	
	public List<Product> findByGenre(Integer genreId);
	
	public List<Product> findByArtist(String artist);
	
	public Product save(Product product);
	
	public void delete(Integer id);
}
