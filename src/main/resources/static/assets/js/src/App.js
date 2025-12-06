import Navbar from "./shared/components/Navbar.js";

export default {
  template: `
    <div>
      ${Navbar.template}
      <router-view></router-view>
    </div>
  `
};