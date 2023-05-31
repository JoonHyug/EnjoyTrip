<template>
  <!-- Navbar -->
  <div class="w-[90%] relative container mx-auto p-6">
    <!-- Flex container -->
    <div class="flex items-center justify-end">
      <!-- Logo -->
      <div class="">
        <img
          src="/img/sample/logo.png"
          class="cursor-pointer"
          alt="logo"
          @click="goPath()" />
      </div>
      <!-- Menu Items -->
      <div
        class="flex justify-between text-center space-x-8 ml-auto items-center text-xl font-bold">
        <span
          class="hover:text-gray-500 cursor-pointer"
          @click="goPath('board/notice')"
          >공지사항</span
        >
        <span
          class="hover:text-gray-500 cursor-pointer"
          @click="goPath('board/board')"
          >커뮤니티</span
        >
        <span
          class="hover:text-gray-500 cursor-pointer"
          @click="goPath('travelboard')"
          >여행 계획</span
        >
      </div>
      <!-- Login Button -->
      <div class="min-w-[183px] text-center ml-8" v-if="!userInfo">
        <span
          class="hidden md:block p-3 px-6 pt-2 text-white bg-blue-600 rounded-full baseline hover:bg-blue-500 cursor-pointer"
          @click="goPath('login')"
          >로그인</span
        >
      </div>
      <div class="items-center text-center ml-8 flex" v-if="userInfo">
        <span
          class="w-[3rem] h-[3rem] rounded-full bg-cover cursor-pointer shadow-md border border-slate-400"
          :style="{
            backgroundImage: profileImage
              ? `url(/img/profile/${profileImage})`
              : 'url(/img/no-image.jpg)',
          }"
          @click="goPath('mypagehome')">
        </span>
        <span
          class="font-bold cursor-pointer mx-4"
          @click="goPath('mypagehome')"
          >{{ userInfo.name }}님</span
        >
        <span
          class="p-3 px-6 pt-2 text-white bg-blue-600 rounded-full baseline hover:bg-blue-500 cursor-pointer"
          @click="logout">
          로그아웃
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  methods: {
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
    logout() {
      this.$store.dispatch("auth/logout");
    },
  },
  computed: {
    ...mapState("auth", { userInfo: "info" }),
    ...mapState("file", ["profileImage"]),
  },
  mounted() {
    if (this.userInfo) {
      this.$store.dispatch("file/getProfileImage", {
        accountId: this.userInfo.id,
        type: "profile",
      });
    }
  },
  watch: {},
  created() {
    if (this.userInfo) {
      this.$store.dispatch("file/getProfileImage", {
        accountId: this.userInfo.id,
        type: "profile",
      });
    }
  },
};
</script>

<style lang="scss" scoped></style>
