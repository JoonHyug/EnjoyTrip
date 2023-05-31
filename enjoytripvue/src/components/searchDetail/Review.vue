<template>
  <div
    class="review absolute right-0 w-[25rem] overflow-y-scroll h-full overflow-hidden">
    <!-- 클릭 시 숨겨진 내용 다 보이게 -->
    <div
      class="flex items-center justify-center ml-16 mr-10 mb-10 cursor-pointer"
      v-if="reviews == null">
      <div
        class="w-full bg-white max-w-xl rounded-2xl px-10 py-8 shadow-lg hover:shadow-2xl transition duration-500">
        <div class="my-4 text-center">
          <h1 v-if="noData" class="text-lg text-gray-700 font-semibold">
            리뷰를 등록해보세요
          </h1>
          <h1
            v-else
            class="text-lg text-gray-700 font-semibold"
            @click="sendPlaceRegistration">
            등록 요청 하기
          </h1>
        </div>
      </div>
    </div>
    <div
      class="flex items-center justify-center ml-16 mr-10 mb-10 cursor-pointer"
      v-for="(review, index) in reviews"
      :key="index"
      @click="openReviewDetail(index)">
      <div
        class="bg-white max-w-xl rounded-2xl px-7 py-8 shadow-lg hover:shadow-2xl transition duration-500 w-full">
        <div class="flex items-center px-3">
          <span
            class="rounded-full shadow-xl w-[3rem] h-[3rem] bg-cover"
            :style="{
              backgroundImage: profileImages
                ? `url(/img/profile/${checkAccountId(review.accountId)})`
                : 'url(/img/no-image.jpg)',
            }">
          </span>
          <h1 class="text-lg text-gray-700 font-semibold items-center ml-6">
            {{ review.accountId }}
          </h1>
        </div>

        <div class="flex mt-4 px-3">
          <i
            class="fa fa-star text-yellow-400"
            v-for="index in Math.floor(review.point / 2)"
            :key="index"></i>
          <i
            class="fa fa-star-half text-yellow-400"
            v-if="review.point % 2 != 0"></i>
        </div>
        <div class="mt-4 flex px-3">
          <!-- 오버 플로우 시 가려지게 -->
          <span
            class="text-md text-gray-600 break-all"
            :class="{ 'line-clamp-3': isLineClamp[index] }">
            {{ review.content }}
          </span>
        </div>
        <div class="mt-4 flex items-center space-x-4 py-4 justify-end">
          <div class="text-sm font-semibold">
            <span class="font-normal">{{ review.createTime }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      reviews: null,
      isLineClamp: [],
    };
  },
  mounted() {},
  methods: {
    openReviewDetail(index) {
      // console.log(this.isLineClamp);
      this.$set(this.isLineClamp, index, !this.isLineClamp[index]);
    },
    sendPlaceRegistration() {
      if (this.getInfo == null) {
        alert("로그인 해주세요");
        this.$router.push("/login");
      }
      this.$store.dispatch("detail/registrationRequest", {
        detailResult: this.getDetailResult,
        user: this.getInfo,
      });
    },
    checkAccountId(accountId) {
      for (let i of this.profileImages) {
        if (i.accountId === accountId) {
          return i.picName;
        }
      }
    },
  },
  computed: {
    ...mapGetters("detail", ["getDetailResult"]),
    ...mapGetters("auth", ["getInfo"]),
    ...mapGetters("review", ["getReviewResult"]),
    ...mapState("review", ["noData"]),
    ...mapState("file", ["profileImages"]),
  },
  created() {
    this.$store.dispatch("review/getReview", this.$route.params.id);
  },
  watch: {
    getReviewResult(result) {
      this.reviews = result;
      this.isLineClamp = new Array(this.reviews.length).fill(true);

      // console.log(this.reviews);
    },
  },
};
</script>

<style lang="scss" scoped></style>
