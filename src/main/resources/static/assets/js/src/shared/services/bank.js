import http from "../constants/http.js";

export default {
  /**
   * Cadastra um novo cliente na base de dados.
   *
   * @param {Object} data - Objeto contendo os dados do cliente.
   * @param {string} data.fullName - Nome completo do cliente.
   * @param {string} data.username - Nome de usuário do cliente.
   * @param {string} data.password - Senha do cliente.
   *
   * @returns {Promise<Object>} Resposta contendo os dados do cliente cadastrado.
   * @returns {string} `return.fullName` - Nome completo do cliente.
   * @returns {string} `return.username` - Nome de usuário do cliente.
   * @returns {number} `return.numeroConta` - Número da conta gerado.
   */
  cadastrarCliente: (data) => {
    return http.post("/bank/user/add", data);
  },

  /**
   * Busca clientes na base de dados.
   *
   * @param {string} [username] - Nome de usuário do cliente.
   * @param {number} [pg] - Número da página.
   * @param {number} [qtd] - Quantidade de itens por página.
   *
   * @returns {Promise<Object[]>} Resposta contendo os dados dos clientes.
   * @returns {string} `return[0].fullName` - Nome completo do cliente.
   * @returns {string} `return[0].username` - Nome de usuário do cliente.
   * 
   */
  buscarClientes: (
    username = "",
    pg = 1,
    qtd = 10
  ) => {
    return http.get(
      `/bank/users/${username}`,
      {
        pagina: pg,
        quantidade: qtd
      }
    )
  },

  /**
   * Busca um cliente na base de dados.
   *
   * @param {string} [usernameOrId] - Nome de usuário ou ID do cliente.
   *
   * @returns {Promise<Object>} Resposta contendo os dados do cliente.
   * @returns {string} `return.fullName` - Nome completo do cliente.
   * @returns {string} `return.username` - Nome de usuário do cliente.
   */
  buscarCliente: (usernameOrId = "") => {
    return http.get(`/bank/user/${usernameOrId}`);
  },

  /**
   * Exclui um cliente da base de dados.
   *
   * @param {number} [id] - ID do cliente.
   *
   * @returns {Promise<string>} Resposta contendo a mensagem de sucesso.
   * @returns {string} `return` - Mensagem de sucesso (incorporada em um cabeçalho de resposta).
   */
  excluirCliente: (id = 0) => {
    return http.delete(`/bank/user/${id}`);
  },

  /**
   * Realiza uma transferência bancária.
   * 
   * @param {Object} data - Objeto contendo os dados da transferência.
   * @param {number} data.valor - Valor da transferência.
   * @param {string} data.dataAgendamento - Data de agendamento da transferência.
   * @param {Object} data.origem - Objeto contendo os dados da conta de origem.
   * @param {number} data.origem.numeroConta - Número da conta de origem.
   * @param {Object} data.destino - Objeto contendo os dados da conta de destino.
   * @param {number} data.destino.numeroConta - Número da conta de destino.
   * 
   * @returns {Promise<Object>} Resposta contendo os dados da transferência.
   * @returns {number} `return.id` - ID da transferência.
   * @returns {number} `return.valor` - Valor da transferência.
   * @returns {number} `return.valorPercentual` - Valor percentual da transferência.
   * @returns {number} `return.valorOperacao` - Valor da operação da transferência.
   * @returns {string} `return.dataOperacao` - Data da operação da transferência.
   * @returns {string} `return.dataAgendamento` - Data de agendamento da transferência.
   * @returns {Object} `return.origem` - Objeto contendo os dados da conta de origem.
   * @returns {number} `return.origem.id` - ID da conta de origem.
   * @returns {string} `return.origem.fullName` - Nome completo da conta de origem.
   * @returns {string} `return.origem.username` - Nome de usuário da conta de origem.
   * @returns {number} `return.origem.numeroConta` - Número da conta de origem.
   * @returns {number} `return.origem.saldo` - Saldo da conta de origem.
   * @returns {Object} `return.destino` - Objeto contendo os dados da conta de destino.
   * @returns {string} `return.destino.fullName` - Nome completo da conta de destino.
   * @returns {string} `return.destino.username` - Nome de usuário da conta de destino.
   * @returns {number} `return.destino.numeroConta` - Número da conta de destino.
   * @returns {Object} `return.taxa` - Objeto contendo os dados da taxa.
   * @returns {number} `return.taxa.id` - ID da taxa.
   * @returns {number} `return.taxa.minDias` - Número mínimo de dias para a taxa.
   * @returns {number} `return.taxa.maxDias` - Número máximo de dias para a taxa.
   * @returns {number} `return.taxa.valor` - Valor da taxa.
   * @returns {number} `return.taxa.taxa` - Taxa da transferência.
   */
  realizarTransferencia: (data) => {
    return http.post("/bank/transfer", data);
  },

  /**
   * Consulta o extrato bancário.
   * 
   * @param {Object} data - Objeto contendo os dados da consulta.
   * @param {number} data.nrConta - Número da conta.
   * @param {string} data.tipo - Tipo da consulta (ENVIADO ou RECEBIDO).
   * @param {string} data.inicio - Data de início da consulta.
   * @param {string} data.fim - Data de fim da consulta.
   * @param {number} data.pagina - Número da página.
   * @param {number} data.quantidade - Quantidade de itens por página.
   * 
   * @returns {Promise<Object[]>} Resposta contendo os dados do extrato.
   * @returns {number} `return[0].id` - ID da transferência.
   * @returns {number} `return[0].valor` - Valor da transferência.
   * @returns {number} `return[0].valorPercentual` - Valor percentual da transferência.
   * @returns {number} `return[0].valorOperacao` - Valor da operação da transferência.
   * @returns {string} `return[0].dataOperacao` - Data da operação da transferência.
   * @returns {string} `return[0].dataAgendamento` - Data de agendamento da transferência.
   * @returns {Object} `return[0].origem` - Objeto contendo os dados da conta de origem. (pode ser nulo quando o tipo for `ENVIADO`)
   * @returns {number} `return[0].origem.id` - ID da conta de origem.
   * @returns {string} `return[0].origem.fullName` - Nome completo da conta de origem.
   * @returns {string} `return[0].origem.username` - Nome de usuário da conta de origem.
   * @returns {number} `return[0].origem.numeroConta` - Número da conta de origem.
   * @returns {number} `return[0].origem.saldo` - Saldo da conta de origem.
   * @returns {Object} `return[0].destino` - Objeto contendo os dados da conta de destino. (pode ser nulo quando o tipo for `RECEBIDO`)
   * @returns {string} `return[0].destino.fullName` - Nome completo da conta de destino.
   * @returns {string} `return[0].destino.username` - Nome de usuário da conta de destino.
   * @returns {number} `return[0].destino.numeroConta` - Número da conta de destino.
   * @returns {Object} `return[0].taxa` - Objeto contendo os dados da taxa.
   * @returns {number} `return[0].taxa.id` - ID da taxa.
   * @returns {number} `return[0].taxa.minDias` - Número mínimo de dias para a taxa.
   * @returns {number} `return[0].taxa.maxDias` - Número máximo de dias para a taxa.
   * @returns {number} `return[0].taxa.valor` - Valor da taxa.
   * @returns {number} `return[0].taxa.taxa` - Taxa da transferência.
   */
  consultarExtrato: (data) => {
    return http.post("/bank/transfer", data);
  }
}