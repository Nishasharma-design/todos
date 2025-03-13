import { useState } from "react";



const AddTodoForm = ({ categories, addTodo }: { 
    categories: { id: number; name: string }[]; 
    addTodo: (newTodo: { title: string; categoryId: number }) => Promise<void>; 
}) => {
    const [newTodoTitle, setNewTodoTitle] = useState("");
    const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(null);
    
    const handleSubmit = async () => {
        if (newTodoTitle && selectedCategoryId !== null) {
            await addTodo({ title: newTodoTitle, categoryId: selectedCategoryId });

            
            setNewTodoTitle(""); // resets todo title back to empty 
            setSelectedCategoryId(null); // resets category space back to empty
        }
    };

    return (
        <div className="bg-white shadow-md p-4 rounded-lg">
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