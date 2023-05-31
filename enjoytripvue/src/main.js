import Vue from "vue";
import App from "./App";
import router from "@/router/router";
import store from "@/store/index";
import "./assets/css/style.css";
import "bootstrap-icons/font/bootstrap-icons.css";

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  render: (h) => h(App),
}).$mount("#app");
