package io.nology.todos.todo;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import io.nology.todos.category.CategoryRepository;

@Service
public class TodoService {
    
    private final TodoRepository todoRepository;
    //private final CategoryRepository categoryRepository;


   
    public TodoService(TodoRepository todoRepository, CategoryRepository categoryRepository) {
        this.todoRepository = todoRepository;
        //this.categoryRepository = categoryRepository;
    }

    // create a new todo
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // get all todos
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // get todos by category ID
    public List<Todo> getTodosByCategory(Long categoryId) {
        return todoRepository.findByCategoryId(categoryId);
    }

    // Soft delete todo (archive it)
    public Todo softDeleteTodo(Long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setIsArchived(true);
            return todoRepository.save(todo); // retaining the task in db but hide it from active list
        }
        return null;
    }

    public Todo duplicateTodo(Long todoId) {
     
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();

            // creating a new Todo instance (copy)
            Todo duplicateTodo = new Todo();
            duplicateTodo.setTitle(todo.getTitle());
            duplicateTodo.setCategory(todo.getCategory());
            duplicateTodo.setIsArchived(false);

            return todoRepository.save(duplicateTodo);
        }

        return null;

    }

    // updating the todo
    public Todo updateTodo(Long todoId, Todo updatedTodo) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
       
        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();

            // updating the fields

            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setCategory(updatedTodo.getCategory());

            existingTodo.setDueDate(updatedTodo.getDueDate());

            return todoRepository.save(existingTodo);
        }
        return null;

    }

     // Get a todo by its ID
     public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

}



/*
 * The TodoService class is responsible for handling all the business logic related to Todo tasks. 
 * It acts as an intermediary between the TodoController (which handles API requests)
 *  and the TodoRepository (which interacts with the database).
 * 
 */
