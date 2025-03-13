import { useState } from "react"


const AddCategoryForm = ({ addCategory }: { addCategory: (name: string) => Promise<void> }) => {
    const [categoryName, setCategoryName] = useState("");

    const handleSubmit = async () => {
        if (!categoryName) return;
        await addCategory(categoryName);
        setCategoryName(""); // Reset input field
    };

    return (
        <div className="bg-white shadow-md p-4 rounded-lg">
            <h3 className="text-lg font-semibold mb-2">Add New Category</h3>
            <input
               type="text"
               placeholder="Category name"
               value={categoryName}
               onChange={(e) => setCategoryName(e.target.value)}
               className="border rounded px-2 py-1 w-full"
               />
               <button className="bg-green-500 text-black px-3 py-1 rounded hover:bg-green-600"
                onClick={handleSubmit}>
                    Add Category
                </button>

        </div>
    );
};

export default AddCategoryForm;