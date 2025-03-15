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
}



/* public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
    Todo createdTodo = todoService.createTodo(todo);
    
    Meaning of above:-  ResponseEntity<Todo> - this defines the return type
      it is a wrapper that provides extra info along with response like HTTP status codes
      the actual response body will contain a Todo object
    
      @RequestBody Todo todo - now this tells spring that the incoming JSON 
      request body should be converted into Todo java object

      
  @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    //  Get a single Todo, handling 404 errors
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = Optional.ofNullable(todoService.getTodoById(id));
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

✔️ The frontend sends a DELETE /todos/{id} request.
✔️ Spring Boot maps it to @DeleteMapping("/{id}").
✔️ Instead of deleting, softDeleteTodo() sets isArchived = true.
✔️ Future GET /todos requests only return non-archived todos, so archived ones "disappear" from the UI.



    */
