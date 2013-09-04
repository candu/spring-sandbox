package net.savageinter.springpad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/sessions/new", method = RequestMethod.GET)
	public String newSessionForm(Model model) {
		model.addAttribute("user", new User());
		return "sessions/new";
	}
	
	@RequestMapping(value = "/sessions", method = RequestMethod.POST)
	public String createSession() {
		return null;
	}
	
	@RequestMapping(value = "/sessions", method = RequestMethod.DELETE)
	public String deleteSession() {
		return "redirect:/sessions/new";
	}
	
	@RequestMapping(value = "/users/new", method = RequestMethod.GET)
	public String newUser() {
		return "users/new";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String createUser(@ModelAttribute User user,
						     BindingResult result,
			 				 Model model) {
		if (result.hasErrors()) {
			return "redirect:/users/new";
		}
		// TODO: update session.userId = user.id
		return "redirect:/notes";
	}
}
