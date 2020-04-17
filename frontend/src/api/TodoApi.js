import {doRequest, doRequestWithBody, doDeleteRequest} from './BaseApi'

const TODO_PATH = "/api/tasks";

 export const getTodos = () => {
     return doRequest(TODO_PATH)
    .then(response => response.json());
}