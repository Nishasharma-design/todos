package io.nology.todos.category;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}




/*  super(message)  is calling the constructor of the
    parent class RuntimeException  and assing the error message 

    

    RuntimeException already has a constructor that accepts an error
    message (String message), 
    
    when we call super(message), it passes the message to the RuntimeException class, 
    allowing the exception to store and display the error message.
    
    
  */