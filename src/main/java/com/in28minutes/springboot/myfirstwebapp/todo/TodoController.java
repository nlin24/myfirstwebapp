package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
public class TodoController {

	private TodoService todoService;

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	// list-todos
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listAllTodos(ModelMap model) {
		String username = getLoggedinUsername();
		List<Todo> todos = todoService.findByUsername(username);
		model.put("todos", todos);
		return "listTodos";
	}
	
	//redirect to add-todo page
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showNewTodo(ModelMap model) {
		String username = getLoggedinUsername();
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedinUsername();
		LocalDate dueDate = todo.getTargetDate();
		todoService.addTodo(username, todo.getDescription(), dueDate, false);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todo.setUsername(getLoggedinUsername());
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggedinUsername() {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
