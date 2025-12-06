const { createApp } = Vue;

import App from "./src/App.js";
import router from "./src/routes/router.js";

const app = createApp(App);

app.use(router);
app.mount("#app");