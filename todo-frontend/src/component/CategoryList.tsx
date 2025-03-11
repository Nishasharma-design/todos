import React from "react";



interface Category {
    id: number;
    name: string;
}

interface CategoryListProps {
    categories: Category[];
}

const CategoryList: React.FC<CategoryListProps> = ({ categories }) => {

    return (
        <div className="bg-white shadow-md p-6 rounded-lg">
      <h2 className="text-xl font-semibold mb-2">Categories</h2>
      <ul className="list-disc pl-5">
        {categories.map((category) => (
          <li key={category.id} className="text-gray-700">{category.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default CategoryList;