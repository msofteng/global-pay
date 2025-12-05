const httpInstance = {
  create: (baseUrl) => {
    return async (path, options) => {
      return await fetch(`${baseUrl}${path}`, options);
    }
  }
};

const baseUrl = 'https://localhost:8080';

const _http = httpInstance.create(baseUrl);

const http = {
  get: async (path, params, options = {}) => {
    const url = new URL(`${baseUrl}${path}`);
    url.search = new URLSearchParams(params).toString();

    const res = await _http(
      url.toString().replace(`${baseUrl}/`, ''),
      {
        method: 'GET',
        ...options
      }
    );

    if (!res.ok) {
      return Promise.reject(await res.json());
    } else {
      return Promise.resolve(await res.json());
    }
  },
  post: async (path, data, options = {}) => {
    const res = await _http(
      path,
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
        ...options
      }
    );

    if (!res.ok) {
      return Promise.reject(await res.json());
    } else {
      return Promise.resolve(await res.json());
    }
  }
};

export default http;