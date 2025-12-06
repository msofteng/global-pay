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
          name: "Login",
          path: "/auth/login"
        },
        {
          name: "Cadastro",
          path: "/auth/register"
        }
      ]
    }
  }
}