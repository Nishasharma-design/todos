package io.nology.todos.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Category categoryId);

    List<Category> findByIsArchivedFalse();  // Fetch only non-archived categories
    
}



/* Why we need a Repository
--- The repository is responsible for database operations(CRUD - Create, Read, Update, Delete)
 instead of writing SQL queries manually, we use Spring Data, JPA to interact
 with the database easily.


 * 
 * 
 */



 /*  Does switiching databases (H2 - MySQL) require changes in entities?
     --- No, bcz JPA makes entity classes independent of the database
     
    What is the role of Hibernate in this process?
      --- Hibernate converts Java objects into SQL queries based on the database dialect
*
    What happens if we dont set spring.jpa.database-platform?
     --- Hibernate will try to detect the database type
     
    How does JpaRepository work with different databases?
     --- It dynamically generates SQL queries based on the database
     --- dialect without modifying code.


     JPA (Java Persistence API) is Database-Agnostic

We are using JPA annotations (@Entity, @Table, @Column, etc.), which work independently of the database type.
Whether it's H2, MySQL, or PostgreSQL, JPA will generate the correct SQL queries for that database.
2 Spring Data JPA Handles the Database Operations

The Repository layer (CategoryRepository) extends JpaRepository, which abstracts database operations.
It automatically generates queries for MySQL without modifying the entity or repository.
3️ Hibernate Dialect Takes Care of Database-Specific Syntax

We set spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect in application.properties.
Hibernate translates our JPA entity mappings into MySQL-compatible SQL statements.
  */


  /* this interface is responsible for interacting with the database 
   * and performing CRUD operations like Create, Read, Update, Delete
   * It extends JpaRepository<Category, Long>, which provides built-in
   * operations.
   * JpaRepository provides methods like save(), findAll(), findById(),
   * deleteById()
   * 
   */