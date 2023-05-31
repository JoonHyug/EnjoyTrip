<template>
  <div class="bg-slate-80 w-full h-full">
    <div
      class="flex content-center items-center justify-center h-full w-full text-sm select-none">
      <div
        class="border-slate-200 border-2 my-28 w-full sm:w-[479px] shadow-lg rounded-xl bg-slate-200 h-[70%] px-5 py-14 sm:p-14 grid place-items-center text-slate-800">
        <img src="/img/sample/logo.png" alt="LOGO" />
        <h1 class="mb-5 text-lg font-bold text-slate-800 uppercase mt-7">
          회원가입
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
                v-model="user.id" />
              <label
                for="id"
                class="absolute left-5 top-1/3 origin-left text-[13px] text-slate-800 transition duration-200 peer-focus:-translate-y-3 peer-focus:scale-90"
                :class="{
                  '-translate-y-3 scale-90': user.id != '',
                }"
                >ID</label
              >
            </div>

            <!-- input Name -->
            <div class="relative w-full">
              <input
                type="text"
                id="name"
                autocomplete="off"
                class="appearance-none relative z-10 px-5 pt-8 pb-2 rounded-md transition duration-200 bg-transparent w-full focus:outline-none border border-slate-500 focus:border-slate-400 peer"
                v-model="user.name" />
              <label
                for="name"
                class="absolute left-5 top-1/3 origin-left text-[13px] text-slate-800 transition duration-200 peer-focus:-translate-y-3 peer-focus:scale-90"
                :class="{
                  '-translate-y-3 scale-90': user.name != '',
                }"
                >Name</label
              >
            </div>

            <!-- input EmailAddress -->
            <div class="relative w-full">
              <input
                type="text"
                id="email"
                autocomplete="off"
                class="appearance-none relative z-10 px-5 pt-8 pb-2 rounded-md transition duration-200 bg-transparent w-full focus:outline-none border border-slate-500 focus:border-slate-400 peer"
                v-model="user.email" />
              <label
                for="email"
                class="absolute left-5 top-1/3 origin-left text-[13px] text-slate-800 transition duration-200 peer-focus:-translate-y-3 peer-focus:scale-90"
                :class="{
                  '-translate-y-3 scale-90': user.email != '',
                }"
                >Email Address</label
              >
            </div>

            <!-- input password -->
            <div class="relative w-full">
              <input
                id="password"
                autocomplete="off"
                class="appearance-none relative z-10 px-5 pt-8 pb-2 rounded-md transition duration-200 bg-transparent w-full focus:outline-none border border-slate-500 focus:border-slate-400 peer"
                v-model="user.password"
                :type="passwordType" />
              <label
                for="password"
                class="absolute left-5 top-1/3 origin-left text-[13px] text-slate-800 transition duration-200 peer-focus:-translate-y-3 peer-focus:scale-90"
                :class="{
                  '-translate-y-3 scale-90': user.password != '',
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

          <div class="flex items-center justify-between text-[13px] mt-6 mb-10">
            <button
              type="submit"
              class="w-full h-14 bg-blue-500 text-[15px] text-slate-50 rounded uppercase font-bold hover:brightness-110"
              @click="register">
              회원가입
            </button>
          </div>

          <div class="grid place-items-center text-[13px] gap-2">
            <div class="font-medium">
              <span class="mt-1"> 계정이 이미 있으십니까? </span>
              <span
                class="text-black hover:underline cursor-pointer"
                @click="goLogin"
                >로그인</span
              >
            </div>
            <div class="font-medium">
              <span
                class="text-black hover:underline cursor-pointer"
                @click="goHome"
                >돌아가기</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      user: {
        id: "",
        name: "",
        email: "",
        password: "",
      },
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
    goLogin() {
      this.$router.push("/login");
    },
    goHome() {
      this.$router.push("/");
    },
    register() {
      console.log("regist함수 호출");
      const url = "http://localhost:8080/api/auth/regist";
      axios
        .post(url, this.user)
        .then(() => {
          alert("success");
          this.$router.push("/login");
        })
        .catch((err) => {
          alert("failed in sign in");
        });
    },
  },
};
</script>

<style lang="scss" scoped></style>
