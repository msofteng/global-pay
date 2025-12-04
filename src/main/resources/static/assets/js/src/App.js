const App = {
  data() {
    return {
      users: []
    }
  },
  methods: {
    async listarUsuarios() {
      const req = await fetch('http://localhost:8080/home/users');
      this.users = await req.json();
    }
  }
}

export default App;