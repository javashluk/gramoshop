package tfzr.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tfzr.store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT pr FROM tfzr.store.model.Product pr WHERE pr.genre.id = ?1")
	List<Product> findByGenre(Integer id);
	
	@Query("SELECT pr FROM tfzr.store.model.Product pr WHERE pr.artist = ?1")
	List<Product> findByArtist(String artist);
}
