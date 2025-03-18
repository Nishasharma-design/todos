import { useState } from "react";


// todo represents a single task
interface Todo {
  id: number; 
  title: string; 
  isArchived: boolean; 
  category: { id: number; name: string }; 
}



const AddTodoForm = ({ categories, addTodo, setTodos, todos}: { 
    categories: { id: number; name: string }[]; 
    addTodo: (newTodo: { title: string; categoryId: number }) => Promise<void>;
    setTodos: React.Dispatch<React.SetStateAction<Todo[]>>;
    todos: Todo[];
}) => {
    const [newTodoTitle, setNewTodoTitle] = useState("");
    const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(null);
    
   
   
    const handleSubmit = async () => {
      if (newTodoTitle && selectedCategoryId !== null) {
        // Add new todo via the addTodo function
        await addTodo({ title: newTodoTitle, categoryId: selectedCategoryId });
  
        // Immediately update the todos state to reflect the new todo
        setTodos((prevTodos) => [
          ...prevTodos,
          {
            id: Date.now(), 
            title: newTodoTitle,
            isArchived: false, // Default value for new todos
            category: { id: selectedCategoryId, name: "Loading..." }, // Temporary placeholder
          },
        ]);
  
        setNewTodoTitle(""); // Reset todo title
        setSelectedCategoryId(null); // Reset category
      }
    };

    return (
        <div className="bg-white shadow-md p-4 rounded-lg w-full">
            <h3 className="text-lg font-semibold mb-2">Add New Todo</h3>
            <div className="flex space-x-3">
                <input
                  type="text"
                  placeholder="Todo title"
                  className="border rounded px-2 py-1 flex-grow"
                  value={newTodoTitle}
                  onChange={(e) => setNewTodoTitle(e.target.value)}
                  />
                <select
                  className="border rounded px-2 py-1"
                  onChange={(e) => setSelectedCategoryId(Number(e.target.value))}
                  value={selectedCategoryId || ""}
                  >
                    <option value="">Select category</option>
                    {categories.map((category) => (
                        <option key={category.id}  value={category.id}>{category.name}</option>
                    ))}
                  </select>
                  <button
                    className="bg-green-500 text-black px-3 py-1 rounded hover:bg-green-600"
                    onClick={handleSubmit}
                      >
                        Add Todo
                      </button>
                    </div>
        </div>
    );
 };

 export default AddTodoForm;