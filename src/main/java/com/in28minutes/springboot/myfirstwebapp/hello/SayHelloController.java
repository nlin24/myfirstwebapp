package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
//say-hello => "Hello! What are you learning today?"

	@RequestMapping("/say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello! What are you learning today?";
	}
	
	@RequestMapping("/say-hello-html")
	@ResponseBody
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
	
	@RequestMapping("/say-hello-jsp")
	public String sayHelloJsp(@RequestParam String name, ModelMap model) {
		model.put("name", name);
		return "sayHello";
	}
}
