<template>
  <div class="pt-20">
    <div
      class="mx-auto w-[70%] flex flex-col text-gray-800 border border-gray-300 p-4 shadow-lg rounded-xl bg-white">
      <div class="w-full items-center text-center mb-4 font-bold text-3xl">
        <h1 v-if="type === 1">공지사항 수정</h1>
        <h1 v-if="type === 2">자유게시판 수정</h1>
      </div>
      <input
        class="title bg-gray-100 border border-gray-300 p-2 outline-none rounded-xl"
        spellcheck="false"
        placeholder="제목"
        type="text"
        v-model="title" />
      <div class="icons flex text-gray-500 m-2">
        <div
          class="count ml-auto text-gray-400 text-xs font-semibold"
          :class="titleLength > 50 ? 'text-red-600' : ''">
          {{ titleLength }}/50
        </div>
      </div>
      <textarea
        class="bg-gray-100 sec p-3 h-60 border border-gray-300 outline-none rounded-xl"
        spellcheck="false"
        placeholder="내용"
        style="resize: none"
        v-model="content"></textarea>

      <div class="icons flex text-gray-500 m-2">
        <div
          class="count ml-auto text-gray-400 text-xs font-semibold"
          :class="contentLength > 3000 ? 'text-red-600' : ''">
          {{ contentLength }}/3000
        </div>
      </div>
      <!-- buttons -->
      <div class="buttons flex">
        <div
          class="border border-gray-300 p-1 px-4 font-semibold cursor-pointer text-gray-500 ml-auto rounded-md"
          @click="prevPage">
          취소
        </div>
        <div
          class="border border-gray-500 p-1 px-4 font-semibold cursor-pointer text-gray-200 ml-2 bg-blue-500 rounded-md"
          @click="editNotice">
          수정
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      title: null,
      content: null,
    };
  },
  computed: {
    titleLength() {
      if (this.title !== null) return this.title.length;
      return 0;
    },
    contentLength() {
      if (this.content !== null) return this.content.length;
      return 0;
    },
    ...mapState("board", { data: "noticeDetail" }),
    ...mapState("auth", { userInfo: "info" }),
    ...mapState("board", ["type"]),
  },
  mounted() {},
  methods: {
    async editNotice() {
      if (this.contentLength > 3000) {
        alert("본문의 글자수를 확인해주세요.");
        return;
      }
      if (this.titleLength > 50) {
        alert("제목의 글자수를 확인해주세요.");
        return;
      }
      await this.$store.dispatch("board/edit", {
        articleno: this.$route.params.id,
        subject: this.title,
        content: this.content,
        type: this.type,
      });
      if (this.type === 1) {
        this.$router.push("/board/notice");
      } else if (this.type === 2) {
        this.$router.push("/board/board");
      }
    },
    prevPage() {
      this.$router.go(-1);
    },
  },
  created() {
    this.$store.dispatch("board/detail", this.$route.params.id);
  },
  watch: {
    data(newData) {
      this.title = newData.subject;
      this.content = newData.content;
    },
  },
};
</script>

<style lang="scss" scoped></style>
