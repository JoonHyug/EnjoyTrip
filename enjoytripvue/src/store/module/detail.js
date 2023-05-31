import Vue from "vue";
import Vuex from "vuex";
import axios from "@/axios";
import qs from "qs";

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    detailResult: null,
  },
  mutations: {
    setDetailResult(state, detailResult) {
      state.detailResult = detailResult;
    },
  },
  actions: {
    async detail(context, contentId) {
      const detail_url = `/attraction/${contentId}`;
      try {
        const res = await axios.get(detail_url);
        const item = res.data;
        const result = {
          contentId: item.info.contentId,
          contentTypeId: item.info.contentTypeId,
          addr1: item.info.addr1,
          addr2: item.info.addr2,
          firstImage: item.info.firstImage,
          firstImage2: item.info.firstImage2,
          overview: item.description.overview,
          tel: item.info.tel,
          title: item.info.title,
          zipcode: item.info.zipcode,
          gugunCode: item.info.gugunCode,
          sidoCode: item.info.sidoCode,
          latitude: item.info.latitude,
          longitude: item.info.longitude,
          readcount: item.info.readcount,
          homePage: item.description.hompage,
          isApi: false,
        };
        // console.log("로컬 : ", result);
        context.commit("setDetailResult", result);
      } catch (error) {
        await context.dispatch("apiDetail", contentId);
        console.log(
          "DetailError : 해당 콘텐트 ID가 DB에 존재하지 않아 API 실행",
          error
        );
      }
    },
    async apiDetail(context, contentId) {
      // DB에 해당 정보가 없다는 뜻이기 때문에 DB에 넣기 위한 코드가 필요
      try {
        const res = await axios.get(
          "https://apis.data.go.kr/B551011/KorService1/detailCommon1",
          {
            params: {
              MobileOS: "ETC",
              MobileApp: "MobileApp",
              _type: "json",
              contentId: contentId,
              defaultYN: "Y",
              firstImageYN: "Y",
              areacodeYN: "Y",
              addrinfoYN: "Y",
              mapinfoYN: "Y",
              overviewYN: "Y",
              serviceKey: "공공데이터포털 서비스 키",
            },
            paramsSerializer: function (params_1) {
              return qs.stringify(params_1, { encode: false });
            },
          }
        );
        const item = res.data.response.body.items.item[0];
        const result = {
          contentId: item.contentid,
          contentTypeId: item.contenttypeid,
          addr1: item.addr1,
          addr2: item.addr2,
          firstImage: item.firstimage,
          firstImage2: item.firstimage2,
          overview: item.overview,
          tel: item.tel,
          title: item.title,
          zipcode: item.zipcode,
          gugunCode: item.siguguncode,
          sidoCode: item.areacode,
          latitude: item.mapy,
          longitude: item.mapx,
          readcount: 0,
          homePage: item.homepage,
          isApi: true,
        };
        // console.log(item);
        console.log("API : ", result);
        context.commit("setDetailResult", result);
      } catch (err) {
        console.log("ApiDetailError : ", err);
      }
    },
    // DB에 해당 contentId에 대한 정보를 등록해달라는 요청을 보내는 메소드
    async registrationRequest({}, data) {
      try {
        await axios.post(
          "/registration",
          JSON.stringify({
            contentId: data.detailResult.contentId,
            accountId: data.user.id,
          }),
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        alert("등록 요청 성공");
      } catch (error) {
        alert("이미 등록 요청을 보냈습니다.");
      }
    },
    addDetailInDB({}, data) {},
  },
  getters: {
    getDetailResult(state) {
      return state.detailResult;
    },
    getDetailContentId(state) {
      return state.detailResult.contentId;
    },
  },
};
