<template lang="">
  <!-- 상세정보 -->
  <div class="flex mt-10 relative" v-if="getDetailResult != null">
    <div class="h-full mx-auto pl-16 w-full" style="margin-right: 25rem">
      <section class="relative block h-[500px]">
        <div
          class="absolute top-0 w-full h-full bg-center bg-cover rounded-lg"
          :style="{
            backgroundImage: getDetailResult.firstImage
              ? `url(${getDetailResult.firstImage})`
              : `url(/img/no-image.jpg)`,
          }">
          <span
            id="blackOverlay"
            class="w-full h-full absolute opacity-50 bg-black rounded-lg"></span>
        </div>
        <div
          class="top-auto bottom-0 left-0 right-0 w-full absolute overflow-hidden h-70-px"
          style="transform: translateZ(0px)"></div>
      </section>
      <section class="relative pt-16 bg-blueGray-200">
        <div class="mx-auto px-4">
          <div
            class="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-xl rounded-lg -mt-64">
            <div class="px-6">
              <div class="flex flex-wrap justify-center">
                <div
                  class="w-full lg:w-3/12 px-4 lg:order-2 flex justify-center">
                  <div class="relative">
                    <img
                      alt="..."
                      :src="
                        getDetailResult.firstImage
                          ? getDetailResult.firstImage
                          : '/img/no-image.jpg'
                      "
                      class="shadow-xl rounded-full h-auto align-middle border-none absolute -m-16 -ml-20 lg:-ml-16 max-w-[150px]"
                      style="width: 150px; height: 150px" />
                  </div>
                </div>
                <div
                  class="w-full lg:w-4/12 px-4 lg:order-3 lg:text-right lg:self-center">
                  <div class="py-6 px-3 mt-32 sm:mt-0" v-if="userInfo">
                    <button
                      class="bg-blue-500 uppercase text-white font-bold hover:shadow-md shadow text-md px-4 py-2 rounded outline-none focus:outline-none mb-1 ease-linear transition-all duration-150"
                      type="button"
                      @click="addBookmark"
                      :class="registed ? 'bg-gray-500' : 'bg-blue-500'">
                      북마크
                    </button>
                  </div>
                </div>
                <div class="w-full lg:w-4/12 px-4 lg:order-1">
                  <div class="flex justify-center py-4 lg:pt-4 pt-8">
                    <div class="mr-4 p-3 text-center">
                      <span
                        class="text-xl font-bold block uppercase tracking-wide text-blueGray-600"
                        >{{ getDetailResult.readcount }}</span
                      ><span class="text-sm text-blueGray-400">조회수</span>
                    </div>
                    <div class="mr-4 p-3 text-center">
                      <!-- 사용자들이 올린 사진 수 -->
                      <span
                        class="text-xl font-bold block uppercase tracking-wide text-blueGray-600"
                        >{{ (avgPoint / 2).toFixed(2) }}</span
                      ><span class="text-sm text-blueGray-400">평점</span>
                    </div>
                    <div class="lg:mr-4 p-3 text-center">
                      <!-- 사용자들이 남긴 후기 수 -->
                      <span
                        class="text-xl font-bold block uppercase tracking-wide text-blueGray-600"
                        >{{ contentTotalItem }}</span
                      ><span class="text-sm text-blueGray-400">후기</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-center mt-12">
                <h3
                  class="text-4xl font-semibold leading-normal text-blueGray-700 mb-2">
                  {{ getDetailResult.title }}
                </h3>
                <div
                  class="text-sm leading-normal mt-0 mb-2 text-blueGray-400 font-bold uppercase">
                  <i
                    class="bi bi-geo-alt-fill mr-2 text-lg text-blueGray-400"></i>
                  {{ getDetailResult.addr1 }}
                </div>
              </div>
              <div class="mt-10 py-10 border-t border-blueGray-200 text-center">
                <div class="flex flex-wrap justify-center">
                  <div class="w-full lg:w-9/12 px-4">
                    <p
                      class="content line-clamp-5 mb-4 text-lg leading-relaxed text-blueGray-700 break-keep text-center">
                      {{ getDetailResult.overview }}
                    </p>
                    <span
                      class="more font-normal text-pink-500 cursor-pointer"
                      @click="openContentDetail"
                      >더보기</span
                    >
                    <span
                      class="close font-normal text-pink-500 cursor-pointer hidden"
                      @click="openContentDetail"
                      >닫기</span
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- 후기 작성 -->
      <AddReview v-if="userInfo" />
    </div>
    <Review />
  </div>
</template>
<script>
import Review from "@/components/searchDetail/Review.vue";
import AddReview from "@/components/searchDetail/AddReview.vue";
import { mapGetters, mapState } from "vuex";
export default {
  name: "Detail",
  components: {
    Review,
    AddReview,
  },
  data() {
    return {
      showmore: true,
      contentId: this.$route.params.id,
    };
  },
  computed: {
    ...mapGetters("detail", ["getDetailResult"]),
    ...mapState("auth", { userInfo: "info" }),
    ...mapState("bookmark", { registed: "alreadyRegister" }),
    ...mapState("review", ["contentTotalItem", "reviewedId", "avgPoint"]),
  },
  created() {
    this.$store.dispatch("detail/detail", this.contentId);
    this.$store.dispatch("bookmark/checkAlreadyBookmark", {
      accountId: this.userInfo.id,
      contentId: this.contentId,
    });
    this.getExInfo();
  },
  mounted() {
    if (this.$store.getters["detail/getDetailResult"] !== null) {
      const overview = this.$el.querySelector(".content");
      const showmoreButton = this.$el.querySelector(".more");
      if (overview.offsetHeight < overview.scrollHeight) {
        this.showmore = !this.showmore;
      } else {
        showmoreButton.classList.add("hidden");
      }
      this.getExInfo();
    }
  },
  methods: {
    getExInfo() {
      this.$store.dispatch("review/getTotalItemsByContentId", this.contentId);
      this.$store
        .dispatch("review/getReviewedIdByContentId", this.contentId)
        .then(() => {
          this.$store.dispatch("file/getAllAccountPicture", this.reviewedId);
        });
      this.$store.dispatch("review/getAvgPointByContentId", this.contentId);
    },
    openContentDetail() {
      const overview = this.$el.querySelector(".content");
      const showmoreButton = this.$el.querySelector(".more");
      const closeButton = this.$el.querySelector(".close");
      overview.classList.toggle("line-clamp-5");
      showmoreButton.classList.toggle("hidden");
      closeButton.classList.toggle("hidden");
    },
    addBookmark() {
      this.$store
        .dispatch("bookmark/addBookmark", {
          accountId: this.userInfo.id,
          contentId: this.contentId,
        })
        .then((res) => {
          if (res == null) alert("북마크 등록 완료");
          else alert("북마크 해제 완료.");
        });
    },
  },
};
</script>
<style scoped>
@import url("@/assets/css/review_point.css");
</style>
