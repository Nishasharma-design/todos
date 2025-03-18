

import { useState } from "react";

const AddCategoryForm = ({ addCategory, categories }: { 
    addCategory: (name: string) => Promise<void>;
    categories: { id: number; name: string }[];
}) => {
    const [categoryName, setCategoryName] = useState("");
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState(false);

    const handleSubmit = async () => {
        if (!categoryName.trim()) {
            setError("Category name cannot be empty.");
            return;
        }

        // Check for duplicate category
        const isDuplicate = categories.some(cat => cat.name.toLowerCase() === categoryName.toLowerCase());
        if (isDuplicate) {
            setError("This category already exists.");
            return;
        }

        setError(null);
        setLoading(true);
        
        try {
            await addCategory(categoryName);
            setCategoryName(""); // Reset input field
        } catch (error) {
            setError("Failed to add category. Please try again.");
        } finally {
            setLoading(false);
        }
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
            {error && <p className="text-red-500 text-sm">{error}</p>}
            <button 
                className="bg-green-500 text-black px-3 py-1 rounded hover:bg-green-600 disabled:bg-gray-400"
                onClick={handleSubmit}
                disabled={loading}
            >
                {loading ? "Adding..." : "Add Category"}
            </button>
        </div>
    );
};

export default AddCategoryForm;
