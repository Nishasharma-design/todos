package io.nology.todos.todo;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    
    @Autowired
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //  Create a new Todo with proper response codes
    @PostMapping
public ResponseEntity<Todo> createTodo(@RequestBody CreateTodoDTO data) {
    Todo createdTodo = todoService.createTodo(data);
    return ResponseEntity.ok(createdTodo);
}


    //  Get all Todos (no ResponseEntity needed, simple case)
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Long category) {
        List<Todo> todos;
        if (category != null) {
            todos = todoService.getTodosByCategory(category); // Fetch todos by category
        } else {
            todos = todoService.getTodos(); // Fetch all todos if no category is provided
        }
        return ResponseEntity.ok(todos);
    }
    
     // Get a single Todo by ID
     @GetMapping("/{id}")
     public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
         Todo todo = todoService.getTodoById(id); // Calls the service method to fetch todo by ID
         return ResponseEntity.ok(todo); // Returns the todo with a 200 OK response
     }
    

    //  Update a Todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody UpdateTodoDTO data) {
        Todo updatedTodo = todoService.updateTodo(id, data);
        return ResponseEntity.ok(updatedTodo);
    }

    // ✅ Soft delete (archive) a Todo
    @DeleteMapping("/{id}") //@DeleteMapping("/{id}") annotation maps the DELETE request to this method.
    public ResponseEntity<Void> softDeleteTodo(@PathVariable Long id) {
        Todo deletedTodo = todoService.softDeleteTodo(id);
        return deletedTodo != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    // to check that todos are archived and not hard deleted
    @GetMapping("/all")
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> allTodos = todoService.getAllTodos();
        return ResponseEntity.ok(allTodos);
    }

}




