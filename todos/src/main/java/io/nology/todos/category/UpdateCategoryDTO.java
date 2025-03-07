package io.nology.todos.category;

import jakarta.validation.constraints.NotEmpty;

public class UpdateCategoryDTO {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
