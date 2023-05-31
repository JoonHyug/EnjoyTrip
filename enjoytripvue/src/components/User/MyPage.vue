<template>
  <div class="w-[90%] mx-auto">
    <Form />
    <h1>마이페이지</h1>
    <div v-for="(review, index) in getUserReviews" :key="index + 'review'">
      {{ review }}
    </div>
    <h1>북마크</h1>
    <div v-for="(bookmark, index) in bookmarks" :key="index + 'bookmark'">
      {{ bookmark }}
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import Form from "@/components/views/form.vue";
export default {
  components: {
    Form,
  },
  data() {
    return {};
  },
  created() {
    this.$store.dispatch("review/getReviewsToAccountId", this.userInfo.id);
    this.$store.dispatch("bookmark/getBookmarks", this.userInfo.id);
  },
  mounted() {},
  computed: {
    ...mapGetters("review", ["getUserReviews"]),
    ...mapState("bookmark", { bookmarks: "userBookmarks" }),
    ...mapState("auth", { userInfo: "info" }),
  },
  methods: {},
  watch: {},
};
</script>

<style lang="scss" scoped></style>
