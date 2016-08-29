package tfzr.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tfzr.store.common.AbstractCommon;
import tfzr.store.model.Role;
import tfzr.store.model.User;

@Controller
public class IndexController extends AbstractCommon{
	
	@RequestMapping("/login")
	public ModelAndView loginForm(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("registration", new User());
		return "registration";
	}
	
	@RequestMapping("/")
	public String home(Model model){
		
		final Role role = getCurrentUser().getRole();
		
	        switch (role.toString()) {
	            case "USER":
	            	return "redirect:/public/index";
	            case "ADMIN":
	            	return "redirect:/admin/index";
	            case "NULL":
	            	return "redirect:/login";
	        }
	        return "redirect:/login";
	}
	
	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public ModelAndView adminIndex(){
		return new ModelAndView("adminIndex");
	}

}
