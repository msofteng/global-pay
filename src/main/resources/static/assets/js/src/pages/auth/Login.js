import formDataToJson from "../../shared/functions/form.js";
import serviceBank from "../../shared/services/bank.js";

export default {
  template: "#login-template",
  data() {
    return {
      errors: []
    };
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      
      const data = formDataToJson(new FormData(e.target));
      
      serviceBank.realizarLogin(
        data
      ).then(_ => {
        this.errors = [];
        e.target.reset();
        window.location.href = "/#/bank";
      }).catch(error => {
        this.errors = error.errors;
      });
    }
  }
}