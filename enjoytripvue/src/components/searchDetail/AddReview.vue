<template>
  <section class="pb-12">
    <div class="mx-auto w-full px-4">
      <div class="bg-white overflow-hidden shadow-xl rounded-lg">
        <div class="p-4 bg-white border-b flex flex-row">
          <div class="flex-auto mr-4">
            <div class="mb-4">
              <div id="half-stars-example">
                <div class="rating-group">
                  <label
                    aria-label="0.5 stars"
                    class="rating_label rating_label-half"
                    for="rating2-05">
                    <i class="rating_icon rating_icon-star fa fa-star-half"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-05"
                    value="1"
                    type="radio" />
                  <label
                    aria-label="1 star"
                    class="rating_label"
                    for="rating2-10">
                    <i class="rating_icon rating_icon-star fa fa-star"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-10"
                    value="2"
                    type="radio" />
                  <label
                    aria-label="1.5 stars"
                    class="rating_label rating_label-half"
                    for="rating2-15">
                    <i class="rating_icon rating_icon-star fa fa-star-half"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-15"
                    value="3"
                    type="radio" />
                  <label
                    aria-label="2 stars"
                    class="rating_label"
                    for="rating2-20">
                    <i class="rating_icon rating_icon-star fa fa-star"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-20"
                    value="4"
                    type="radio" />
                  <label
                    aria-label="2.5 stars"
                    class="rating_label rating_label-half"
                    for="rating2-25">
                    <i class="rating_icon rating_icon-star fa fa-star-half"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-25"
                    value="5"
                    type="radio" />
                  <label
                    aria-label="3 stars"
                    class="rating_label"
                    for="rating2-30">
                    <i class="rating_icon rating_icon-star fa fa-star"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-30"
                    value="6"
                    type="radio" />
                  <label
                    aria-label="3.5 stars"
                    class="rating_label rating_label-half"
                    for="rating2-35">
                    <i class="rating_icon rating_icon-star fa fa-star-half"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-35"
                    value="7"
                    type="radio" />
                  <label
                    aria-label="4 stars"
                    class="rating_label"
                    for="rating2-40">
                    <i class="rating_icon rating_icon-star fa fa-star"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-40"
                    value="8"
                    type="radio" />
                  <label
                    aria-label="4.5 stars"
                    class="rating_label rating_label-half"
                    for="rating2-45">
                    <i class="rating_icon rating_icon-star fa fa-star-half"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-45"
                    value="9"
                    type="radio" />
                  <label
                    aria-label="5 stars"
                    class="rating_label"
                    for="rating2-50">
                    <i class="rating_icon rating_icon-star fa fa-star"></i
                  ></label>
                  <input
                    v-model="rating"
                    class="rating_input"
                    name="rating"
                    id="rating2-50"
                    value="10"
                    type="radio" />
                </div>
              </div>
            </div>

            <div class="">
              <textarea
                v-model="text"
                cols="30"
                rows="4"
                class="border-2 border-gray-500 w-full rounded-lg"
                style="resize: none"
                placeholder="내용"></textarea>
            </div>
          </div>

          <!-- 이미지도 넣을 수 있게? -->

          <!-- 버튼 모양 바꾸기 -->
          <div class="flex-grow-0 p-1">
            <input
              type="button"
              value="제출"
              class="p-3 bg-blue-500 text-white hover:bg-blue-400 h-full rounded-lg"
              style="width: 100px"
              @click="sendReview" />
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      rating: 10,
      text: "",
    };
  },
  computed: {
    ...mapGetters("detail", ["getDetailResult"]),
    ...mapState("auth", { userInfo: "info" }),
  },
  methods: {
    async sendReview() {
      await this.$store.dispatch("review/addReview", {
        point: this.rating,
        content: this.text,
        contentId: this.getDetailResult.contentId,
        accountId: this.userInfo.id,
      });
      this.rating = 10;
      this.text = "";
    },
  },
};
</script>

<style lang="scss" scoped></style>
