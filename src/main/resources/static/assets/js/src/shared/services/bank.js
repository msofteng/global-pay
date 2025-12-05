import http from "../constants/http.js";

const _http = http.create('https://localhost:8080');

export default {
  cadastrarCliente: async (data) => {
    const res = await _http(
      '/bank/user/add',
      {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      }
    );

    if (!res.ok) {
      return Promise.reject(await res.json());
    } else {
      return Promise.resolve(await res.json());
    }
  }
}