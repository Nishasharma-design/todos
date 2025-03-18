import { useEffect, useState } from "react";
import { addTodo, deleteTodo as deleteTodoApi, getCategories, getTodos, updateTodo, addNewCategory } from "./services/api";
import CategoryList from "./component/CategoryList";
import TodoList from "./component/TodoList";
import AddTodoForm from "./component/AddTodoForm";
import AddCategoryForm from "./component/AddCategoryForm";
import './App.css';

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

const App = () => {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(null);

  // fxn to filter todos based on selected category
  const filteredTodos = selectedCategoryId 
                       ?  todos.filter(todo => todo.category.id === selectedCategoryId)
                        : todos;

  useEffect(() => {
    const fetchData = async () => {
      try {
        setTodos(await getTodos());
        setCategories(await getCategories());
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);

const handleTitleChange = async (id: number, newTitle: string, categoryId?: number) => {
  // first it updates the todos state by changing the title of the todo with the matching id
  setTodos(prevTodos => 
      prevTodos.map(todo => 
          todo.id === id ? { ...todo, title: newTitle } : todo
      )
  );
  await updateTodo(id, { title: newTitle, categoryId }); // Send update to backend
};





const handleCategoryChange = async (id: number, newCategoryId: number) => {
  
  setTodos(prevTodos =>
    prevTodos.map(todo =>
        todo.id === id ? { ...todo, category: { ...todo.category, id: newCategoryId } } : todo
    )
);

// Send the update to the backend with the new categoryId
await updateTodo(id, { categoryId: newCategoryId });
};





  const duplicateTodo = async (todo: any) => {
    await addTodo({ title: todo.title, categoryId: todo.category.id });
    setTodos(await getTodos());
  };
  

  const handleDeleteTodo = async (id: number) => {
    await deleteTodoApi(id);
    setTodos(await getTodos());
  };


  const addCategory = async (name: string) => {
    await addNewCategory(name);
    setCategories(await getCategories());
  };
  


  return (
    <div className="flex flex-col justify-start min-h-screen bg-gray-100 py-8 w-full">
      
      <h1 className="text-3xl font-bold text-center mb-6">Todo App</h1>
      <div className="w-full px-4 max-w-5xl mx-auto space-y-6">
      <AddCategoryForm addCategory={addCategory} categories={categories}/>
      <CategoryList categories={categories} />
      {/* Category filter dropdown */}
      <select 
            value={selectedCategoryId || ""}
            onChange={(e) => setSelectedCategoryId(e.target.value ? Number(e.target.value) : null)}
            className="border px-2 py-1 rounded"
            >
              <option value="">All Categories</option>
              {categories.map((category) => (
                <option key={category.id}  value={category.id}>{category.name}</option>
              ))}
            </select>
      <TodoList
        todos={filteredTodos}
        categories={categories}
        handleTitleChange={handleTitleChange}
        handleCategoryChange={handleCategoryChange}
        deleteTodo={handleDeleteTodo}
        duplicateTodo={duplicateTodo}
      />
      
      <AddTodoForm categories={categories} addTodo={addTodo} setTodos={setTodos} todos={todos} />
    </div>
    </div>
   
  );
};

export default App;



  
