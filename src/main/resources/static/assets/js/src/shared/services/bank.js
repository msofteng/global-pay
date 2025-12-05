import http from "../constants/http.js";

export default {
  cadastrarCliente: (data) => {
    return http.post('/bank/user/add', data);
  }
}