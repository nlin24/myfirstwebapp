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
	
	@GetMapping(path = "/say-hello-html")
	public String sayHelloHtml() {
		/*
		 * <html>
		 * <head><title>My first HTML page</title></head>
		 * <body>My first HTML page with body</body>
		 * </html>
		 * */
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head><title>My first HTML page</title></head>");
		sb.append("<body>My first HTML page with body</body>");
		sb.append("</html>");
		return sb.toString();
	}

}
