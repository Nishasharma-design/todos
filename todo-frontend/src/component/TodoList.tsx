import React from "react";

// todo represents a single task
interface Todo {
    id: number; 
    title: string; 
    isArchived: boolean; 
    category: { id: number; name: string }; 
}


// represents a category of tasks
interface Category {
    id: number;
    name: string;
}


// this defines what props TodoList expects to receive from App.tsx
interface TodoListProps {
    todos: Todo[];  // an array of Todo(interface defined above) objects
    categories: Category[]; // an array of Category(interface defined above) objects
    handleTitleChange: (id: number, newTitle: string) => void; // fxn to change todo's title
    handleCategoryChange: (id: number, newCategoryId: number) => void; // fxn to change todo's category
    deleteTodo: (id: number) => void;              // fxn to soft-delet todo
    duplicateTodo: (todo: Todo) => void; // fxn to duplicate a todo, it takes a full todo object
}


// this is a fxnal defined using the React.FC type 
const TodoList: React.FC<TodoListProps> = ({
    todos,
    categories,
    handleTitleChange,
    handleCategoryChange,
    deleteTodo,
    duplicateTodo,
}) => {
    return (
        <div className="bg-white shadow-md rounded-lg p-6 space-y-4 w-full">
            <h2 className="text-xl font-semibold mb-2">Todos</h2>
            {todos?.length > 0 ? (
  <ul className="space-y-3">
    {/*  todos.filter -> removes archived todos */}
    {todos.filter(todo => !todo.isArchived).map((todo) => ( 
      <li key={todo.id} className="flex items-center justify-between hover:bg-gray-50 rounded-lg shadow-sm"> 
        <input 
          type="text"
          value={todo.title}
          className="border rounded px-2 py-1 w-full"
          onChange={(e) => handleTitleChange(todo.id, e.target.value)}
        />
        <select 
          className="border rounded px-2 py-1 w-full"
          value={todo.category?.id || ""}
          onChange={(e) => handleCategoryChange(todo.id, Number(e.target.value))}
        >
          <option value="">Select Category</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>{category.name}</option>
          ))}
        </select>
        <button 
          className="bg-red-500 text-black px-3 py-1 rounded hover:bg-red-600"
          onClick={() => deleteTodo(todo.id)}>
          Delete
        </button>
        <button 
          className="bg-blue-500 text-black px-3 py-1 rounded hover:bg-blue-600"
          onClick={() => duplicateTodo(todo)}>
          Duplicate
        </button>
      </li>
    ))}
  </ul>
) : (
  <p className="text-gray-500">No todos available.</p>
)}

        </div>
    );
};

export default TodoList;


