import serviceBank from "../shared/services/bank.js";

export default {
  data() {
    return {
      users: []
    };
  },
  methods: {
    registerUser() {
      serviceBank.cadastrarCliente({
        fullName: "MATEUS SILVA",
        username: "msofteng",
        password: "mateus123"
      }).then(response => {
        this.users = [response];
      }).catch(error => {
        this.users = error.errors;
      })
    }
  },
  template: "#user-template"
};