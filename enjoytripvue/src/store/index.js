import Vue from "vue";
import Vuex from "vuex";
import search from "./module/search.js";
import detail from "./module/detail.js";
import kakaoMap from "./module/kakaoMap.js";
import review from "./module/review.js";
import auth from "./module/auth.js";
import bookmark from "./module/bookmark.js";
import createVuexPersistedState from "vuex-persistedstate";
import movePath from "./module/movePath.js";
import board from "./module/board.js";
import travelplan from "./module/travelPlan.js";
import file from "./module/file.js";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    search,
    detail,
    kakaoMap,
    review,
    auth,
    bookmark,
    movePath,
    board,
    travelplan,
    file,
  },
  plugins: [
    createVuexPersistedState({
      key: "token",
      paths: ["auth.token"],
    }),
  ],
});

// import Vue from "vue";
// import Vuex from "vuex";

// Vue.use(Vuex);

// export default {
//   namespaced: true,
//   state: {},
//   mutations: {},
//   actions: {},
//   getters: {},
// };
