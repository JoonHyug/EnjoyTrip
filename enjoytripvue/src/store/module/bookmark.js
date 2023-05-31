import Vue from "vue";
import Vuex from "vuex";
import axios from "@/axios";
import jwtDecode from "jwt-decode";
Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    userBookmarks: [],
    alreadyRegister: false,
    user3Bookmarks: [],
  },
  mutations: {
    setUserBookmarks(state, bookmarks) {
      state.userBookmarks = bookmarks;
    },
    setAlreadyRegister(state, value) {
      state.alreadyRegister = value;
    },
    setUser3Bookmarks(state, bookmarks) {
      state.user3Bookmarks = bookmarks;
    },
  },
  actions: {
    async getBookmarks(context, accountId) {
      const url = `/bookmark/${accountId}`;
      const res = await axios.get(url);
      // console.log(res.data);
      context.commit("setUserBookmarks", res.data);
    },
    async addBookmark({ commit }, data) {
      const url = `/bookmark`;
      try {
        await axios.post(url, JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
          },
        });
        commit("setAlreadyRegister", true);
        return null;
      } catch (error) {
        console.log(error);
        commit("setAlreadyRegister", false);
        return error;
      }
    },
    async checkAlreadyBookmark({ commit }, data) {
      const url = `/bookmark/check`;
      const res = await axios.post(url, JSON.stringify(data), {
        headers: {
          "Content-Type": "application/json",
        },
      });
      commit("setAlreadyRegister", res.data);
    },
    async get3Bookmarks(context, accountId) {
      const url = `/bookmark/recent/${accountId}`;
      const res = await axios.get(url);
      // console.log(res.data);
      context.commit("setUser3Bookmarks", res.data);
    },
  },
  getters: {
    getUserBookmarks(state) {
      return state.userBookmarks;
    },
    getUser3Bookmarks(state) {
      return state.user3Bookmarks;
    },
  },
};
