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
        component: Home
      },
      {
        path: 'users',
        component: Users
      }
    ]
  }
];

export default routes;