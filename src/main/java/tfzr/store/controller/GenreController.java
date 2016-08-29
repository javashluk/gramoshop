package tfzr.store.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tfzr.store.model.Genre;
import tfzr.store.service.GenreService;

@Controller
public class GenreController {

	private final GenreService genreService;
	
	@Inject
	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}
	
	@RequestMapping(value = "/admin/genre", method = RequestMethod.GET)
	public ModelAndView allCategories(Model model){
		model.addAttribute("editGenre", new Genre());
		model.addAttribute("genres", genreService.findAll());
		return new ModelAndView("genre");
	}
	
	@RequestMapping(value = "/admin/genre/delete/{genreId}", method = RequestMethod.GET)
	public String deleteGenre(@PathVariable Integer genreId){
		genreService.delete(genreId);
		return "redirect:/admin/genre";
	}
	
	@RequestMapping(value = "/admin/genre/edit/{genreId}", method = RequestMethod.GET)
	public String editGenre(@PathVariable Integer genreId, Model model){
		model.addAttribute("genreToSave", new Genre());
		model.addAttribute("genre", genreService.findOne(genreId));
		return "editGenre";
	}
	
	@RequestMapping(value = "/admin/genre/save", method = RequestMethod.POST)
	public String saveGenre(@ModelAttribute("genreToSave") Genre genre){
		genreService.save(genre);
		return "redirect:/admin/genre";
	}
	
	@RequestMapping(value = "/admin/genre/add", method = RequestMethod.GET)
	public String addGenre(Model model){
		model.addAttribute("genre", new Genre());
		return "editGenre";
	}
}
