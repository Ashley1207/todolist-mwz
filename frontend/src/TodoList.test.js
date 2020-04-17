import React from "react";
import {render,act,getByTestId,} from "@testing-library/react";
import TodoList from "./TodoList";
import * as TodoApi from "./api/TodoApi";

describe("<TodoList>", () => {
  const item = { id: 1, content: "a", createAt: "2020/04/17" };

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

});
