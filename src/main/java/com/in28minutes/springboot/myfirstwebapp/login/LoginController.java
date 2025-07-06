package com.in28minutes.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String gotoLoginJsp(){
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String gotoWelcomePage(@RequestParam String name, ModelMap model){
		model.put("name", name);
		return "welcome";
	}
}
