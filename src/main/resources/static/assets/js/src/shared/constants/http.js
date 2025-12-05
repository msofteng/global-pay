const http = {
  create: (baseUrl) => {
    return async (path, options) => {
      return await fetch(`${baseUrl}${path}`, options);
    }
  }
}

export default http;