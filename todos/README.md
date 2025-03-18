## Overview
- Create an API to be integrated with your todos-ui project, that allows you to store and retrieve tasks from a database.

## MVP For Backend
Deleting a task should set an isArchived flag in the database instead of deleting the task from the database
Add a filter to the frontend application that allows you to filter tasks by category
Categories and Todos should be stored in separate tables

## MVP for Frontend

Must be able to add categories
Must be able to add new tasks tagged with a task category
Must be able to update tasks automatically by changing the task name and the category / edit button is fine
Must be able to duplicate tasks
Must be able to delete tasks (remember this is soft delete, in the backend it will be isArchived)
You must add your own styling


## Endpoints
GET /categories

POST /categories

PUT /categories/:id

DELETE /categories/:id

GET /todos

GET /todos?category={}

POST /todos

PUT /todos/:id

DELETE /todos/:id

## Stack 
Spring Boot
Hibernate ORM
Model Mapper is optional
Testing is optional but reccomended

## Stack
React with TypeScript
NextJs with coach confirmation
Component Libraries allowed but discouraged
Talk to a coach
Tailwind optional
React Hook Form optional - but strongly encouraged

## Design Goals
- I implemented it this way because it helped me learn alot more about Spring
- It helped explore so much about Java
- Earlier i thought i would request my Coach to allow me use NextJS but i realised i do not want to miss the opportunity to learn Java and backend via this project

## Features
- Project allows:-
- Sending API request to Backend and seeing the Todos and categories coming up on frontend without refreshing the page
- Improves user experience by reducing delay 
- Allows updating Todo task and categories 
- Allows user to filter task as per needs
- Allows user to add a new task to the list
- Allows user to add categories as per need
- Great user experience by alerting if category already exist

## Future Goals
- Yes, I want to follow up on each and every advice that my coach has for me and the project

## Struggles
- I definitely had to learn alot that i actually thought i would 
- But i believe thats what makes me realize this project and makes me feel amazing of what i am doing
- Learning was the best part but it was overwhelming as well because at first i designed backend and frontend both but 
- when i was running - npm run dev
- i could not see anything on my browser
- and i was stuck at it for hours, wbut i had it in my mind that no matter what i want to figure it on my own
- another was getting bit confused while using Typescript
- i was mistaking it for javascript
- and then bit overwhelming again due to so much learning
- But definitely the most beautiful experience 
