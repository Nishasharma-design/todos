package io.nology.todos.category;

import java.util.List;

import jakarta.persistence.*;

//we use below bcz, if we fetch category, it also fetches Todos, which fetches Category again- causing recursion
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.nology.todos.todo.Todo; 

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true) //name must be unique to avoid duplicacy
    private String name;

    private boolean isArchived = false; //Soft delete

    @OneToMany(mappedBy = "category") //one category can have multiple todos
    @JsonIgnoreProperties("category")
    private List<Todo> todos; // A list to store Todo objects


    public Category(String name) {
        this.name = name; 
    }

    public Category() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodos() { //List<Todo> is the return type of the method name
        return todos; //returns the list of Todo objects stored in the todos variable.
    }

    // Getter and Setter
    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    
}



/* 
 * This Category class maps to the category table in MySQL.
     Every Category object corresponds to a row in the database.
 * 
 * 
 * JsonIgnoreProperties***
 */
