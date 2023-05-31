import Vue from "vue";
import Vuex from "vuex";
import axios from "@/axios";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    noticeBoardResult: [],
    totalItems: 0,
    noticeDetail: null,
    type: null,
  },
  mutations: {
    SET_NOTICEBOARDRESULT(state, result) {
      state.noticeBoardResult = result;
    },
    SET_TOTALITEMS(state, items) {
      state.totalItems = items;
    },
    SET_NOTICEDETAIL(state, detail) {
      state.noticeDetail = detail;
    },
    SET_TYPE(state, type) {
      state.type = type;
    },
  },
  actions: {
    async getNoticeBoard({ commit }, data) {
      // key는 subject나 accountId만 사용 가능
      let url = `/board?currentPage=${data.currentPage}&perPage=${data.perPage}`;
      if (data.sort !== null && data.sort !== "") {
        url += `&sort=${data.sort}`;
      }
      if (data.key !== null && data.key !== "") {
        url += `&key=${data.key}`;
      }
      if (data.word !== null && data.word !== "") {
        url += `&word=${data.word}`;
      }
      if (data.order !== null && data.order !== "") {
        url += `&order=${data.order}`;
      }
      if (data.type !== null && data.type !== "") {
        url += `&type=${data.type}`;
        commit("SET_TYPE", data.type);
      }

      const res = await axios.get(url);
      commit("SET_NOTICEBOARDRESULT", res.data);
    },
    async addNoticeBoard(context, data) {
      const url = "/board";
      await axios.post(url, JSON.stringify(data), {
        headers: {
          "Content-Type": "application/json",
        },
      });
    },
    async getTotalItems({ commit }, data) {
      let url = "/board/totalitems?";
      if (data.key !== null && data.key !== "") {
        url += `&key=${data.key}`;
      }
      if (data.word !== null && data.word !== "") {
        url += `&word=${data.word}`;
      }
      if (data.type !== null && data.type !== "") {
        url += `&type=${data.type}`;
      }
      const res = await axios.get(url);
      commit("SET_TOTALITEMS", res.data);
    },
    async detail({ commit }, articleno) {
      const url = `/board/${articleno}`;
      const res = await axios.get(url);
      commit("SET_NOTICEDETAIL", res.data);
    },

    async delete({}, articleno) {
      const url = `/board/${articleno}`;
      await axios.delete(url);
    },
    async edit({}, data) {
      const url = `/board`;
      await axios.put(url, JSON.stringify(data), {
        headers: {
          "Content-Type": "application/json",
        },
      });
    },
  },
  getters: {},
};
