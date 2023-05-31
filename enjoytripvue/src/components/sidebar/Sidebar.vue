<template>
  <div>
    <aside class="flex">
      <div
        ref="scrollContainer"
        class="sidebar top-0 bottom-0 lg:left-0 overflow-y-auto text-center bg-gray-900 p-2 w-[25rem]"
        style="z-index: 10"
        v-bind:class="{ 'is-open': !isOpen }">
        <div class="text-gray-100 text-xl">
          <div class="p-2.5 mt-1 flex justify-between">
            <h1
              class="home font-bold text-gray-200 text-[25px] ml-3"
              @click="home"
              style="cursor: pointer">
              Enjoy Trip
            </h1>
            <i
              class="bi bi-chevron-down text-3xl rotate-90 cursor-pointer"
              @click="controllNav"></i>
          </div>
          <!-- 로그인 -->
          <div class="my-4 bg-gray-600 h-[1px]"></div>
          <div class="flex flex-wrap">
            <div
              class="p-2.5 flex-1 items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-600 text-white justify-center"
              @click="goPath('board/notice')">
              <i class="bi bi-chat-left-text-fill"></i><br />
              <!-- 사람 모양 svg -->
              <!-- <i class="bi bi-person-fill"></i> -->
              <span class="text-[15px] text-gray-200 font-bold">공지사항</span>
            </div>
            <div
              class="p-2.5 flex-1 items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-600 text-white justify-center"
              @click="goPath('board/board')">
              <i class="bi bi-bookmark-fill"></i><br />
              <span class="text-[15px] text-gray-200 font-bold">커뮤니티</span>
            </div>
            <div
              class="p-2.5 flex-1 items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-600 text-white justify-center"
              @click="goPath('travelboard')">
              <i class="bi bi-body-text"></i><br />
              <span class="text-[15px] text-gray-200 font-bold">여행계획</span>
            </div>
            <div
              class="p-2.5 flex-1 items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-600 text-white justify-center"
              @click="isAuthenticated ? logout() : login()">
              <i class="bi bi-box-arrow-in-left"></i><br />
              <span class="text-[15px] text-gray-200 font-bold">{{
                isAuthenticated ? "로그아웃" : "로그인"
              }}</span>
            </div>
          </div>

          <!-- 밑줄 -->
          <div class="my-4 bg-gray-600 h-[1px]"></div>
        </div>
        <!-- 지도, 카드리스트 전환 -->
        <!-- url이 /search가 아니면 비활성화 -->
        <div class="flex my-4" :class="{ hidden: !isSearch }">
          <ul class="grid w-full gap-4 md:grid-cols-2">
            <li class="justify-stretch flex">
              <input
                type="radio"
                id="kakao_map"
                value=""
                class="hidden peer"
                name="select_view"
                @click="kakaoMapView"
                :checked="!searchType" />
              <label
                for="kakao_map"
                class="w-full items-center p-3 text-gray-500 bg-white border-2 border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-700 peer-checked:border-blue-600 hover:text-gray-600 dark:peer-checked:text-gray-300 peer-checked:text-gray-600 hover:bg-gray-50 dark:text-gray-400 dark:bg-gray-800 dark:hover:bg-gray-700">
                <div class="block">
                  <div class="w-full text-sm">지도</div>
                </div>
              </label>
            </li>
            <li class="justify-stretch flex">
              <input
                type="radio"
                id="card_list"
                value=""
                class="hidden peer"
                name="select_view"
                @click="cardListView"
                :checked="searchType" />
              <label
                for="card_list"
                class="w-full items-center p-3 text-gray-500 bg-white border-2 border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-700 peer-checked:border-blue-600 hover:text-gray-600 dark:peer-checked:text-gray-300 peer-checked:text-gray-600 hover:bg-gray-50 dark:text-gray-400 dark:bg-gray-800 dark:hover:bg-gray-700">
                <div class="block">
                  <div class="w-full text-sm">카드</div>
                </div>
              </label>
            </li>
          </ul>
        </div>
        <div
          class="w-full my-4 py-2 text-center rounded-lg bg-blue-400 hover:bg-blue-500 text-white shadow-md cursor-pointer"
          id="current_location"
          @click="nowLocationSearch">
          현재 위치
        </div>
        <div class="relative text-gray-600 mb-2">
          <input
            type="search"
            name="serch"
            placeholder="검색에 포함될 단어를 입력하세요."
            class="bg-white h-10 w-full px-5 pr-10 rounded-full text-sm focus:outline-none"
            id="search"
            v-model="searchKeyword" />
          <button type="submit" class="absolute right-0 top-0 mt-3 mr-4">
            <svg
              class="h-4 w-4 fill-current"
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
              version="1.1"
              id="Capa_1"
              x="0px"
              y="0px"
              viewBox="0 0 56.966 56.966"
              style="enable-background: new 0 0 56.966 56.966"
              xml:space="preserve"
              width="512px"
              height="512px">
              <path
                d="M55.146,51.887L41.588,37.786c3.486-4.144,5.396-9.358,5.396-14.786c0-12.682-10.318-23-23-23s-23,10.318-23,23  s10.318,23,23,23c4.761,0,9.298-1.436,13.177-4.162l13.661,14.208c0.571,0.593,1.339,0.92,2.162,0.92  c0.779,0,1.518-0.297,2.079-0.837C56.255,54.982,56.293,53.08,55.146,51.887z M23.984,6c9.374,0,17,7.626,17,17s-7.626,17-17,17  s-17-7.626-17-17S14.61,6,23.984,6z" />
            </svg>
          </button>
        </div>

        <!-- 콘텐츠 드롭다운 -->
        <div
          class="p-2.5 mt-3 mb-3 flex items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-600 text-white"
          @click="dropdown_content">
          <i class="bi bi-tags-fill"></i>
          <div class="flex justify-between w-full items-center">
            <span class="text-[15px] ml-4 text-gray-200 font-bold">콘텐츠</span>
            <span
              class="text-sm"
              id="content_arrow"
              v-bind:class="{
                'rotate-180': !contentDropdown,
                'rotate-0': contentDropdown,
              }">
              <i class="bi bi-chevron-down"></i>
            </span>
          </div>
        </div>
        <div v-bind:class="{ hidden: contentDropdown }" id="content_submenu">
          <ul class="grid w-full gap-4 md:grid-cols-3" id="content">
            <li
              v-for="content in contentList"
              :key="content.contentId"
              class="justify-stretch flex">
              <input
                type="checkbox"
                :id="'content' + content.contentId"
                :value="content.contentId"
                class="hidden peer"
                name="content"
                v-model="contentItems" />
              <label
                :for="'content' + content.contentId"
                class="w-full items-center p-3 text-gray-500 bg-white border-2 border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-700 peer-checked:border-blue-600 hover:text-gray-600 dark:peer-checked:text-gray-300 peer-checked:text-gray-600 hover:bg-gray-50 dark:text-gray-400 dark:bg-gray-800 dark:hover:bg-gray-700">
                <div class="block">
                  <div class="w-full text-sm">{{ content.name }}</div>
                </div>
              </label>
            </li>
          </ul>
        </div>
        <!-- 시도 드롭다운 -->
        <div
          class="p-2.5 mt-3 mb-3 flex items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-600 text-white"
          @click="dropdown_sido">
          <i class="bi bi-tag-fill"></i>
          <div class="flex justify-between w-full items-center">
            <span class="text-[15px] ml-4 text-gray-200 font-bold">시도</span>
            <span
              class="text-sm"
              id="sido_arrow"
              v-bind:class="{
                'rotate-180': !sidoDropdown,
                'rotate-0': sidoDropdown,
              }">
              <i class="bi bi-chevron-down"></i>
            </span>
          </div>
        </div>
        <div v-bind:class="{ hidden: sidoDropdown }" id="sido_submenu">
          <ul class="grid w-full gap-4 md:grid-cols-3" id="city">
            <li
              v-for="sido in sidoList"
              :key="sido.sidoCode"
              class="justify-stretch flex"
              @click="gugun(sido.sidoCode)">
              <input
                type="radio"
                :id="'sido' + sido.sidoCode"
                :value="sido.sidoCode"
                class="hidden peer"
                name="sido"
                v-model="sidoItem" />
              <label
                :for="'sido' + sido.sidoCode"
                class="w-full items-center p-3 text-gray-500 bg-white border-2 border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-700 peer-checked:border-blue-600 hover:text-gray-600 dark:peer-checked:text-gray-300 peer-checked:text-gray-600 hover:bg-gray-50 dark:text-gray-400 dark:bg-gray-800 dark:hover:bg-gray-700">
                <div class="block">
                  <div class="w-full text-sm">{{ sido.sidoName }}</div>
                </div>
              </label>
            </li>
          </ul>
        </div>
        <!-- 구군 드롭다운 -->
        <div
          class="p-2.5 mt-3 mb-3 flex items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-600 text-white"
          @click="dropdown_gugun">
          <i class="bi bi-tag-fill"></i>
          <div class="flex justify-between w-full items-center">
            <span class="text-[15px] ml-4 text-gray-200 font-bold">구/군</span>
            <span
              class="text-sm"
              id="gugun_arrow"
              v-bind:class="{
                'rotate-180': !gugunDropdown,
                'rotate-0': gugunDropdown,
              }">
              <i class="bi bi-chevron-down"></i>
            </span>
          </div>
        </div>
        <div v-bind:class="{ hidden: gugunDropdown }" id="gugun_submenu">
          <ul class="grid w-full gap-4 md:grid-cols-3" id="county">
            <li
              v-for="gugun in gugunList"
              :key="gugun.gugunCode"
              class="justify-stretch flex">
              <input
                type="radio"
                :id="'gugun' + gugun.gugunCode"
                :value="gugun.gugunCode"
                class="hidden peer"
                name="gugun"
                v-model="gugunItem" />
              <label
                :for="'gugun' + gugun.gugunCode"
                class="w-full items-center p-3 text-gray-500 bg-white border-2 border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-700 peer-checked:border-blue-600 hover:text-gray-600 dark:peer-checked:text-gray-300 peer-checked:text-gray-600 hover:bg-gray-50 dark:text-gray-400 dark:bg-gray-800 dark:hover:bg-gray-700">
                <div class="block">
                  <div class="w-full text-sm">{{ gugun.gugunName }}</div>
                </div>
              </label>
            </li>
          </ul>
        </div>
        <div
          class="w-full mt-16 py-2 text-center rounded-lg bg-blue-400 hover:bg-blue-500 text-white shadow-md cursor-pointer"
          @click="search">
          검색
        </div>
      </div>
    </aside>
    <div
      class="fixed flex flex-wrap content-center h-full"
      style="z-index: 10"
      v-bind:class="{ hidden: isOpen }">
      <span
        class="text-gray-900 text-6xl left-4 cursor-pointer rotate-[90deg]"
        @click="controllNav"
        id="sidebar_btn">
        <i class="bi bi-chevron-up px-2"></i>
      </span>
    </div>
  </div>
