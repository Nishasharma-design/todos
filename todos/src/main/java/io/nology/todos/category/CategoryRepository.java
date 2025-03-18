package io.nology.todos.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Category categoryId);

    List<Category> findByIsArchivedFalse();  // Fetch only non-archived categories
    
}



