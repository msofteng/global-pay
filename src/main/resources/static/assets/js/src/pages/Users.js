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
  template: `
    <div class="pg-users">
      <h3 class="text-3xl font-bold underline text-clifford">Users</h3>

      <button @click="registerUser" class="rounded-md bg-gray-950/75 px-3 py-2 text-sm font-medium text-white hover:bg-gray-950/100">
        Add User
      </button>

      <pre>{{ users }}</pre>
    </div>
  `
};