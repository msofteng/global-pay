import BankLayout from "../layout/BankLayout.js";
import Dashboard from "../pages/bank/Dashboard.js";
import Search from "../pages/bank/Search.js";
import Statement from "../pages/bank/Statement.js";
import Transfer from "../pages/bank/Transfer.js";

const bankRoutes = {
  path: "bank",
  component: BankLayout,
  children: [
    {
      path: "",
      component: Dashboard,
      meta: {
        title: "Dashboard"
      }
    },
    {
      path: "search",
      component: Search,
      meta: {
        title: "Buscar Conta"
      }
    },
    {
      path: "statement",
      component: Statement,
      meta: {
        title: "Extrato"
      }
    },
    {
      path: "transfer",
      component: Transfer,
      meta: {
        title: "Transferir"
      }
    }
  ]
};

export default bankRoutes;