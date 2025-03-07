package io.nology.todos.category;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;


    //Getters
    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    

}


/* @MappedSuperclass - this tells hibernate that this is a base class, not a table
                     - @MapperSuperclass means this wont become a separate table but
                     - just a reusable base class

  @GeneratedValue(strategy = GenerationType.IDENTITY) - Automatically generated id
                            - we use GenerationType.IDENTITY for id bcz it tells the
                            - database to auto-increment the ID.

  @CreationTimestamp - Auto-fills createdAt when the entity is first saved
                     - I am using @CreationTimestamp and @UpdateTimestamp 
                     - to automatically track when an entity is created or updated 
                     - without manually setting it.
  @UpdateTimestamp - Auto-updates updatedAt whenever the entity is modified

  
 * 
 */


/* why we need BaseEntity?
-- every entity ( Category, Todo ) will have common fields
like id(primary key) , createdAt(When it was created), updatedAt(when it was last updated)


*/
