import Vue from "vue";
import Vuex from "vuex";
import axios from "@/axios";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    travelPlanList: [],
    totalItems: 0,
    travelPlan: null,
    writer: null,
    travelPlanId: null,
    travelPlanTitle: null,
    travelInfo: null,
  },
  mutations: {
    SET_TRAVELPLANLIST(state, list) {
      state.travelPlanList = list;
    },
    SET_TOTALITEMS(state, items) {
      state.totalItems = items;
    },
    SET_TRAVELPLAN(state, plan) {
      state.travelPlan = plan;
    },
    SET_WRITER(state, writer) {
      state.writer = writer;
    },
    SET_TRAVELPLANID(state, id) {
      state.travelPlanId = id;
    },
    SET_TRAVELPLANTITLE(state, title) {
      state.travelPlanTitle = title;
    },
    SET_TRAVELINFO(state, info) {
      state.travelInfo = info;
    },
  },
  actions: {
    async getTravelPlanId({ commit }, accountId) {
      const url = `/travel/recent/${accountId}`;
      const res = await axios.get(url);
      commit("SET_TRAVELPLANID", res.data);
    },

    async save({}, value) {
      let result = [];
      console.log(value);
      for (let group of value.groups) {
        for (let item of group.items) {
          for (let id of value.data) {
            if (id.contentId === item.contentId) {
              //   console.log(
              //     group.items.findIndex(
              //       (item) => item.contentId === id.contentId
              //     ) + 1
              //   );
              result.push({
                travelPlanId: value.travelPlanId,
                contentOrder:
                  group.items.findIndex(
                    (item) => item.contentId === id.contentId
                  ) + 1,
                day: group.id,
                contentId: item.contentId,
              });
            }
          }
        }
      }
      console.log(result);
      const url = `/travel/plan`;
      try {
        await axios.post(url, JSON.stringify(result), {
          headers: {
            "Content-Type": "application/json",
          },
        });
        return null;
      } catch (error) {
        return error;
      }
    },
    async getTravelPlanList({ commit }, data) {
      let url = `/travel?currentPage=${data.currentPage}&perPage=${data.perPage}`;
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
      commit("SET_TRAVELPLANLIST", res.data);
    },
    async getTotalItems({ commit }, data) {
      let url = `/travel/totalitems?`;
      if (data.key !== null && data.key !== "") {
        url += `&key=${data.key}`;
      }
      if (data.word !== null && data.word !== "") {
        url += `&word=${data.word}`;
      }
      const res = await axios.get(url);
      commit("SET_TOTALITEMS", res.data);
    },
    async addTravelPlan({}, data) {
      // console.log(data);
      const url = "/travel";
      await axios.post(url, JSON.stringify(data), {
        headers: {
          "Content-Type": "application/json",
        },
      });
    },
    async getTravelPlan({ commit }, travelPlanId) {
      const url = `/travel/plan/${travelPlanId}`;
      const res = await axios.get(url);
      commit("SET_TRAVELPLAN", res.data);
    },
    async getAccountId({ commit }, travelPlanId) {
      const url = `/travel/accountId?travelPlanId=${travelPlanId}`;
      const res = await axios.get(url);
      commit("SET_WRITER", res.data);
    },

    async getInfo({ commit }, contentId) {
      const url = `/attraction/planinfo/${contentId}`;
      const res = await axios.get(url);
      commit("SET_TRAVELINFO", res.data);
    },

    async getTitle({ commit }, travelPlanId) {
      const url = `/travel/id?id=${travelPlanId}`;
      const res = await axios.get(url);
      commit("SET_TRAVELPLANTITLE", res.data);
    },

    async deleteTravelPlan({}, travelPlanId) {
      const url = `/travel/${travelPlanId}`;
      await axios.delete(url);
    },

    async deleteTravelPlanDestination({}, travelPlanId) {
      const url = `/travel/plan/${travelPlanId}`;
      await axios.delete(url);
    },
  },
  getters: {},
};
