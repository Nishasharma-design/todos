package io.nology.todos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TodosApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosApplication.class, args);
    }

    // CORS configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Allow requests from frontend running on http://localhost:5173
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}





// package io.nology.todos;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class TodosApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(TodosApplication.class, args);
// 	}

// }

/*
 * 
 * ✅ ResponseEntity is just JSON + HTTP status (like 200, 404, etc.).
✅ Java objects (like Todo) exist in the backend, but before sending them, the backend converts them into JSON.
✅ The frontend doesn’t understand ResponseEntity because it includes HTTP metadata, so it extracts just the JSON using response.json().
 */