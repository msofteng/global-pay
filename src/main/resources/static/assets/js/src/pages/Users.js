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
    }
  },
  template: `
    <div>
      <h1>Users</h1>
      <button @click="loadUsers">Load Users</button>
      <pre>{{ users }}</pre>
    </div>
  `
};
