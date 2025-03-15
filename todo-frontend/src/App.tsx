import { useEffect, useState } from "react";
import { addTodo, deleteTodo as deleteTodoApi, getCategories, getTodos, updateTodo, addNewCategory } from "./services/api";
import CategoryList from "./component/CategoryList";
import TodoList from "./component/TodoList";
import AddTodoForm from "./component/AddTodoForm";
import AddCategoryForm from "./component/AddCategoryForm";
import './App.css';

const App = () => {
  const [todos, setTodos] = useState<any[]>([]);
  const [categories, setCategories] = useState<any[]>([]);
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

const handleTitleChange = async (id: number, newTitle: string) => {
  setTodos(prevTodos => 
      prevTodos.map(todo => 
          todo.id === id ? { ...todo, title: newTitle } : todo
      )
  );

  await updateTodo(id, { title: newTitle }); // Send update to backend
};

const handleCategoryChange = async (id: number, newCategoryId: number) => {
  await updateTodo(id, { categoryId: newCategoryId });
  setTodos(await getTodos());
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
      <AddCategoryForm addCategory={addCategory} />
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
      
      <AddTodoForm categories={categories} addTodo={addTodo}  />
    </div>
    </div>
   
  );
};

export default App;



  // const duplicateTodo = async (todo: any) => {

  //  const duplicatedTodo = { title: todo.title, categoryId: todo.category.id };
  //  setTodos(prevTodos => [...prevTodos, duplicatedTodo]);

  //  // send the new duplicated todo to backend
  //  await addTodo(duplicatedTodo);

  //  // refresh todos list after duplication
  //  setTodos(await getTodos());

  // }

  // const handleDeleteTodo = async (id: number) => {
  //   setTodos(prevTodos => prevTodos.filter(todo => todo.id !== id));

  //   //send the dleete request to backend
  //   await deleteTodoApi(id);

  //   // refresh todos after deletion
  //   setTodos(await getTodos());
  // }


 // const addCategory = async (name: string) => {

  //  const newCategory = { id: Math.random(), name };

  //  setCategories(prevCategories => [prevCategories, newCategory]);

  //  // send the add category request to backend
  //  await addNewCategory(name);

  // // refresh categories list after adding 
  //  setCategories(await getCategories());

  // }

  // handle category change for an existing todo
  // const handleCategoryChange = async (id: number, newCategoryId: number) => {
  //   setTodos(prevTodos => 
  //             prevTodos.map(todo => todo.id === id ? { ...todo, category: { id: newCategoryId } } : todo)
  //   );
  //   // send update to backend
  //   await updateTodo(id, { categoryId: newCategoryId });

  //   // after backend update, refresh todos list
  //   setTodos(await getTodos());
  // };



// import { useEffect, useState } from "react";
// import { addTodo, deleteTodo, getCategories, getTodos, updateTodo } from "./services/api";
// import './App.css';

// const App = () => {
//   const [todos, setTodos] = useState<any[]>([]); //state to hold todos
//   const [categories, setCategories] = useState<any[]>([]); //state to hold categories
//   const [newTodoTitle, setNewTodoTitle] = useState(''); //state for new todo title
//   const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(null); //state for selected category

  
//   useEffect(() => {
//     const fetchData = async () => {
//       try {
//         const todosData = await getTodos(); // fetch todos from api
//         const categoriesData = await getCategories(); //fetch categories
//         setTodos(todosData); //set todos state
//         setCategories(categoriesData); //set categories state
//       } catch (error) {
//         console.error('Error fetching data:', error);
//       }
//     };
//     fetchData(); // call the fxn to fetch data
//   }, []); // empty dependency array to run the effect only once

//    // Handle title change for an existing todo
//    const handleTitleChange = async (id: number, newTitle: string) => {
//     await updateTodo(id, { title: newTitle });
//     setTodos(await getTodos()); // Refresh todos after update
//   };

//   // Handle category change for an existing todo
//   const handleCategoryChange = async (id: number, newCategoryId: number) => {
//     await updateTodo(id, { categoryId: newCategoryId });
//     setTodos(await getTodos()); // Refresh todos after update
//   };


//   // Function to duplicate a todo
// const duplicateTodo = async (todo: any) => {
//   const newTodo = { title: todo.title, categoryId: todo.category.id };
//   await addTodo(newTodo); // Add duplicated todo
//   setTodos(await getTodos()); // Refresh todos list
// };



// return (
//   <div className="max-w-3xl mx-auto p-4 bg-gray-100 min-h-screen">
//     <h1 className="text-3xl font-bold text-center mb-6">Todo App</h1>

