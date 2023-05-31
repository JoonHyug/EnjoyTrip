<template>
  <div class="w-full h-full">
    <div
      class="flex content-center items-center justify-center h-full w-full text-sm select-none">
      <div
        class="border border-slate-200 border-2 my-28 w-full sm:w-[479px] shadow-lg rounded-xl bg-slate-200 h-[70%] px-5 py-14 sm:p-14 grid place-items-center text-slate-800">
        <img src="/img/sample/logo.png" alt="LOGO" />
        <h1 class="mb-5 text-lg font-bold text-slate-800 uppercase mt-7">
          비밀번호를 찾으시겠습니까?
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

            <button
              type="submit"
              class="w-full h-14 bg-blue-500 text-[15px] font-bold text-slate-50 rounded uppercase font-bold hover:brightness-110"
              @click="getpwd">
              비밀번호 찾기
            </button>
          </div>
        </div>

        <div class="grid place-items-center text-[13px] gap-2">
          <div class="font-medium">
            <span class="mt-1"> 비밀번호가 기억나셨나요? </span>
            <span
              class="text-black hover:underline cursor-pointer"
              @click="goLogin"
              >로그인</span
            >
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
      id: "",
    };
  },
  methods: {
    goLogin() {
      this.$router.push("/login");
    },
    getpwd() {
      const url = "http://localhost:8080/api/auth/getpwd";
      axios
        .post(url, { id: this.id })
        .then((res) => {
          console.log(res.data);
          let pwd = res.data;
          if (pwd === null || pwd === undefined || pwd === "") {
            alert("해당 아이디가 존재하지 않습니다.");
            return;
          }
          alert("비밀번호 : " + pwd);
          this.$router.push("/login");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss" scoped></style>
