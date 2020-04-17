import React from "react";
import {render,fireEvent,act,wait,getByTestId,getAllByTestId,} from "@testing-library/react";
import TodoList from "./TodoList";
import * as TodoApi from "./api/TodoApi";

describe("<TodoList>", () => {
  const item = { id: 1, content: "a", createAt: "2020/04/17" };
  const addedItem = { id: 2, content: "b", createAt: "2020/04/17" };

  beforeEach(() => {
    jest
      .spyOn(TodoApi, "getTodos")
      .mockImplementation(() => Promise.resolve([item]));
  });

  test("should display todo items correctly", async () => {
    await act(async () => {
      render(<TodoList />);
    });

    expect(getByTestId(document.body, "task-item")).toHaveTextContent(
      "a"
    );
  });

  test("should add todo item correctly", async () => {
    jest
      .spyOn(TodoApi, "addTodo")
      .mockImplementation(() => Promise.resolve(addedItem));

    await act(async () => {
      render(<TodoList />);
    });

    act(() => {
      fireEvent.change(getByTestId(document.body, "task-input"), {
        target: { value: addedItem.content },
      });
    });

    act(() => {
      fireEvent.click(getByTestId(document.body, "add-button"));
    });
    await wait(() => expect(TodoApi.addTodo).toHaveBeenCalled());

    const taskItems = getAllByTestId(document.body, "task-item");
    expect(taskItems.length).toEqual(2);
    expect(taskItems[1]).toHaveTextContent(addedItem.content);
  });
});
