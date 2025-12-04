const { createApp } = Vue;

import App from './src/App.js';
import router from './src/routes/routes.js';

const app = createApp(App);

app.use(router);
app.mount('#app');