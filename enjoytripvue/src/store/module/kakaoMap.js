import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    nowPosition: null,
    markers: [],
    infoWindows: [],
  },
  mutations: {
    setNowPosition(state, position) {
      state.nowPosition = position;
    },
    setMarkers(state, markers) {
      state.markers = markers;
    },
    setInfoWindows(state, info) {
      state.infoWindows = info;
    },
  },
  actions: {
    async getGeoLocation(context) {
      //geolocation을 이용할 수 있는지 체크
      if (navigator.geolocation) {
        try {
          navigator.geolocation.getCurrentPosition((position) => {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            context.commit("setNowPosition", { latitude, longitude });
          });
        } catch (error) {
          console.log(error);
        }
      } else {
        context.commit("setNowPosition", {
          latitude: 33.450701,
          longitude: 126.570667,
        });
      }
    },
  },
  getters: {
    getNowPosition(state) {
      return state.nowPosition;
    },
    getMarkers(state) {
      return state.markers;
    },
    getInfowindows(state) {
      return state.infoWindows;
    },
  },
};
