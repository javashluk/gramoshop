package tfzr.store.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tfzr.store.common.AbstractCommon;
import tfzr.store.model.Role;
import tfzr.store.model.User;

@Controller
public class UserController extends AbstractCommon{
	
	public UserController() {
		super();
	}

	@RequestMapping(value = "/public/user/edit", method = RequestMethod.GET)
	public String userEdit(Model model){
		model.addAttribute("user", getCurrentUser());
		model.addAttribute("userToSave", new User());
		return "editAccount";
	}
	
	@RequestMapping(value = "/public/user/save", method = RequestMethod.POST)
	public String userEditSave(@ModelAttribute("${userToSave}") User user){
		user.setRole(Role.USER);
		userService.save(user);
		return "redirect:/public/index";
	}
	
	@RequestMapping(value = "/admin/user/save", method = RequestMethod.POST)
	public String approveUser(@ModelAttribute("${userToSave}") User user){
		User userToUpdate = userService.findByUsername(user.getUsername());
		userToUpdate.setRole(Role.USER);
		userService.save(userToUpdate);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/admin/user/delete/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Integer userId){
		userService.delete(userId);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/registration/new", method= RequestMethod.POST)
	public String register(@ModelAttribute("${registration}") User user, BindingResult errors){
		user.setRole(Role.NULL);
		userService.save(user);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/admin/users")
	public String approveUsers(Model model){
		model.addAttribute("unapprovedUsers", userService.findAllUnapproved(Role.NULL));
		model.addAttribute("${userToSave}", new User());
		return "approveUser";
	}
}
