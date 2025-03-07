package io.nology.todos.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    

    // find all todos by category
    List<Todo> findByCategoryId(Long categoryId);

    // find todos that are not archived
    List<Todo> findByIsArchived(boolean isArchived);
    
    // find todos by category and archived status
    List<Todo> findByCategoryIdAndIsArchived(Long categoryId, Boolean isArchived);

    
}
