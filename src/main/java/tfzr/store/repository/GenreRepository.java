package tfzr.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfzr.store.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{
	
}
