/*
 * @Descripttion: 
 * @Author: Ashley
 * @Date: 2020-04-21 17:28:05
 */
const basePath = (process.env.NODE_ENV === 'production' ? '/todolist-demo':'');

export const doRequest = (path, params) => {
	let mergedParams = {
	    credentials: 'same-origin',
	    ...params
	 }
	return fetch(basePath+path, mergedParams);
}

export const doRequestWithBody = (path, method, body) => {
	return doRequest(path, { 
	    headers: {
	      'content-type': 'application/json'
	    },
	    method, 
	    body: JSON.stringify(body)
	})
}

export const doDeleteRequest = (path) => {
	return doRequest(path, { 
	    method: 'delete'
	})
} 