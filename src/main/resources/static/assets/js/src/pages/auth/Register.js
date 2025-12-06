import formDataToJson from "../../shared/functions/form.js";

export default {
  template: "#register-template",
  methods: {
    handleSubmit: (e) => {
      e.preventDefault();
      
      const data = formDataToJson(new FormData(e.target));
      
      console.log(data);
    }
  }
}