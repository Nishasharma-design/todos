package io.nology.todos.category;

import jakarta.validation.constraints.NotBlank;


// I will use DTO to receive data from client and i use model mapper to map DTO into category class
public class CreateCategoryDTO {
    
    @NotBlank(message = "Category name cannot be empty")
    private String name;
    

    public String getName() {
        return name;
    }
}


// DTO are used to receive and validate user input 
// 


/* we use DTOs in CategoryService.

*** It ia A class that stores input data from a user

 * 
 * we need CreateCategoryDTO to ensure name is not empty
 * ** to prevent clients from sending unnecessary data
 * for example forcing only name field for category creation
 */

 /*  DTOs are created to define what data should be sent when 
     creating or updating a category
  * Instead of using the full Category entity, these DTOs ensure
  only necessary fields are passed.


  */
