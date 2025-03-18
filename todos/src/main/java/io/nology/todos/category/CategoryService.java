package io.nology.todos.category;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import io.nology.todos.todo.TodoRepository;

@Service
public class CategoryService {
  
    private final CategoryRepository categoryRepository; //used to interact with database
    private final ModelMapper modelMapper; //convert DTOs into entity objects
    private TodoRepository todoRepository;
   

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper, TodoRepository todoRepository) {
        this.categoryRepository = categoryRepository; //here i am assigning injected categoryRepository to categoryRepository field
        this.modelMapper = modelMapper; 
        this.todoRepository = todoRepository;   
    }

    public Category createCategory(CreateCategoryDTO data) { //this is createCategory method
        Category newCategory = modelMapper.map(data, Category.class); //converts DTOs into Category entity 
        return categoryRepository.save(newCategory); //save new category in db.
    }

    // get all categories
    public List<Category> getAllCategories() { 
        return categoryRepository.findAll(); 
    }

    // get a single category by ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id); 
    }

    // Update category by ID 
    public Optional<Category> updateCategoryById(Long id, UpdateCategoryDTO data) {
        Optional<Category> categoryOptional = getCategoryById(id); //this line checks whether the category with the given id exists by calling getCategoryById()

        if (categoryOptional.isEmpty()) { 
            return Optional.empty(); 
        }

        Category categoryToUpdate = categoryOptional.get(); //this extracts category object from Optional
        modelMapper.map(data, categoryToUpdate); // this converts UpdateCategoryDTO (data) into existing categoryToUpdate, updates fields of the category with the new values

        categoryRepository.save(categoryToUpdate); // save the updates category in db
        return Optional.of(categoryToUpdate);

    } 



    // soft delete category
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Category with ID " + id + " not found"));

        // Soft delete by setting isArchived to true
        category.setIsArchived(true);
        categoryRepository.save(category);
    }

    public List<Category> getActiveCategories() {
        return categoryRepository.findByIsArchivedFalse();
    }

    public void hardDeleteCategory(Long categoryId) {
        // Check if the category exists
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found!");
        }

        // Ensure that no todos are linked to this category before deletion
        long relatedTodosCount = todoRepository.countByCategoryId(categoryId);
        if (relatedTodosCount > 2) {
            throw new RuntimeException("Cannot delete category with existing todos!");
        }

        // Delete category permanently
        categoryRepository.deleteById(categoryId);
    }
}
    
     



