<template>
  <div class="w-full h-full">
    <div
      class="flex content-center items-center justify-center h-full w-full text-sm select-none">
      <div
        class="border border-slate-200 border-2 my-28 w-full sm:w-[479px] shadow-lg rounded-xl bg-slate-200 h-[70%] px-5 py-14 sm:p-14 grid place-items-center text-slate-800">
        <img src="/img/sample/logo.png" alt="LOGO" />
        <h1 class="mb-5 text-lg font-bold text-slate-800 uppercase mt-7">
          계정을 사용하여 로그인 하세요
        </h1>
        <div class="w-full mb-8">
          <div class="space-y-6">
            <!-- input ID -->
            <div class="relative w-full">
              <input
                type="text"
                id="id"
                autocomplete="off"
                class="appearance-none relative z-10 px-5 pt-8 pb-2 rounded-md transition duration-200 bg-transparent w-full focus:outline-none border border-slate-500 focus:border-slate-400 peer"
                v-model="id" />
              <label
                for="id"
                class="absolute left-5 top-1/3 origin-left text-[13px] text-slate-800 transition duration-200 peer-focus:-translate-y-3 peer-focus:scale-90"
                :class="{
                  '-translate-y-3 scale-90': id != '',
                }"
                >ID</label
              >
            </div>

            <!-- input password -->
            <div class="relative w-full">
              <input
                id="password"
                autocomplete="off"
                class="appearance-none relative z-10 px-5 pt-8 pb-2 rounded-md transition duration-200 bg-transparent w-full focus:outline-none border border-slate-500 focus:border-slate-400 peer"
                v-model="password"
                :type="passwordType" />
              <label
                for="password"
                class="absolute left-5 top-1/3 origin-left text-[13px] text-slate-800 transition duration-200 peer-focus:-translate-y-3 peer-focus:scale-90"
                :class="{
                  '-translate-y-3 scale-90': password != '',
                }"
                >Password</label
              >
              <button
                for=""
                class="absolute right-0 top-1/2 z-20 transition duration-200 -translate-y-1/2 rounded p-2 hover:bg-white/20 active:bg-white/30"
                @click="buttonPress">
                <img alt="eye-image" :src="eyeImage" />
              </button>
            </div>
          </div>

          <div class="flex items-center justify-center text-[13px] mt-6 mb-10">
            <span
              class="hover:underline cursor-pointer"
              @click="goPath('forgot_password')"
              >비밀번호 찾기</span
            >
          </div>
          <button
            type="submit"
            class="w-full h-14 bg-blue-500 text-[15px] font-bold text-slate-50 rounded uppercase font-bold hover:brightness-110"
            @click="login">
            로그인
          </button>
        </div>

        <div class="grid place-items-center text-[13px] gap-2">
          <div class="font-medium">
            <span class="mt-1"> 계정이 없으십니까? </span>
            <span
              class="text-black hover:underline cursor-pointer"
              @click="goPath('register')"
              >회원가입</span
            >
          </div>
          <div class="font-medium">
            <span class="mr-1">메인 페이지로 돌아가기</span>
            <span
              class="text-black hover:underline cursor-pointer"
              @click="goPath()"
              >메인 페이지</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      password: "",
      passwordType: "password",
      eyeImage: require("/public/img/eye-off-icon.svg"),
    };
  },
  methods: {
    buttonPress() {
      if (this.passwordType === "password") {
        this.eyeImage = require("/public/img/eye-icon.svg");
        this.passwordType = "text";
      } else {
        this.eyeImage = require("/public/img/eye-off-icon.svg");
        this.passwordType = "password";
      }
    },
    async login() {
      await this.$store.dispatch("auth/login", {
        id: this.id,
        password: this.password,
      });
      if (this.$store.getters["auth/isAuthenticated"]) {
        this.$router.push("/");
        alert("로그인");
      }
    },
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