//     {/* Categories Section, displays list of categories, each category is shown in a <li>  */}
//     <div className="bg-white shadow-md p-4 mb-6 rounded-lg">
//       <h2 className="text-xl font-semibold mb-2">Categories</h2>
//       <ul className="list-disc pl-5">
//         {/* maps thru categories array and renders each category in <li> */}
//         {categories.map((category) => (
//           <li key={category.id} className="text-gray-700">{category.name}</li>
//         ))}
//       </ul>
//     </div>

//     {/* Todos Section, renders todo list, display each todo with editable title input
//          Dropdown for changing category, soft-delete button, duplicate button */}
//     <div className="bg-white shadow-md p-4 mb-6 rounded-lg">
//       <h2 className="text-xl font-semibold mb-2">Todos</h2>
//       <ul className="space-y-3">
//         {/* filters out archived todos, map thru todos array and renders
//             an editable title input, a category selection dropdown,   */}
//         {todos.filter(todo => !todo.isArchived).map((todo) => (
//           <li key={todo.id} className="flex items-center justify-between bg-gray-50 p-3 rounded-lg shadow-sm">
//             <input
//               type="text"
//               value={todo.title}
//               className="border rounded px-2 py-1 w-2/5"
//               onChange={(e) => handleTitleChange(todo.id, e.target.value)}
//             />
//             <select
//               className="border rounded px-2 py-1 w-1/4"
//               value={todo.category.id || ""}
//               onChange={(e) => handleCategoryChange(todo.id, Number(e.target.value))}
//             >
//               <option value="">Select Category</option>
//               {categories.map((category) => (
//                 <option key={category.id} value={category.id}>{category.name}</option>
//               ))}
//             </select>
//             <button
//               className="bg-red-500 text-black px-3 py-1 rounded hover:bg-red-600"
//               onClick={async () => { setTodos(await deleteTodo(todo.id));

//               }}
//             >
//               Delete
//             </button>
//             <button
//               className="bg-blue-500 text-black px-3 py-1 rounded hover:bg-blue-600"
//               onClick={() => duplicateTodo(todo)}
//             >
//               Duplicate
//             </button>
//           </li>
//         ))}
//       </ul>
//     </div>

//     {/* Add New Todo Section, allows users to enter a new todo title and select category,
//        when user click "Add Todo", sends the new todo to the backend("addTodo in api.ts", clears 
//        the input field, fetches the updated list of todos.)
//     */}
//     <div className="bg-white shadow-md p-4 rounded-lg">
//       <h3 className="text-lg font-semibold mb-2">Add New Todo</h3>
//       <div className="flex space-x-3">
//         <input
//           type="text"
//           placeholder="Todo title"
//           className="border rounded px-2 py-1 flex-grow"
//           value={newTodoTitle}
//           onChange={(e) => setNewTodoTitle(e.target.value)}
//         />
//         <select
//           className="border rounded px-2 py-1"
//           onChange={(e) => setSelectedCategoryId(Number(e.target.value))}
//           value={selectedCategoryId || ""}
//         >
//           <option value="">Select category</option>
//           {categories.map((category) => (
//             <option key={category.id} value={category.id}>{category.name}</option>
//           ))}
//         </select>
//         <button
//           className="bg-green-500 text-black px-3 py-1 rounded hover:bg-green-600"
//           onClick={async () => {
//             if (newTodoTitle && selectedCategoryId !== null) {
//               const newTodo = { title: newTodoTitle, categoryId: selectedCategoryId };
//               await addTodo(newTodo);
//               setNewTodoTitle("");
//               setSelectedCategoryId(null);
//               setTodos(await getTodos());
//             }
//           }}
//         >
//           Add Todo
//         </button>
//       </div>
//     </div>
//   </div>
// );
// }

// export default App;



// /* 
//     Fetching Categories & Todos →  already fetching both categories and todos from the backend (getCategories() and getTodos()).
// Adding Todos → I can add a new task associated with a category (addTodo()).
// Deleting Todos (Soft Delete) → I have the deleteTodo() function, which sends a DELETE request to the backend.
// Displaying Todos & Categories → Todos and categories are displayed properly in the UI.

// */

// /* todos.map() loops through each todo and renders it.
// For each todo, i am showing the todo's title and its associated category's name.
// I also provide a delete button for each todo. When clicked, it calls the deleteTodo function and deletes the todo. */


/* An input field where the user can type the title of the new todo. 
    The value of the input is controlled by the newTodoTitle state, 
    and it updates the state as the user types (onChange) */