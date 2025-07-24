package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	private AuthenticationService authenticationService;
		
	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String gotoLoginJsp(){
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model){
		model.put("name", name);

		// demo authentication
		// name - in28minutes
		// password - dummy
		// redirect to welcome if authenticated, else back to login page

		if (authenticationService.authenticate(name, password)) {
			return "welcome";
		}
		model.put("errorMsg", "Invalid credential. Please try again.");
		return "login";
	}
}
