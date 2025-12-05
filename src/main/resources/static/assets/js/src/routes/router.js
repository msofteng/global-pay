const { createRouter, createWebHashHistory } = VueRouter;

import routes from './routes.js';

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  document.title = to.meta?.title || 'Global PAY';
  next();
});

export default router;