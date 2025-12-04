export default {
  template: `
    <div>
      <nav>
        <router-link to="/home">Home</router-link>
        <router-link to="/users">Users</router-link>
      </nav>

      <router-view></router-view>
    </div>
  `
};