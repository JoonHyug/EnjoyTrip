import Vue from "vue";
import Vuex from "vuex";
import axios from "@/axios";
import jwtDecode from "jwt-decode";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    token: null,
    info: null,
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
    },
    clearToken(state) {
      state.token = null;
    },
    setInfo(state, info) {
      state.info = info;
    },
  },
  actions: {
    async login({ commit, dispatch }, data) {
      try {
        const res = await axios.post("/auth", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
          },
        });
        const token = res.data.token;
        dispatch("decodeToken", token);
        commit("setToken", token);
      } catch (error) {
        console.log("로그인 에러", error);
      }
    },
    logout({ commit }) {
      commit("clearToken");
      commit("setInfo", null);
    },
    decodeToken({ commit }, token) {
      const decodeToken = jwtDecode(token);
      commit("setInfo", {
        id: decodeToken.id,
        name: decodeToken.name,
        email: decodeToken.email,
        accessId: decodeToken.accessId,
      });
    },
  },
  getters: {
    isAuthenticated(state) {
      return state.token !== null;
    },
    getInfo(state) {
      return state.info;
    },
  },
};
