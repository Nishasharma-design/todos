package io.nology.todos.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nology.todos.category.Category;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    
  //find todos by category, but exclude archived todos
    List<Todo> findByCategoryIdAndIsArchivedFalse(Long categoryId);
    
    // Find all todos, excluding archived ones
    List<Todo> findByIsArchivedFalse();

    // Find todos by category and check archived status
    List<Todo> findByCategoryIdAndIsArchived(Long categoryId, Boolean isArchived);

    // Query to find todos by category
    List<Todo> findByCategory(Category category);

    long countByCategoryId(Long categoryId);
}


/*
 * countByCategoryId(Long) is a non-static method, meaning you must call it on an instance (todoRepository).
If you try TodoRepository.countByCategoryId(id), Java expects countByCategoryId to be static, but it's not.
 * 
 * 
 * 
 */