package io.nology.todos.todo;

import java.time.LocalDateTime;



public class CreateTodoDTO {
    
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private boolean isArchived;
    private LocalDateTime dueDate;
    private int priority;


    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }



}


/*
 *  🛑 the yellow underline is IntelliJ IDEA warning which
 *  comes if i am not using getter
 * and setter for the fiels, getters and setters are used to 
 * access and modify these fields.
 * 
 * ✅ encapsulation is one of the core principles of OOP
 * and it suggests the fields should be private to prevent direct
 * access
 * 
 * If you're not using getters and setters, your IDE sees that the id 
 * field (or any other private field) is not being accessed in any controlled way 
 * (i.e., it isn't publicly accessible for reading or modifying). Hence, the yellow underline appears.
 * 
 * 
 * 
 */
