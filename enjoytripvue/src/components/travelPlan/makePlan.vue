<template>
  <div>
    <Modal
      v-if="showModal"
      :message="confirmMessage"
      :type="type"
      @confirm="handleConfirm"
      @moveConfirm="moveConfirm"
      @cancel="cancelModal" />
    <div
      class="w-[90%] flex justify-between mx-auto items-center py-4 bg-gray-300 rounded-t-2xl shadow-xl mb-2 px-4">
      <div
        class="min-w-[8%] bg-blue-300 hover:bg-blue-400 px-4 py-2 text-center rounded-xl cursor-pointer text-black"
        @click="prevPage">
        뒤로가기
      </div>
      <div class="text-2xl font-bold">
        {{ travelPlanTitle }}
      </div>
      <div class="flex gap-2 min-w-[8%]" v-if="canModify">
        <div
          class="w-1/2 bg-blue-300 hover:bg-blue-400 px-4 py-2 text-center rounded-xl cursor-pointer text-black"
          @click="cancelModify">
          취소
        </div>
        <div
          class="w-1/2 bg-red-300 hover:bg-red-400 px-4 py-2 text-center rounded-xl cursor-pointer text-black"
          @click="deleteTravelPlan">
          삭제
        </div>
      </div>

      <div
        class="min-w-[8%] bg-blue-300 hover:bg-blue-400 px-4 py-2 text-center rounded-xl cursor-pointer text-black"
        v-if="!canModify"
        @click="checkHost">
        수정
      </div>
    </div>
    <div
      class="flex w-[90%] justify-center mx-auto space-x-8 bg-gray-300 rounded-b-xl mb-8 px-4 py-4">
      <div
        class="flex w-3/5 h-[60rem] rounded-xl overflow-hidden"
        id="map_view">
        <div
          id="map"
          style="
            width: 100%;
            height: 100%;
            position: relative;
            overflow: hidden;
          "></div>
      </div>
      <div class="flex flex-col w-2/5 h-[60rem]">
        <!-- 북마크 리스트 -->
        <div
          class="text-center items-center mb-8 rounded-xl overflow-hidden shadow-md bg-gray-200 min-h-[15%] max-h-[50%] overflow-y-scroll scrollbar"
          v-if="canModify">
          <p class="my-4 text-xl font-bold">{{ bookmarkList.name }}</p>
          <draggable
            v-model="bookmarkList.items"
            v-bind="draggableOptions"
            class="flex flex-row flex-wrap mx-4 w-full">
            <div
              class="border-gray-400 mb-4 mx-2"
              v-for="(item, itemIndex) in bookmarkList.items"
              :key="'item' + itemIndex">
              <div
                class="select-none cursor-move bg-white shadow-md rounded-md flex flex-1 items-center p-4 transition duration-500 ease-in-out transform hover:-translate-y-1 hover:shadow-lg">
                <div class="flex-1 pl-1">
                  <div class="font-medium text-center">
                    {{ item.title }}
                  </div>
                </div>
              </div>
            </div>
          </draggable>
        </div>
        <!-- 일정 리스트 -->
        <div class="overflow-y-scroll scrollbar h-full rounded-xl">
          <div
            class="rounded-xl overflow-hidden shadow-md mb-8 text-white"
            :class="color[group.id % color.length][0]"
            v-for="(group, index) in groups"
            :key="index">
            <div
              class="border-b-2 border-white flex flex-row justify-between items-center px-8 mb-4">
              <p class="my-4 text-xl font-bold text-center">
                {{ group.id + group.name }}
              </p>
              <div
                class="cursor-pointer border-2 border-gray-200 bg-white rounded-md py-1 px-2 text-black hover:text-white duration-300"
                :class="color[group.id % color.length][1]"
                @click="deleteDay(group.id)"
                v-if="canModify">
                삭제
              </div>
            </div>
            <draggable
              v-model="group.items"
              v-bind="draggableOptions"
              :disabled="!canModify">
              <div
                class="border-gray-400 flex flex-row mb-4 mx-8"
                v-for="(item, itemIndex) in group.items"
                :key="'item' + itemIndex">
                <div
                  class="flex items-center mr-8 font-bold justify-center text-2xl w-[5%]">
                  {{ itemIndex + 1 }}
                </div>
                <div
                  class="hoverList select-none bg-white shadow-md rounded-md flex flex-1 items-center p-4 transition duration-500 ease-in-out transform hover:-translate-y-1 hover:shadow-lg"
                  :class="canModify ? 'cursor-move' : 'cursor-pointer'"
                  @mouseover="handleMouseOver(item.contentId)">
                  <div class="flex pl-1 justify-between w-full items-center">
                    <div class="font-medium text-black">
                      <span class="text-lg flex mr-2">
                        {{ item.title }}
                      </span>
                      <span class="text-sm flex items-end">
                        {{ item.contentTypeName }}
                      </span>
                    </div>

                    <div
                      class="text-black border-2 border-gray-300 py-1 px-2 rounded-xl hover:bg-gray-300 cursor-pointer"
                      @click="!canModify && goPath(`detail/${item.contentId}`)">
                      더보기
                    </div>
                  </div>
                </div>
              </div>
            </draggable>
          </div>
        </div>
        <div class="flex px-4 gap-4">
          <template v-if="canModify">
            <div
              class="font-bold text-xl border-4 border-gray-400 w-1/2 bg-gray-300 duration-300 hover:bg-gray-400 rounded-xl text-center py-4 cursor-pointer my-4 shadow-md"
              @click="addDay">
              추가
            </div>
            <div
              class="font-bold text-xl border-4 border-gray-400 w-1/2 bg-gray-300 duration-300 hover:bg-gray-400 rounded-xl text-center py-4 cursor-pointer my-4 shadow-md"
              @click="savePlan">
              저장
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import { mapGetters, mapState } from "vuex";
import Modal from "@/components/modal/Confirm.vue";
import axios from "@/axios";
export default {
  components: {
    draggable,
    Modal,
  },
  data() {
    return {
      showModal: false,
      confirm: false,
      confirmMessage: "현재 페이지를 벗어나면 경로 생성이 저장되지 않습니다.",
      type: "move",
      day: 0,
      bookmarkList: {
        name: "북마크",
        items: [],
      },

      groups: [],
      draggableOptions: {
        group: "myGroup",
        animation: 150,
        pull: true,
        put: true,
      },
      map: null,
      markers: [],
      customWindow: [],
      infoWindows: [],
      clusterer: null,
      canModify: false,
      movePath: null,
      isdelete: null,
      color: [
        ["bg-slate-400", , "hover:bg-slate-400"],
        ["bg-red-400", "hover:bg-red-500"],
        ["bg-orange-400", "hover:bg-orange-400"],
        ["bg-yellow-400", "hover:bg-yellow-400"],
        ["bg-lime-400", "hover:bg-lime-400"],
        ["bg-green-400", "hover:bg-green-400"],
        ["bg-teal-400", "hover:bg-teal-400"],
        ["bg-cyan-400", "hover:bg-cyan-400"],
        ["bg-blue-400", "hover:bg-blue-400"],
        ["bg-violet-400", "hover:bg-violet-400"],
        ["bg-pink-400", "hover:bg-pink-400"],
      ],
    };
  },
  mounted() {
    this.checkScript();
  },
  computed: {
    ...mapState("auth", { userInfo: "info" }),
    ...mapState("travelplan", [
      "travelPlan",
      "writer",
      "travelPlanId",
      "travelInfo",
      "travelPlanTitle",
    ]),
    ...mapState("kakaoMap", ["nowPosition"]),
  },
  created() {
    if (this.$route.params.id) {
      this.$store
        .dispatch("travelplan/getTravelPlan", this.$route.params.id)
        .then(() => {
          // console.log(this.travelPlan);
          this.loadPlan();
        });
      this.$store.dispatch("travelplan/getAccountId", this.$route.params.id);
      this.$store.dispatch("travelplan/getTitle", this.$route.params.id);
    }
    if (this.$route.path === "/travelplan") {
      this.$store
        .dispatch("bookmark/getBookmarks", this.userInfo.id)
        .then(() => {
          this.bookmarkList.items = this.$store.state.bookmark.userBookmarks;
          this.$store
            .dispatch("travelplan/getTravelPlanId", this.userInfo.id)
            .then(() => {
              this.$store.dispatch("travelplan/getTitle", this.travelPlanId);
            });
        });
    }
    this.$store.dispatch("kakaoMap/getGeoLocation");
  },
  methods: {
    prevPage() {
      this.$router.go(-1);
    },
    deleteTravelPlan() {
      console.log("삭제");
      this.type = "delete";
      this.confirmMessage = "정말 삭제하시겠습니까?";
      this.isdelete = true;
      this.$router.push("/travelboard");
    },
    handleConfirm(value) {
      this.showModal = false;
      this.confirm = value;
      this.$router.push(this.movePath.path);
    },
    cancelModify() {
      this.canModify = false;
    },
    handleMouseOver(contentId) {
      for (let group of this.groups) {
        for (let item of group.items) {
          if (item.contentId == contentId) {
            console.log(item);
            this.map.setCenter(
              new kakao.maps.LatLng(item.latitude, item.longitude)
            );
            this.map.setLevel(4);
          }
        }
      }
    },
    showConfirm() {
      this.showModal = true;
    },
    moveConfirm(value) {
      this.showModal = false;
      this.confirm = value;
      this.$router.push(this.movePath.path);
    },
    cancelModal() {
      this.showModal = false;
    },
    addDay() {
      this.day += 1;
      this.groups.push({
        id: this.day,
        name: `일차`,
        items: [],
      });
    },
    deleteDay(id) {
      this.day -= 1;
      const index = this.groups.findIndex((group) => group.id === id);
      if (index !== 0) {
        const deleteGroup = this.groups.splice(index, 1)[0];
        const previousGroup = this.groups[index - 1];
        if (previousGroup) {
          previousGroup.items.push(...deleteGroup.items);
        }
        this.groups.forEach((group, i) => {
          group.id = i + 1;
        });
      } else {
        const deleteGroup = this.groups.splice(index, 1)[0];
        const previousGroup = this.bookmarkList;
        if (previousGroup) {
          previousGroup.items.push(...deleteGroup.items);
        }
        this.groups.forEach((group, i) => {
          group.id = i + 1;
        });
      }
    },
    savePlan() {
      console.log("1");
      if (this.$route.path === "/travelplan") {
        console.log("2");
        this.$store
          .dispatch("travelplan/getTravelPlanId", this.userInfo.id)
          .then(() => {
            this.$store.dispatch("travelplan/save", {
              groups: this.groups,
              data: this.$store.state.bookmark.userBookmarks,
              travelPlanId: this.travelPlanId,
            });
            this.$router.push("/travelboard");
          });
      } else {
        console.log("3");
        this.$store
          .dispatch("travelplan/deleteTravelPlanDestination", this.travelPlanId)
          .then(() => {
            this.$store.dispatch("travelplan/save", {
              groups: this.groups,
              data: this.$store.state.bookmark.userBookmarks,
              travelPlanId: this.travelPlanId,
            });
            this.$router.push("/travelboard");
          });
      }
    },
    checkHost() {
      if (this.userInfo.id === this.writer) {
        this.canModify = true;
        this.$store.dispatch("bookmark/getBookmarks", this.writer).then(() => {
          let checkId = [];
          for (let group of this.groups) {
            for (let item of group.items) {
              checkId.push(item.contentId);
            }
          }
          const userBookmarks = this.$store.state.bookmark.userBookmarks;
          this.bookmarkList.items = [...userBookmarks];
          for (let i = this.bookmarkList.items.length - 1; i >= 0; i--) {
            if (checkId.includes(this.bookmarkList.items[i].contentId)) {
              this.bookmarkList.items.splice(i, 1);
            }
          }
        });
      } else {
        alert("수정 권한이 없습니다.");
      }
    },
    async loadPlan() {
      let maxDay = 0;
      for (let plan of this.travelPlan) {
        maxDay = Math.max(maxDay, plan.day);
      }
      for (let i = 0; i < maxDay; i++) {
        this.addDay();
      }
      let countDayItems = Array(maxDay).fill(0);
      for (let group of this.groups) {
        for (let plan of this.travelPlan) {
          if (plan.day === group.id) {
            countDayItems[plan.day - 1]++;
          }
        }
      }
      for (let group of this.groups) {
        group.items = Array(countDayItems[group.id - 1]).fill(0);
      }
      await this.loadInfo();
    },
    async loadInfo() {
      for (let plan of this.travelPlan) {
        await this.$store.dispatch("travelplan/getInfo", plan.contentId);
        this.groups[plan.day - 1].items.splice(
          plan.contentOrder - 1,
          1,
          this.travelInfo
        );
      }
    },
    checkScript() {
      if (window.kakao && window.kakao.maps) {
        this.loadMap();
      } else {
        this.loadScript();
      }
    },
    loadScript() {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_API_KAKAO_KEY}&libraries=clusterer&autoload=false`;
      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
    async loadMap() {
      if (this.map === null) {
        const mapContainer = document.getElementById("map");
        const mapOption = {
          center: new kakao.maps.LatLng(37.566826, 126.978656),
          level: 11,
        };
        this.map = await new window.kakao.maps.Map(mapContainer, mapOption);
        const zoomControl = new kakao.maps.ZoomControl();
        this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      }
      // if (this.clusterer === null) {
      //   this.clusterer = await new kakao.maps.MarkerClusterer({
      //     map: this.map,
      //     markers: this.markers,
      //     gridSize: 60,
      //     averageCenter: true,
      //     minLevel: 5,
      //   });
      // }
      // setTimeout(() => {
      //   this.isMapLoad();
      // }, 3000);
    },
    isMapLoad() {
      if (this.canModify === true) {
        let pingList = [];
        for (let group of this.groups) {
          for (let item of group.items) {
            pingList.push(item);
          }
        }
        if (
          this.$store.state.bookmark.userBookmarks &&
          this.$store.state.bookmark.userBookmarks.length == 0
        ) {
          this.loadResultToMap(pingList);
        } else {
          const userBookmarks = this.bookmarkList.items;
          this.loadResultToMap({ ...userBookmarks, ...pingList });
        }
      } else {
        let pingList = [];
        for (let group of this.groups) {
          for (let item of group.items) {
            pingList.push(item);
          }
        }
        // console.log(pingList);
        this.loadResultToMap(pingList);
      }
    },
    check() {
      if (this.map === null) {
        return false;
      }
      return true;
    },
    loadResultToMap(result) {
      if (!this.check()) {
        return;
      }
      this.clearMarkers();
      let totalPoint = 0;
      let totalLatitude = 0;
      let totalLongitude = 0;
      totalPoint = result.length;
      for (let i in result) {
        const data = result[i];
        totalLatitude += Number(data.latitude);
        totalLongitude += Number(data.longitude);
        const position = new kakao.maps.LatLng(data.latitude, data.longitude);
        this.addMarker(position, data);
      }
      let nowPosition;
      if (this.nowPosition) {
        nowPosition = new kakao.maps.LatLng(
          this.nowPosition.latitude,
          this.nowPosition.longitude
        );
      } else {
        nowPosition = new kakao.maps.LatLng(37.566826, 126.978656);
      }
      this.setMarkers(1);
      this.map.setLevel(this.map.getLevel());
      const center = new kakao.maps.LatLng(
        totalLatitude / totalPoint,
        totalLongitude / totalPoint
      );
      if (totalPoint !== 0) {
        if (totalLatitude / totalPoint) {
          this.map.setCenter(center);
        } else {
          this.map.setCenter(nowPosition);
        }
      } else {
        this.map.setCenter(nowPosition);
      }
    },

    // position : 맵 위에 있는 핑의 위도, 경도
    // message : 인포 윈도우에 넣을 message
    addMarker(position, data) {
      const imgSrc = "";
      const imgSize = new kakao.maps.Size(0, 0);
      const imgOption = { offset: new kakao.maps.Point(0, 0) };
      const markerImage = new kakao.maps.MarkerImage(
        imgSrc,
        imgSize,
        imgOption
      );
      const marker = new kakao.maps.Marker({
        image: markerImage,
        position: position,
      });
      marker.setMap(this.map);
      this.markers.push(marker);

      // 인포윈도우에 표시할 내용
      let day = 0;
      let order = 0;
      for (let group of this.groups) {
        for (let item of group.items) {
          if (data.contentId === item.contentId) {
            day = group.id;
            order =
              group.items.findIndex(
                (item) => item.contentId === data.contentId
              ) + 1;
          }
        }
      }
      const infow = document.createElement("div");
      infow.classList.add(
        "w-[30px]",
        "h-[30px]",
        "cursor-pointer",
        "rounded-full",
        "opacity-75",
        "hover:opacity-100",
        this.color[day % this.color.length][0],
        this.color[day % this.color.length][1]
      );
      const text = document.createElement("span");
      text.innerText = order;
      text.classList.add(
        "flex",
        "items-center",
        "justify-center",
        "bg-none",
        "font-bold",
        "text-2xl"
      );
      infow.appendChild(text);
      infow.addEventListener("click", () => {
        this.goPath(`detail/${data.contentId}`);
      });
      infow.addEventListener("mouseover", () => {
        infowindow.setMap(this.map);
      });
      infow.addEventListener("mouseout", () => {
        infowindow.setMap(null);
      });
      const customWindow = new kakao.maps.CustomOverlay({
        content: infow,
        map: this.map,
        position: marker.getPosition(),
      });
      customWindow.setMap(this.map);

      // title을 보여주는 window
      const iwContent = `<div class="w-full bg-white rounded-full px-4 py-2">${data.title}</div>`;
      const infowindow = new kakao.maps.CustomOverlay({
        content: iwContent,
        map: this.map,
        position: marker.getPosition(),
        yAnchor: 1.55,
      });
      infowindow.setMap(null);

      this.customWindow.push(customWindow);
      this.infoWindows.push(infowindow);
    },
    setMarkers(value) {
      if (value === null) {
        // this.clusterer.clear();
        // this.clusterer.removeMarkers(this.markers);
      } else {
        for (let marker of this.markers) {
          if (value === null) {
            marker.setMap(null);
          } else {
            marker.setMap(this.map);
          }
        }
        // this.clusterer.addMarkers(this.markers);
      }
    },
    clearMarkers() {
      this.setMarkers(null);
      this.markers = [];
      for (let custom of this.customWindow) {
        custom.setMap(null);
      }
      this.customWindow = [];
      for (let info of this.infoWindows) {
        info.setMap(null);
      }
      this.infoWindows = [];
    },
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
  },
  watch: {
    groups: {
      deep: true,
      handler(newGroups, oldGroups) {
        this.isMapLoad();
      },
    },
    $route: {
      immediate: true,
      handler(to, from) {
        if (to.path === "/travelplan/:id") {
          this.canModify = false;
          this.$store.dispatch(
            "travelplan/getTravelPlan",
            this.$route.params.id
          );
        } else if (to.path === "/travelplan") {
          this.canModify = true;
          this.$store
            .dispatch("bookmark/getBookmarks", this.userInfo.id)
            .then(() => {
              this.bookmarkList.items =
                this.$store.state.bookmark.userBookmarks;
            });
        }
      },
    },
  },
  async beforeRouteLeave(to, from, next) {
    console.log(this.confirm);
    if (this.confirm) {
      console.log("1");
      if (this.isdelete) {
        console.log("2");
        await this.$store.dispatch(
          "travelplan/deleteTravelPlan",
          this.travelPlanId
        );
      }
      console.log(next);
      next();
    } else {
      console.log("4");
      if (from.path.includes("/travelplan")) {
        console.log("5");
        if (this.groups.length == 0) {
          console.log("6");
          // this.$store.dispatch(
          //   "travelplan/deleteTravelPlan",
          //   this.travelPlanId
          // );
          this.isdelete = true;
          this.showConfirm();
          this.movePath = to;
        } else if (!this.isdelete && this.groups.length >= 1) {
          next();
        } else if (this.isdelete) {
          console.log("3");
          this.showConfirm();
          this.movePath = to;
        } else {
          this.showConfirm();
          this.movePath = to;
        }
      }
    }
  },
};
</script>

<style lang="scss" scoped>
.scrollbar::-webkit-scrollbar {
  @apply w-3
        rounded-xl;
}
.scrollbar::-webkit-scrollbar-thumb {
  @apply bg-gray-400
        rounded-xl;
}
.scrollbar::-webkit-scrollbar-track {
  @apply bg-gray-200
        rounded-xl;
}
</style>
