import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {},
  mutations: {},
  actions: {
    goPath(_, { path, router, route }) {
      if (path === undefined) path = "";
      path = "/" + path;
      if (route.path !== path) router.push(path);
    },
  },
  getters: {},
};
