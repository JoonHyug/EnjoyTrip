<template>
  <!-- Footer -->
  <div class="bg-blue-950">
    <!-- Flex Container -->
    <div
      class="container flex flex-col-reverse justify-between px-6 py-10 mx-auto space-y-8 md:flex-row md:space-y-0">
      <!-- Logo and social links container -->
      <div
        class="flex flex-col-reverse items-center justify-between space-y-12 md:flex-col md:space-y-0 md:items-start">
        <!-- Logo -->
        <div>
          <img
            src="/img/sample/logo-light.png"
            class="h-8 cursor-pointer"
            alt="logo"
            @click="goPath()" />
        </div>
      </div>
      <!-- List Container -->
      <div class="flex justify-around space-x-32">
        <div class="flex flex-col space-y-3 text-white">
          <span class="">Home</span>
          <span
            class="hover:text-blue-300 cursor-pointer"
            @click="goPath('board/notice')"
            >공지사항</span
          >
          <span
            class="hover:text-blue-300 cursor-pointer"
            @click="goPath('board/board')"
            >커뮤니티</span
          >
          <span
            class="hover:text-blue-300 cursor-pointer"
            @click="goPath('travelboard')"
            >여행계획</span
          >
        </div>
        <div class="flex flex-col space-y-3 text-white">
          <span class="">정보</span>
          <span
            class="hover:text-blue-300 cursor-pointer"
            @click="goPath('mypagebookmark')"
            >북마크</span
          >
          <span
            class="hover:text-blue-300 cursor-pointer"
            @click="goPath('mypagereview')"
            >리뷰</span
          >
        </div>
      </div>

      <!-- input Container -->
      <div class="flex flex-col justify-end">
        <div class="hidden text-white md:block">
          Copyright &copy; 2023, All Rights Reserved
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
  },
};
</script>

<style lang="scss" scoped></style>
