package io.nology.todos.todo;

import java.util.Date;

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
    private Boolean isArchived = false;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public Todo() {

    }

    public Todo(String title, Category category, Date dueDate, Priority priority) {
        this.title = title;
        this.category = category;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean archived) {
          isArchived = archived;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Category getCategory() {
        return category;
    }

     public void setCategory(Category category) {
        this.category = category;
     }


}
