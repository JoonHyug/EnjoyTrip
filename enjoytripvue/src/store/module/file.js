import Vue from "vue";
import Vuex from "vuex";
import axios from "@/axios";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    profileImage: null,
    profileImages: null,
  },
  mutations: {
    SET_PROFILE_IMAGE(state, image) {
      state.profileImage = image;
    },
    SET_PROFILE_IMAGES(state, images) {
      state.profileImages = images;
    },
  },
  actions: {
    async uploadFile({}, file) {
      let url = `/file/upload?accountId=${file.accountId}&type=${file.type}`;
      // console.log(file);
      try {
        const res = await axios.post(url, file.uploadFile, {
          headers: {
            "Content-Type ": "multipart/form-data",
          },
        });
        alert("등록 성공");
      } catch (error) {
        alert("등록 실패");
      }
    },

    async getProfileImage({ commit }, data) {
      try {
        const url = `/file?accountId=${data.accountId}&type=${data.type}`;
        const res = await axios.get(url);
        // console.log("res", res.data);
        const url_id = `/file/${res.data}`;
        const res2 = await axios.get(url_id);
        commit("SET_PROFILE_IMAGE", res2.data.picName);
        console.log(res2.data.picName);
      } catch (error) {}
    },

    async getAllAccountPicture({ commit }, accountIds) {
      const url = `/file/allaccount`;
      const res = await axios.post(url, JSON.stringify(accountIds), {
        headers: {
          "Content-Type": "application/json",
        },
      });
      let data = [];
      for (let d of res.data) {
        data.push({ accountId: d.accountId, picName: d.picName });
      }
      commit("SET_PROFILE_IMAGES", data);
    },
  },
  getters: {},
};
