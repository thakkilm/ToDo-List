package com.practice.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;


@Controller
public class TodoService {
  private static List<Todo> todo=new ArrayList<Todo>();
  private static int count=0;
  
  static {
	  todo.add(new Todo(++count, "Mahesh", "one", LocalDate.now().plusYears(1), false));
	  todo.add(new Todo(++count, "Mahesh", "Two", LocalDate.now().plusYears(2), false));
	  todo.add(new Todo(++count, "Mahesh", "Three", LocalDate.now().plusYears(3), false));
  }
  
  
  public List<Todo> findByUsername(String username){
	  Predicate<? super Todo>predicate=todos->todos.getUsername().equalsIgnoreCase(username);
	  return todo.stream().filter(predicate).toList();
  }
  public void addTodo(int number,String username,String description, LocalDate date, boolean bool) {
	   todo.add(new Todo(++count,username,description,date,false));
  }
  public void deleteTodo(int id) {
	  for (Todo todo2 : todo) {
		  if(todo2.getId()==id) {
			
			  todo.remove(todo2);
			  break;
		  }		
	}
  }
public Todo findByID(int id) {
	for (Todo todo2 : todo) {
		  if(todo2.getId()==id) {
			
			  return todo2;
		  }
		 
	}
	 return null;
	
}
public void updateTodo(@Valid Todo todo2) {
	// TODO Auto-generated method stub
	
	deleteTodo(todo2.getId());
	todo.add(todo2);
	
}
}
