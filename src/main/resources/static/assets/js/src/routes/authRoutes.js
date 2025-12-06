import AuthLayout from "../layout/AuthLayout.js";
import Login from "../pages/auth/Login.js";
import Register from "../pages/auth/Register.js";

const authRoutes = {
  path: "auth",
  component: AuthLayout,
  children: [
    {
      path: "login",
      component: Login,
      meta: {
        title: "Login"
      }
    },
    {
      path: "register",
      component: Register,
      meta: {
        title: "Register"
      }
    }
  ]
}

export default authRoutes;