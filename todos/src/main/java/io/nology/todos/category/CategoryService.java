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
   
    //constructor automatically injects CategoryRepo and ModelMappr into this service
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository; //here i am assigning injected categoryRepository to categoryRepository field
        this.modelMapper = modelMapper;   
    }
//** A class is defined using class and a method is method a class and has a return type */

    // ctreate a new category
//convert DTO into category entity using modelMapper
    public Category createCategory(CreateCategoryDTO data) { //this is createCategory method
        Category newCategory = modelMapper.map(data, Category.class); //converts DTOs into Category entity 
        return categoryRepository.save(newCategory); //save new category in db.
    }

    // get all categories
    public List<Category> getAllCategories() { //this method returns a list of category entities
        return categoryRepository.findAll(); //retrieve all categories from db
    }

    // get a single category by ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id); //return Optional<Category>
    }

    // Update category by ID 
    public Optional<Category> updateCategoryById(Long id, UpdateCategoryDTO data) {
        Optional<Category> categoryOptional = getCategoryById(id); //this line checks whether the category with the given id exists by calling getCategoryById()

        if (categoryOptional.isEmpty()) { //this checks whether categoryOptional is empty.
            return Optional.empty(); //if it is empty, this means the category with the given id dont exist in db, so it return an empty Optional
        }

        Category categoryToUpdate = categoryOptional.get(); //this extracts category object from Optional
        modelMapper.map(data, categoryToUpdate); // this converts UpdateCategoryDTO (data) into existing categoryToUpdate, updates fields of the category with the new values

        categoryRepository.save(categoryToUpdate); // save the updates category in db
        return Optional.of(categoryToUpdate);

    } 

    // Delete category by ID (Hard delete)

    // public boolean deleteCategoryById(Long id) {
    //     Optional<Category> categoryOptional = getCategoryById(id);

    //     if (categoryOptional.isEmpty()) {
    //         return false;
    //     }

    //     categoryRepository.delete(categoryOptional.get());
    //     return true;
    // }


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
    
     }


/*  why service layer?
 ---- the service layer contains business logic and acts as a bridge between the
 --- controller (API endpoints) and the repository (database operations).
 
 ** It ensures clean separation of concerns (controller only handles HTTP requests,
    repository only interacts with the Database. ) 
 
 *** It allows us to reuse logic in different parts of the 
     application (e.g., validation, transformations, etc.)

  *** By implementing CategoryService first, we can properly handle CRUD operations before exposing them
      in the controller.

  *** The controller shud not contain business logic , 
      it shud call the service



      🔹 Why do we use a service layer in Spring Boot?
✔️ It helps separate business logic from controllers and repositories, making the code more maintainable.

🔹 Why are we using ModelMapper?
✔️ It helps convert DTOs into entities automatically, reducing boilerplate code.

🔹 Why is Optional used in getCategoryById()?
✔️ It helps handle cases where the category might not exist, preventing NullPointerException.

🔹 What happens if you delete a category that has associated todos?
✔️ If the relationship is mapped correctly with @OneToMany, Spring/Hibernate prevents deletion unless todos are handled first. 
 */

 /* **About Code**
 @Service - it tells Spring Boot that this is a service component, Spring will automatically manage
  * it ensures that the class can be injected into other parts of app 

  categoryRepository - interact with database( perform CRUD operations )
  modelMapper - convert DTOs into entity objects.

  */


  /* 
     Cmd + Ctrl + Space to add emojis

   * This class is responsible for:
Processing the data (converting DTOs to entities, validating, etc.)
 Interacting with the database via CategoryRepository
 Performing CRUD operations (create, read, update, delete categories)

 It does not handle HTTP requests—it only performs business logic!
   * 
   * 
   * 
   */

