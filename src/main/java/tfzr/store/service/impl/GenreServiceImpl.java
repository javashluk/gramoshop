package tfzr.store.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.TransactionalException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import tfzr.store.model.Genre;
import tfzr.store.repository.GenreRepository;
import tfzr.store.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
	
	private GenreRepository genreRepository;
	
	@Inject
	public GenreServiceImpl(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

	@Override
	public Genre save(Genre genre) {
		return genreRepository.save(genre);
	}

	@Override
	public void delete(Integer id) {
		try{
			genreRepository.delete(id);
		}catch(TransactionalException | DataAccessException e){
			
		}
	}

	@Override
	public Genre findOne(Integer id) {
		return genreRepository.findOne(id);
	}
}
