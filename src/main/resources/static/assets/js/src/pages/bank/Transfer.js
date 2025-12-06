import formDataToJson from "../../shared/functions/form.js";
import serviceBank from "../../shared/services/bank.js";

export default {
  template: "#transfer-template",
  data() {
    return {
      errors: [],
      numeroContaOrigem: 0
    };
  },
  mounted() {
    const usuario = JSON.parse(localStorage.getItem("usuario"));
    
    if (usuario) {
      this.numeroContaOrigem = usuario.numeroConta;
    } else {
      window.location.href = "/#/login";
    }
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      
      const data = formDataToJson(new FormData(e.target));
      
      serviceBank.realizarTransferencia(
        data
      ).then(data => {
        this.errors = [];
        e.target.reset();
        window.location.href = "/#/bank";
      }).catch(error => {
        this.errors = error.errors;
      });
    }
  }
};