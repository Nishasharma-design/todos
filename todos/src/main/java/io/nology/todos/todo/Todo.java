package io.nology.todos.todo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nology.todos.category.BaseEntity;
import io.nology.todos.category.Category;
import jakarta.persistence.*;



@Entity
@Table(name = "todos")
public class Todo extends BaseEntity {
     
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean isCompleted = false;

    @Column(nullable = false)
    @JsonIgnore
    private Boolean isArchived;

    @Column
    private LocalDateTime dueDate;

    private String description;

    
    private Integer priority;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) //this establish Foreign key relation with category model
    private Category category;


    

    public Todo() {

    }

    public Todo(String title, Category category, boolean isCompleted, LocalDateTime dueDate, Integer priority,Boolean isArchived) {
        this.title = title;
        this.isCompleted = isCompleted;
        this.category = category;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isArchived = isArchived;
    }

//getter and setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//getter and setter for isCompleted
    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }

//getter and setter for isArchived
    public Boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Boolean archived) {
          isArchived = archived;
    }


//getter and setter for dueDate
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


//getter and setter for category
    public Category getCategory() {
        return category;
    }

     public void setCategory(Category category) {
        this.category = category;
     }

//getter and setter for priority

     public Integer getPriority() {
        return priority;
     }

     public void setPriority(Integer priority) {
        this.priority = priority;
     }

}
