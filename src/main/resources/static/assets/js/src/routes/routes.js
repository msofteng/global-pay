const { createRouter, createWebHashHistory } = VueRouter;

import Home from '../pages/Home.js';
import Users from '../pages/Users.js';

const routes = [
  { path: '/home', component: Home },
  { path: '/users', component: Users }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

export default router;