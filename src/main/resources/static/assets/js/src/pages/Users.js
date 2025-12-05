import serviceBank from '../shared/services/bank.js';

export default {
  data() {
    return {
      users: []
    };
  },
  methods: {
    async loadUsers() {
      const req = await fetch('http://localhost:8080/home/users');
      this.users = await req.json();
    },
    async registerUser() {
      serviceBank.cadastrarCliente({
        fullName: "MATEUS SILVA",
        username: "msofteng",
        password: "mateus123"
      }).then(response => {
        console.log(response);
      }).catch(error => {
        console.log(error.errors);
      })
    }
  },
  template: `
    <div class="pg-users">
      <h3>Users</h3>
      <button @click="loadUsers">Load Users</button>
      <button @click="registerUser">Add User</button>
      <pre>{{ users }}</pre>
    </div>
  `
};