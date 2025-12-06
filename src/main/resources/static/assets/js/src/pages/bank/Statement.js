import formDataToJson from "../../shared/functions/form.js";
import serviceBank from "../../shared/services/bank.js";

export default {
  template: "#statement-template",
  data() {
    return {
      errors: [],
      transferencias: [],
      numeroConta: 0,
    };
  },
  mounted() {
    const usuario = JSON.parse(localStorage.getItem("usuario"));
    
    if (usuario) {
      this.numeroConta = usuario.numeroConta;
    } else {
      window.location.href = "/#/login";
    }
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      
      const data = formDataToJson(new FormData(e.target));
      
      serviceBank.consultarExtrato(
        {
          ...data,
          pagina: 1,
          quantidade: 1000
        }
      ).then(data => {
        this.transferencias = data;
        this.errors = [];
      }).catch(error => {
        this.errors = error.errors;
      });
    },
    formatarMoeda(valor) {
      return new Intl.NumberFormat(
        'pt-BR',
        {
          style: 'currency',
          currency: 'BRL'
        }
      ).format(valor);
    },
    tipoOperacao(transferencia) {
      if (!transferencia.origem) {
        return 'enviado';
      } else {
        if (transferencia.origem.numeroConta != this.numeroConta) {
          return 'recebido';
        } else {
          return 'enviado';
        }
      }
    },
    formatarData(data) {
      const dataFormatada = new Date(data);

      const dia = dataFormatada.getDate().toString().padStart(2, '0');
      const mes = (dataFormatada.getMonth() + 1).toString().padStart(2, '0');
      const ano = dataFormatada.getFullYear();

      return `${dia}/${mes}/${ano}`;
    },
    isAgendado(data) {
      const dataAgendada = new Date(data);
      const dataAtual = new Date();

      return dataAgendada > dataAtual;
    }
  }
};