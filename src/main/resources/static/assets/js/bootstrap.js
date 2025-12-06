import { loadTemplates } from "./load-templates.js";

await loadTemplates();

import("./index.js").then(module => {
  module.startApp();
});