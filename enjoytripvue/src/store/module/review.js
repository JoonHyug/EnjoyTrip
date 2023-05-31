import Vue from "vue";
import Vuex from "vuex";
import axios from "@/axios";
import jwtDecode from "jwt-decode";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    reviewResult: null,
    userReviews: null,
    noData: true,
    user3Reviews: null,
    totalItem: null,
    contentTotalItem: null,
    reviewedId: null,
    avgPoint: null,
  },
  mutations: {
    setReviewResult(state, result) {
      state.reviewResult = result;
    },
    setUserReviews(state, reviews) {
      state.userReviews = reviews;
    },
    setNoData(state, data) {
      state.noData = data;
    },
    setUser3Reviews(state, reviews) {
      state.user3Reviews = reviews;
    },
    SET_TOTAL_ITEM(state, item) {
      state.totalItem = item;
    },
    SET_CONTENT_TOTAL_ITEM(state, item) {
      state.contentTotalItem = item;
    },
    SET_REVIEWED_ID(state, id) {
      state.reviewedId = id;
    },
    SET_AVG_POINT(state, point) {
      state.avgPoint = point;
    },
  },
  actions: {
    addReview(context, data) {
      // console.log(decodeToken);
      axios
        .post("/review", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          // console.log(res);
          context.dispatch("getReview", data.contentId);
          alert("등록 완료");
        })
        .catch((err) => {
          // console.log(err);
          // 관리자에게 등록 요청 보내기
          alert("등록 실패");
        });
    },
    async getReview(context, contentId) {
      try {
        const url = `/review/content/${contentId}`;
        await context.dispatch("checkContentId", contentId);
        const res = await axios.get(url);
        context.commit("setReviewResult", res.data);
      } catch (error) {
        console.log("리뷰가 없음", error);
      }
    },
    async checkContentId({ commit }, contentId) {
      const url = `/attraction/check/${contentId}`;
      const res = await axios.get(url);
      commit("setNoData", res.data);
    },

    async getReviewsToAccountId({ commit }, data) {
      let url = `/review/user/${data.accountId}?currentPage=${data.currentPage}&perPage=${data.perPage}`;
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
      const res = await axios.get(url);
      commit("setUserReviews", res.data);
    },
    async get3ReviewsToAccountId({ commit }, accountId) {
      const url = `/review/recent/${accountId}`;
      const res = await axios.get(url);
      commit("setUser3Reviews", res.data);
    },
    async getTotalItemsByAccountId({ commit }, data) {
      let url = `/review/user/totalItems/${data.accountId}?`;
      if (data.key !== null && data.key !== "") {
        url += `&key=${data.key}`;
      }
      if (data.word !== null && data.word !== "") {
        url += `&word=${data.word}`;
      }
      const res = await axios.get(url);
      commit("SET_TOTAL_ITEM", res.data);
    },
    async getTotalItemsByContentId({ commit }, contentId) {
      const url = `/review/totalreview/${contentId}`;
      const res = await axios.get(url);
      commit("SET_CONTENT_TOTAL_ITEM", res.data);
    },
    async getReviewedIdByContentId({ commit }, contentId) {
      const url = `/review/reviewedid/${contentId}`;
      const res = await axios.get(url);
      commit("SET_REVIEWED_ID", res.data);
    },
    async getAvgPointByContentId({ commit }, contentId) {
      const url = `/review/avgpoint/${contentId}`;
      const res = await axios.get(url);
      commit("SET_AVG_POINT", res.data);
    },
  },
  getters: {
    getReviewResult(state) {
      return state.reviewResult;
    },
    getUserReviews(state) {
      return state.userReviews;
    },
    getNoData(state) {
      return state.noData;
    },
    getUser3Reviews(state) {
      return state.user3Reviews;
    },
  },
};
