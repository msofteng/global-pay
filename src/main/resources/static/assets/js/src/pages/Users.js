export default {
  data() {
    return {
      users: []
    };
  },
  async mounted() {
    const req = await fetch('http://localhost:8080/home/users');
    this.users = await req.json();
  },
  template: `
    <div>
      <h1>Usuários</h1>
      <pre>{{ users }}</pre>
    </div>
  `
};
