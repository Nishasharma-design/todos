package io.nology.todos.todo;

import java.util.List;

import org.springframework.stereotype.Service;

import io.nology.todos.category.Category;
import io.nology.todos.category.CategoryRepository;
import io.nology.todos.category.NotFoundException;

@Service
public class TodoService {
    
    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;


    public TodoService(TodoRepository todoRepository, CategoryRepository categoryRepository) {
        this.todoRepository = todoRepository;
        this.categoryRepository = categoryRepository;
    }

    // create a new todo
    public Todo createTodo(CreateTodoDTO data) {
        Todo todo = new Todo();
        todo.setTitle(data.getTitle());
        todo.setDescription(data.getDescription());
       Category category = categoryRepository.findById(data.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found"));

        todo.setCategory(category);
        todo.setDueDate(data.getDueDate());
        todo.setPriority(data.getPriority());
        todo.setIsArchived(false); //Defaults to false when creating
        todo = todoRepository.save(todo);

        return todo;
    }

    // fetch all non-archived todos and returns the list of all active todos
    public List<Todo> getTodos() {
        return todoRepository.findByIsArchivedFalse();
    }

     // Find todo by id (excluding archived todos)
     public Todo getTodoById(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        if (todo.getIsArchived()) {
            throw new NotFoundException("Todo not found");
        }
        return todo;
    }



    // Soft delete todo (archive it)
    public Todo softDeleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NotFoundException("Todo not found"));
        
        todo.setIsArchived(true);
        return todoRepository.save(todo);
   
    }

   //Duplicate a Todo

  public Todo duplicateTodo(Long todoId) {
    Todo originalTodo = todoRepository.findById(todoId).orElseThrow(() -> new NotFoundException("Todo not found"));

    Todo duplicateTodo = new Todo();
    duplicateTodo.setTitle(originalTodo.getTitle());
    duplicateTodo.setDescription(originalTodo.getDescription());
    duplicateTodo.setCategory(originalTodo.getCategory());
    duplicateTodo.setIsArchived(false);
    duplicateTodo.setDueDate(originalTodo.getDueDate());
    duplicateTodo.setPriority(originalTodo.getPriority());

    return todoRepository.save(duplicateTodo);
  }


    // updating the todo
    
    public Todo updateTodo(Long todoId, UpdateTodoDTO data) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NotFoundException("Todo not found with id: " + todoId));
    
        todo.setTitle(data.getTitle());
        todo.setDescription(data.getDescription());

        // Fetch the category by categoryId for update
        Category category = categoryRepository.findById(data.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + data.getCategoryId()));
        
        todo.setCategory(category);  // Set the updated category object
        todo.setDueDate(data.getDueDate());
        todo.setPriority(data.getPriority());
    
        return todoRepository.save(todo);
    }

    // Fetch todos by categoryId

      // Fetch todos by categoryId
      public List<Todo> getTodosByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        
        // Fetch todos for the given category
        return todoRepository.findByCategory(category);
    }

    // to check that todos are archived and not hard deleted
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

}




 
