package com.practice.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

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
@SessionAttributes("name")
public class TodoControllerJpa {

	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
//		this.todoService = todoService;
		this.todoRepository=todoRepository;
	}


//	private TodoService todoService;
	private TodoRepository todoRepository;
	
	@RequestMapping(value="list-todos")
	public String todo(ModelMap map) {
		List<Todo> todos=todoRepository.findByUsername(getLoggedInUsernae(map));
//		List<Todo> todos=todoService.findByUsername(getLoggedInUsernae(map));
		map.addAttribute("todos",todos);
		return "listTodos";
	}

	
	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String showNewTodo(ModelMap map) {
		Todo todo=new Todo(0, getLoggedInUsernae(map), "", LocalDate.now().plusYears(3), false);
		map.put("todo", todo);
		return "addTodo";
	}
	@RequestMapping(value="add-todo",method = RequestMethod.POST)
	public String addNewTodo( ModelMap map,@Valid Todo todo, BindingResult resul) {
		
		if(resul.hasErrors()) {
			return "addTodo";
		}
		String username=getLoggedInUsernae(map);
		todo.setUsername(username);
		todoRepository.save(todo);
		
//		todoService.addTodo(1,getLoggedInUsernae(map), todo.getDescription(), todo.getTargetDate(),todo.isDone());	
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="delete-todo")
	public String deleteTodo(ModelMap map, @RequestParam int id) {
		todoRepository.deleteById(id);
//		todoService.deleteTodo(id);
		return "redirect:list-todos";
	}
	@RequestMapping(value="update-todo",method = RequestMethod.GET)
	public String updateTodo(ModelMap map,@RequestParam int id) {
		Todo todo=todoRepository.findById(id).get();
		map.addAttribute("todo", todo);
		return "addTodo";
	}
	@RequestMapping(value="update-todo",method = RequestMethod.POST)
	public String updateNewTodo( ModelMap map,@Valid Todo todo, BindingResult resul) {
		
		if(resul.hasErrors()) {
			return "addTodo";
		}
		String username=getLoggedInUsernae(map);
		todo.setUsername(username);
		todoRepository.save(todo);
//		todoService.updateTodo(todo);
		
		return "redirect:list-todos";
	}
	private String getLoggedInUsernae(ModelMap map) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}

