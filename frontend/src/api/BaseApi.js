const basePath = (process.env.NODE_ENV === 'production' ? '/todo-list':'');

export const doRequest = (path, params) => {
	let mergedParams = {
	    credentials: 'same-origin',
	    ...params
	 }
	return fetch(basePath+path, mergedParams);
}
