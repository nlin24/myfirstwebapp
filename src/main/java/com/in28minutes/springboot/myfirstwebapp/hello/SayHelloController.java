package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {
//say-hello => "Hello! What are you learning today?"
	
	@GetMapping(path = "/say-hello")
	public String sayHello() {
		return "Hello! What are you learning today?";
	}
}
