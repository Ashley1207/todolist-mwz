import React, { useState, Fragment, useEffect } from "react";
import TodoItem from "./TodoItem";
import { getTodos} from "./api/TodoApi";
import "./style.css";
import _ from "lodash";

const TodoList = () => {
  const [list, setList] = useState(null);
  const [error, setError] = useState(""); 
 
  const handleLoadTasks = () => {
    getTodos()
      .then((response) => {
        setList(response);
      })
      .catch((error) => {
        setError("Unable to retrieve todo's");
      });
  };

  useEffect(() => {
    handleLoadTasks();
  }, []);

  if (list === null) {
    return <div>Tasks is loading ...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  } 

  return (
    <Fragment>
      <div className="container">  
        <h1> Todo List </h1>

        <ul data-testid="task-items" className="task-items">
          {list.map((item) => (
            <TodoItem
              key={item.id}
              item={item}
              index={item.id}
            />
          ))}
        </ul>
      
        <input
            className="task-input"
            type="text"
            data-testid="task-input"
         />
          <span
           className="text-button add-button"
           data-testid="add-button" 
          >
           Add
          </span>
  
      </div>
    </Fragment>
  );
};

export default TodoList;
