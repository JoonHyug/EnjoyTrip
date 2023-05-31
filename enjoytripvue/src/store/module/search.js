import Vue from "vue";
import Vuex from "vuex";
import axios from "@/axios";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    searchResult: [],
    isOpen: false,
    isLocalData: true,
  },

  mutations: {
    setSearchResult(state, result) {
      state.searchResult = result;
    },
    setIsOpen(state, open) {
      state.isOpen = open;
    },
    setIsLocalData(state, data) {
      state.isLocalData = data;
    },
  },

  actions: {
    search(context, payload) {
      const { searchKeyword, contentItems, sidoItem, gugunItem } = payload;
      let search_url = "/attraction";
      let json = {};
      if (searchKeyword != null) {
        json.keyword = searchKeyword;
      }
      if (contentItems.length == 0) {
        alert("콘텐츠를 선택해 주세요.");
        return;
      } else {
        let cat = new Array();
        for (let content of contentItems) {
          cat.push(content);
        }
        json.category = cat;
      }
      if (sidoItem == null) {
        alert("시도를 선택해 주세요.");
      } else {
        json.sidoCode = sidoItem;
      }
      if (gugunItem == null) {
        alert("구/군을 선택해 주세요.");
      } else {
        json.gugunCode = gugunItem;
      }
      axios
        .post(search_url, JSON.stringify(json), {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          const result = response.data;
          context.commit("setSearchResult", result); // mutation 호출
        })
        .catch((error) => {
          console.log("Error : ", error);
        });
      context.commit("setIsLocalData", true);
    },
    async geolocationSearch(context, data) {
      const myLocate = data.myLocate;
      const contentItems = data.contentItems;
      if (contentItems.length == 0) {
        alert("콘텐츠를 선택해주세요.");
        return;
      }
      let result = [];
      for (let content of contentItems) {
        const url = `?&numOfRows=${100}&pageNo=1&MobileOS=ETC&MobileApp=MobileApp&_type=json&mapX=${
          myLocate.longitude
        }&mapY=${myLocate.latitude}&radius=${
          5 * 1000
        }&contentTypeId=${content}&serviceKey=Cksl%2BGSpyo7qp56IK%2BEBEN8Sb8TLBvaZo2UkKXQq%2FEH63Ym60M5HyG2Kcq5he%2Bc8%2B7CorzeYSq0GRBCiaB4bww%3D%3D`;
        await axios
          .get(
            "http://apis.data.go.kr/B551011/KorService1/locationBasedList1" +
              url
          )
          .then((res) => {
            const item = res.data.response.body.items.item;
            for (let i in item) {
              const d = {
                addr1: item[i].addr1,
                addr2: item[i].addr2,
                contentId: item[i].contentid,
                contentTypeId: item[i].contenttypeid,
                firstImage: item[i].firstimage,
                firstImage2: item[i].firstimage2,
                gugunCode: item[i].sigungucode,
                latitude: item[i].mapy,
                longitude: item[i].mapx,
                mlevel: item[i].mlevel,
                sidoCode: item[i].areacode,
                tel: item[i].tel,
                title: item[i].title,
                zipcode: null,
                govData: true,
              };
              result.push(d);
            }
          });
      }
      context.commit("setSearchResult", result);
      context.commit("setIsLocalData", false);
    },
  },
  getters: {
    getSearchResult(state) {
      return state.searchResult;
    },
    getIsOpen(state) {
      return state.isOpen;
    },
    getIsLocalData(state) {
      return state.isLocalData;
    },
  },
};
