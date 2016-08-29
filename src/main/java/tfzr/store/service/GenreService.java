package tfzr.store.service;

import java.util.List;

import tfzr.store.model.Genre;

public interface GenreService {

	public List<Genre> findAll();
	
	public Genre findOne(Integer id);
	
	public Genre save(Genre genre);
	
	public void delete(Integer id);
}
