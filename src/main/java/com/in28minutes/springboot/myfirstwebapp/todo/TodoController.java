package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ch.qos.logback.core.model.Model;

@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoService;

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	// list-todos
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("in28minutes");
		model.put("todos", todos);
		return "listTodos";
	}
	
	//redirect to add-todo page
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showNewTodo(ModelMap model) {
		String username = (String)model.get("username");
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap model, Todo todo) {
		String username = (String) model.get("name");
		LocalDate dueDate = LocalDate.now().plusYears(1);
		todoService.addTodo(username, todo.getDescription(), dueDate, false);
		return "redirect:list-todos";
	}
}
