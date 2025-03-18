const BASE_URL = 'http://localhost:8080/todos'; // this is my Backend API URL


//fxn to fetch all todos
export const getTodos = async () => {
    try {
        const response = await fetch(`${BASE_URL}`);
        if (!response.ok) {
            throw new Error("Failed to fetch todos");
        }
        return response.json(); // Return the parsed data
    } catch (error) {
        console.error("Error fetching todos:", error);
        throw error; // Propagate the error
    }
};



export const addTodo = async (todoData: { title: string; categoryId: number }) => {
    const response = await fetch(`${BASE_URL}`, {
        method: "POST", //tells server I am sending new data
        headers: { "Content-Type": "application/json" }, // tells the server that sending JSON data
        body: JSON.stringify(todoData), // Converts JavaScript Object to JSOn string

    });
    return response.json(); // converts response received to JSON format
};

// Soft delete a todo by setting isArchived to true
export const deleteTodo = async (id: number) => {
    await fetch(`${BASE_URL}/${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ isArchived: true }),
    });
    return getTodos(); //refresh todos after deletion
};


export const updateTodo = async (id: number, updatedData: { title?: string; categoryId?: number; isArchived?: boolean }) => {

    
    const response = await fetch(`${BASE_URL}/${id}`, {
        method: "PUT", // or PATCH if partial updates are allowed
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedData), // Convert the updated data to JSON format
    });
    return response.json(); // Convert the response to JSON
};


// fetching categories



export const getCategories = async () => {
    const response = await fetch('http://localhost:8080/category');
    return response.json();
};

export const addNewCategory = async (name: string) => {
    const response = await fetch('http://localhost:8080/category', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ name }),
    });

    if (!response.ok) {
        throw new Error("Failed to add Category");
    }

    return response.json(); // return the created category
};


