import Home from '../pages/Home.js';
import Users from '../pages/Users.js';

const routes = [
  {
    path: '',
    component: {
      template: '<router-view />'
    },
    children: [
      {
        path: '',
        redirect: 'home'
      },
      {
        path: 'home',
        component: Home,
        meta: {
          title: 'Página Inicial'
        }
      },
      {
        path: 'users',
        component: Users,
        meta: {
          title: 'Listar Usuários'
        }
      }
    ]
  }
];

export default routes;