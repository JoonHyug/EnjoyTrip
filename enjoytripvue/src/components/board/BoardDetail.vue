<template>
  <div class="pt-20">
    <Modal
      v-if="showModal"
      :message="confirmMessage"
      :type="type"
      @confirm="handleConfirm"
      @cancel="cancelModal" />
    <section
      class="mx-auto w-6/12 px-8 flex flex-col bg-white rounded-xl"
      v-if="data">
      <div class="border-b-2 mb-2">
        <div class="flex justify-end mt-8">
          <div
            class="border rounded-lg py-2 px-4 cursor-pointer transition duration-200 text-gray-200 bg-blue-500 hover:bg-blue-100 hover:text-black"
            @click="prevPage">
            뒤로가기
          </div>
          <ul class="flex space-x-4 ml-auto">
            <li
              class="border rounded-lg py-2 px-4 cursor-pointer transition duration-200 text-gray-200 bg-blue-500 hover:bg-blue-100 hover:text-black"
              @click="goPath(`board/edit/${contentId}`)"
              v-if="userInfo.id == data.accountId">
              수정
            </li>
            <li
              class="border rounded-lg py-2 px-4 cursor-pointer transition duration-200 text-gray-200 bg-blue-500 hover:bg-blue-100 hover:text-black"
              @click="showConfirm">
              삭제
            </li>
          </ul>
        </div>
        <div class="flex justify-center items-center">
          <h1 class="font-bold text-2xl">{{ data.subject }}</h1>
        </div>
        <div class="flex items-end my-4 justify-between">
          <h3>조회수 : {{ data.hit }}</h3>
          <h3 class="justify-end">{{ data.regtime }}</h3>
        </div>
      </div>
      <section class="mb-8">
        <article class="mt-8 text-gray-500 leading-7 tracking-wider">
          <span class="break-all">{{ data.content }}</span>
        </article>
      </section>
    </section>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Modal from "@/components/modal/Confirm.vue";
export default {
  components: {
    Modal,
  },
  data() {
    return {
      showModal: false,
      confirmMessage: "정말 삭제하시겠습니까?",
      contentId: this.$route.params.id,
      type: "delete",
    };
  },
  computed: {
    ...mapState("board", { data: "noticeDetail" }),
    ...mapState("auth", { userInfo: "info" }),
  },
  created() {
    this.$store.dispatch("board/detail", this.$route.params.id);
  },
  methods: {
    prevPage() {
      this.$router.go(-1);
    },
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
    showConfirm() {
      this.showModal = true;
    },
    handleConfirm() {
      this.showModal = false;
      this.$store.dispatch("board/delete", this.contentId).then(() => {
        this.$router.push("/board/notice");
      });
    },
    cancelModal() {
      this.showModal = false;
    },
  },
};
</script>

<style lang="scss" scoped></style>
