package io.nology.todos.category;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/category")
public class CategoryController {
    
   @Autowired
   private CategoryService categoryService;

   public CategoryController(CategoryService categoryService) {
         this.categoryService = categoryService; // it assigns the injected categoryService to the field categoryService in controller class
   }

   
   //Create a Category
   @PostMapping // This maps the method to HTTP POST requests @RequestBody - is an annotation that converts incoming JSON data from HTTP request into a java object (CreateCategoryDTO)
   public ResponseEntity<Category> createCategory(@Valid @RequestBody CreateCategoryDTO data) {
         Category newCategory = this.categoryService.createCategory(data); //Calls service layer
         return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
   }
   
   // Get All Categories
   @GetMapping
   public ResponseEntity<List<Category>> getAllCategories() { // this means the method will return list of Category objects inside ResponseEntity
       List<Category> categories = this.categoryService.getAllCategories();
       return new ResponseEntity<>(categories, HttpStatus.OK);
   }



   
   // get a category by ID
   @GetMapping("/{id}")
   public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws NotFoundException {
    Optional<Category> result = this.categoryService.getCategoryById(id); //this method checks if a category with the given id exists in database, if found, result will contain it
    // if category not found, result will be Optional.empty()
    Category foundCategory = result.orElseThrow(() -> new NotFoundException("Category with ID " + id + " not found"));
    return new ResponseEntity<>(foundCategory, HttpStatus.OK);
   }

   // update category by ID
   @PutMapping("/{id}")
   public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCategoryDTO data) throws NotFoundException {
    Optional<Category> result = this.categoryService.updateCategoryById(id, data);
    Category updatedCategory = result.orElseThrow(() -> new NotFoundException("Category with ID " + id + " not found"));
    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<Category>> getActiveCategories() {
        List<Category> categories = categoryService.getActiveCategories();
        return ResponseEntity.ok(categories);
    }


     // ✅ Hard Delete API (Deletes Permanently)
     @DeleteMapping("/hard/{categoryId}")
     public ResponseEntity<String> hardDeleteCategory(@PathVariable Long categoryId) {
         try {
             categoryService.hardDeleteCategory(categoryId);
             return ResponseEntity.ok("Category permanently deleted!");
         } catch (RuntimeException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }
}