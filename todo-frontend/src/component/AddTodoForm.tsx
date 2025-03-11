import { useState } from "react";



interface Category {
    id: number;
    name: string;
}

interface AddTodoFormProps {
    categories: Category[];
    addTodo: (newTodo: { title: string; categoryId: number }) => Promise<void>;
}

const AddTodoForm: React.FC<AddTodoFormProps> = ({ categories, addTodo }) => {
    const [newTodoTitle, setNewTodoTitle] = useState('');
    const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(null);

    const handleSubmit = async () => {
        if (newTodoTitle && selectedCategoryId !== null) {
            await addTodo({ title: newTodoTitle, categoryId: selectedCategoryId });
            setNewTodoTitle("");
            setSelectedCategoryId(null);
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