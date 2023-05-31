<template>
  <div class="w-full mx-auto">
    <!-- First Section -->
    <section id="first">
      <!-- Flex Container -->
      <div
        class="container w-3/4 flex flex-col-reverse md:flex-row items-center px-6 mx-auto mt-10 space-y-0 md:space-y-0">
        <!-- Left item -->
        <div class="flex flex-col mb-32 space-y-12 md:w-1/2 px-2">
          <h1
            class="max-w-md text-4xl font-bold text-center md:text-5xl md:text-left">
            Leave Your FootPrint with EnjoyTrip.
          </h1>
          <p
            class="max-w-sm text-center text-gray-500 md:text-left text-xl break-all">
            EnjoyTrip과 함께하는 여행을 더 즐겁게 만들어보세요. 최고의 여행을
            위해 최고의 여행 서비스를 제공하고 있습니다.
          </p>
          <div class="flex justify-center md:justify-start">
            <a
              class="p-3 px-6 pt-2 text-white bg-blue-600 rounded-full baseline hover:bg-blue-500 cursor-pointer"
              @click="controllNav"
              >시작하기</a
            >
          </div>
        </div>
        <!-- image -->
        <div class="flex justify-center md:w-1/2 pb-6">
          <img
            class="border-solid border-slate-100 border-8 rounded-2xl shadow-lg opacity-90 hover:opacity-100"
            src="/img/sample/header-1.jpg"
            alt="" />
        </div>
      </div>
    </section>

    <!-- Second Section -->
    <section id="second">
      <!-- Flex container -->
      <div
        class="container w-3/4 flex flex-col px-4 mx-auto mt-10 space-y-12 md:space-y-0 md:flex-row">
        <!-- Destination sample -->
        <div class="flex flex-col space-y-12 md:w-1/2">
          <h2 class="max-w-md text-4xl font-bold text-center md:text-left">
            EnjoyTrip 100% 이용하기!
          </h2>
          <p class="max-w-md text-center text-gray-500 md:text-left text-2xl">
            EnjoyTrip을 이용하면 여행 계획을 쉽게 작성하고 공유할 수 있습니다!
          </p>
        </div>
        <!-- Nubered List -->
        <div class="flex flex-col space-y-8 md:w-1/2">
          <!-- List item 1 -->
          <div
            class="flex flex-col space-y-3 md:space-y-0 md:space-x-6 md:flex-row">
            <!-- Heading -->
            <div class="rounded-l-full bg-blue-300 md:bg-transparent">
              <div class="flex items-center space-x-2">
                <div
                  class="px-4 py-2 text-white rounded-full md:py-1 bg-blue-600">
                  01
                </div>
                <h3 class="text-base font-bold md:mb-4 md:hidden">
                  여행지를 검색해보세요!
                </h3>
              </div>
            </div>

            <div>
              <h3 class="hidden mb-4 text-lg font-bold md:block">
                여행지를 검색해보세요!
              </h3>
              <p class="text-gray-500 max-w-lg">
                시작하기를 눌러 여행하고 싶은 장소를 선택하고 검색을 해보세요!
              </p>
            </div>
          </div>

          <!-- List item 2 -->
          <div
            class="flex flex-col space-y-3 md:space-y-0 md:space-x-6 md:flex-row">
            <!-- Heading -->
            <div class="rounded-l-full bg-blue-300 md:bg-transparent">
              <div class="flex items-center space-x-2">
                <div
                  class="px-4 py-2 text-white rounded-full md:py-1 bg-blue-600">
                  02
                </div>
                <h3 class="text-base font-bold md:mb-4 md:hidden">
                  여행 계획을 공유해보세요!
                </h3>
              </div>
            </div>

            <div>
              <h3 class="hidden mb-4 text-lg font-bold md:block">
                여행 계획을 공유해보세요!
              </h3>
              <p class="text-gray-500 max-w-lg">
                여행하고 싶은은 장소를 북마크에 등록하고 여행 계획을 작성해서
                사람들과 공유해보세요!
              </p>
            </div>
          </div>

          <!-- List item 3 -->
          <div
            class="flex flex-col space-y-3 md:space-y-0 md:space-x-6 md:flex-row">
            <!-- Heading -->
            <div class="rounded-l-full bg-blue-300 md:bg-transparent">
              <div class="flex items-center space-x-2">
                <div
                  class="px-4 py-2 text-white rounded-full md:py-1 bg-blue-600">
                  03
                </div>
                <h3 class="text-base font-bold md:mb-4 md:hidden">
                  커뮤니티를 통해서 사람들과 정보를 공유해보세요!
                </h3>
              </div>
            </div>

            <div>
              <h3 class="hidden mb-4 text-lg font-bold md:block">
                커뮤니티를 통해서 사람들과 정보를 공유해보세요!
              </h3>
              <p class="text-gray-500 max-w-lg">
                커뮤니티를 통해서 다양한 사람들과 여행 관련 정보들을 공유하고
                다양한 이야기를 나눠보세요!
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Destination -->
    <section id="destinations">
      <!-- Container to Heading -->
      <div class="w-3/4 max-x-6xl px-5 mx-auto my-16 text-center">
        <!-- Heading -->
        <h2 class="text-4xl font-bold text-center">인기 여행지</h2>
        <!-- Destination Container -->
        <div class="flex flex-col mt-16 md:flex-row md:space-x-6 min-h-[40rem]">
          <!-- Destination 1. -->
          <div
            class="flex flex-col items-center p-6 space-y-6 md:w-1/3"
            v-for="(item, idx) of top3Content"
            :key="`item` + item.title + idx">
            <div
              class="transition ease-in-out delay-100 hover:scale-110 duration-100 h-full w-full relative block overflow-hidden rounded-xl bg-cover bg-center bg-no-repeat cursor-pointer"
              :style="{
                backgroundImage: item.firstImage
                  ? `url(${item.firstImage})`
                  : 'url(img/no-image.jpg)',
              }"
              @click="goPath(`detail/${item.contentId}`)">
              <div class="absolute inset-0 bg-black/25"></div>
              <div
                class="h-full relative flex items-end justify-between p-4 sm:p-6 lg:p-8">
                <div
                  class="sm:pt-18 pt-12 text-white lg:pt-24 flex flex-col justify-center w-full">
                  <h3 class="text-xl font-bold sm:text-2xl">
                    {{ item.title }}
                  </h3>
                  <p class="text-sm">{{ item.addr1 }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- button -->
        <div class="my-16">
          <a
            class="p-3 px-6 pt-2 text-white bg-blue-600 rounded-full baseline hover:bg-blue-500 cursor-pointer"
            @click="controllNav"
            >시작하기</a
          >
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import axios from "@/axios";
import { mapState } from "vuex";
export default {
  data() {
    return {
      top3Content: null,
    };
  },
  components: {},
  computed: {
    ...mapState("search", ["isOpen"]),
  },
  methods: {
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
    controllNav() {
      this.$store.commit("search/setIsOpen", !this.isOpen);
    },
  },
  created() {
    axios.get("/attraction/hot").then((res) => {
      this.top3Content = res.data;
    });
  },
};
</script>

<style scoped></style>
