import authRoutes from "./authRoutes.js";
import bankRoutes from "./bankRoutes.js";

import AppLayout from "../layout/AppLayout.js";

import Home from "../pages/Home.js";
import Users from "../pages/Users.js";

const routes = [
  {
    path: "",
    component: AppLayout,
    children: [
      {
        path: "",
        redirect: "home"
      },
      {
        path: "home",
        component: Home,
        meta: {
          title: "Página Inicial"
        }
      },
      {
        path: "users",
        component: Users,
        meta: {
          title: "Listar Usuários"
        }
      },
      authRoutes,
      bankRoutes
    ]
  }
];

export default routes;