import formDataToJson from "../../shared/functions/form.js";
import serviceBank from "../../shared/services/bank.js";

export default {
  template: "#register-template",
  data() {
    return {
      errors: []
    };
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      
      const data = formDataToJson(new FormData(e.target));

      if (data.password !== data.passwordConfirm) {
        this.errors = ["As senhas não conferem"];
        return;
      }

      serviceBank.cadastrarCliente(
        data
      ).then(_ => {
        this.errors = [];
        e.target.reset();
        window.location.href = "/#/auth/login";
      }).catch(error => {
        this.errors = error.errors;
      });
    }
  }
}