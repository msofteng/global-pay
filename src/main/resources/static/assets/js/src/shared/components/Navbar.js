export default {
  template: "#navbar-template",
  data() {
    return {
      pages: [
        {
          name: "Página Inicial",
          path: "/home"
        },
        {
          name: "Usuários",
          path: "/users"
        }
      ]
    }
  }
}