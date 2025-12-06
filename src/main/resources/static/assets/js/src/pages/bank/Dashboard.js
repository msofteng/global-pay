import serviceBank from "../../shared/services/bank.js";

export default {
  template: "#dashboard-template",
  data() {
    return {
      usuario: null
    };
  },
  mounted() {
    const usuario = localStorage.getItem("usuario");

    if (usuario) {
      this.usuario = JSON.parse(usuario);

      serviceBank.buscarCliente(
        this.usuario.username
      ).then(data => {
        this.usuario = data;
      }).catch(_ => {
        this.logout();
      });
    } else {
      this.logout();
    }
  },
  methods: {
    logout() {
      localStorage.removeItem("usuario");
      window.location.href = "/#/auth/login";
    },
    formatarMoeda(valor) {
      return new Intl.NumberFormat(
        'pt-BR',
        {
          style: 'currency',
          currency: 'BRL'
        }
      ).format(valor);
    }
  }
};