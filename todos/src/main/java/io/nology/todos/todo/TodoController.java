package io.nology.todos.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    
     private final TodoService todoService;


     public TodoController(TodoService todoService) {
        this.todoService = todoService;
     }

     //Create a new Todo
     @PostMapping
     public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo); // this is calling service layer to do logic and save in db
        return ResponseEntity.ok(createdTodo);
     }

     @GetMapping
     public ResponseEntity<List<Todo>> getTodosByCategory(Long categoryId) {
        List<Todo> todos;
        if (categoryId != null) {
            todos = todoService.getTodosByCategory(categoryId);
        } else {
            todos = todoService.getTodos();
        }
        return ResponseEntity.ok(todos);
        
     }

     @GetMapping("/{id}")
     public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        if (todo.isPresent()) {
            return ResponseEntity.ok(todo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Update an existing Todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        Todo todo = todoService.updateTodo(id, updatedTodo);
        if (todo != null) {
            return ResponseEntity.ok(todo);
        }
        return ResponseEntity.notFound().build();
    }
    
    


}


/* public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
    Todo createdTodo = todoService.createTodo(todo);
    
    Meaning of above:-  ResponseEntity<Todo> - this defines the return type
      it is a wrapper that provides extra info along with response like HTTP status codes
      the actual response body will contain a Todo object
    
      @RequestBody Todo todo - now this tells spring that the incoming JSON 
      request body should be converted into Todo java object

      
    */