</template>
<script>
import axios from "@/axios";
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      contentList: [],
      sidoList: [],
      gugunList: [],
      searchKeyword: null,
      contentItems: [],
      sidoItem: null,
      gugunItem: null,
      contentDropdown: false,
      sidoDropdown: false,
      gugunDropdown: false,
      openButton: true,
      searchType: true,
    };
  },
  computed: {
    isSearch() {
      if (
        this.$route.path === "/card_search" ||
        this.$route.path === "/map_search"
      ) {
        return true;
      } else {
        return false;
      }
    },
    ...mapGetters("auth", ["isAuthenticated"]),
    ...mapState("kakaoMap", ["nowPosition"]),
    ...mapState("search", ["isOpen"]),
  },
  created() {
    const content_url = "/search/content";
    axios.get(content_url).then((res) => {
      this.contentList = res.data;
    });
    const sido_url = "/search/sido";
    axios.get(sido_url).then((res) => {
      this.sidoList = res.data;
    });
    if (this.$route.path === "/card_search") {
      this.searchType = true;
    } else if (this.$route.path === "/map_search") {
      this.searchType = false;
    }
    this.controllNav();
  },
  methods: {
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
    myPage() {
      if (this.$route.path !== "/mypage") this.$router.push("/mypage");
    },
    login() {
      if (this.$route.path !== "/login") this.$router.push("/login");
    },
    logout() {
      this.$store.dispatch("auth/logout");
      if (this.$route.path !== "/") this.$router.push("/");
      alert("로그아웃");
    },
    home() {
      if (this.$route.path !== "/") {
        this.$router.push("/");
      }
    },
    gugun(sidoCode) {
      const gugun_url = `/search/gugun/${sidoCode}`;
      axios.get(gugun_url).then((res) => {
        this.gugunList = res.data;
      });
    },
    search() {
      const container = this.$refs.scrollContainer;
      container.scrollTo({
        top: 0,
        behavior: "smooth",
      });
      this.$store.dispatch("search/search", {
        searchKeyword: this.searchKeyword,
        contentItems: this.contentItems,
        sidoItem: this.sidoItem,
        gugunItem: this.gugunItem,
      });
      if (this.$route.path === "/map_search") {
        this.searchType = false;
      } else if (this.$route.path === "/card_search") {
        this.searchType = true;
      } else {
        this.$router.push("/map_search");
        this.searchType = false;
      }
    },
    controllNav() {
      this.$store.commit("search/setIsOpen", !this.isOpen);
    },
    dropdown_content() {
      this.contentDropdown = !this.contentDropdown;
    },
    dropdown_sido() {
      this.sidoDropdown = !this.sidoDropdown;
    },
    dropdown_gugun() {
      this.gugunDropdown = !this.gugunDropdown;
    },
    onOpenButton() {
      this.openButton = !this.openButton;
    },
    kakaoMapView() {
      if (this.$route.path !== "/map_search") {
        this.$router.push("/map_search");
        this.searchType = false;
      }
    },
    cardListView() {
      if (this.$route.path !== "/card_search") {
        this.$router.push("/card_search");
        this.searchType = true;
      }
    },
    async nowLocationSearch() {
      const container = this.$refs.scrollContainer;
      container.scrollTo({
        top: 0,
        behavior: "smooth",
      });
      //현재 위치로 검색
      this.$store.dispatch("search/geolocationSearch", {
        myLocate: this.nowPosition,
        contentItems: this.contentItems,
      });
      if (this.$route.path === "/map_search") {
        this.searchType = false;
      } else if (this.$route.path === "/card_search") {
        this.searchType = true;
      } else {
        this.$router.push("/map_search");
        this.searchType = false;
      }
    },
  },
  mounted() {},
};
</script>
<style lang="scss">
.sidebar::-webkit-scrollbar {
  display: none;
}
.sidebar {
  position: fixed;
  // width: 400px;
  transition: 0.5s;
}
.is-open {
  transform: translateX(-25rem);
}
</style>
