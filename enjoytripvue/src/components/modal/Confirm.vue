<template>
  <div
    class="min-w-screen h-screen animated fadeIn faster fixed left-0 top-0 flex justify-center items-center inset-0 z-50 outline-none focus:outline-none bg-no-repeat bg-center bg-cover">
    <div class="absolute bg-black opacity-80 inset-0 z-0"></div>
    <div
      class="w-full max-w-lg p-5 relative mx-auto my-auto rounded-xl shadow-lg bg-white">
      <!--content-->
      <div class="">
        <!--body-->
        <template v-if="type === 'delete' || type === 'move'">
          <div class="text-center p-5 flex-auto justify-center">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="w-4 h-4 -m-1 flex items-center text-red-500 mx-auto"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor">
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="w-16 h-16 flex items-center text-red-500 mx-auto"
              viewBox="0 0 20 20"
              fill="currentColor">
              <path
                fill-rule="evenodd"
                d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
                clip-rule="evenodd" />
            </svg>
            <h2 class="text-xl font-bold py-4"></h2>
            <h3>{{ message }}</h3>
            <p class="text-sm text-gray-500 px-8"></p>
          </div>
        </template>
        <template v-if="type === 'input'">
          <h1 class="flex justify-center my-4 text-2xl font-bold">
            여행 계획 생성
          </h1>
          <div class="flex flex-col w-[90%] mx-auto">
            <div class="flex flex-row">
              <p class="w-1/6 text-xl">제목</p>
              <input
                type="text"
                class="w-5/6 border-2 border-blue-500 rounded-md"
                v-model="modalTitle" />
            </div>
          </div>
          <div class="flex justify-center text-xl mt-4">
            {{ message }}
          </div>
        </template>
        <!--footer-->
        <div class="p-3 mt-2 text-center space-x-4 md:block">
          <button
            class="duration-300 mb-2 md:mb-0 bg-white px-5 py-2 text-sm shadow-sm font-medium tracking-wider border text-gray-600 rounded-full hover:shadow-lg hover:bg-gray-100"
            @click="cancelModal">
            취소
          </button>
          <button
            class="duration-300 mb-2 md:mb-0 bg-blue-400 border border-blue-400 px-5 py-2 text-sm shadow-sm font-medium tracking-wider text-white rounded-full hover:shadow-lg hover:bg-blue-600"
            @click="[handleConfirm(), sendTitle()]"
            v-if="type === 'input'">
            확인
          </button>
          <button
            class="duration-300 mb-2 md:mb-0 bg-blue-400 border border-blue-400 px-5 py-2 text-sm shadow-sm font-medium tracking-wider text-white rounded-full hover:shadow-lg hover:bg-blue-600"
            @click="handleConfirm"
            v-if="type === 'delete'">
            삭제
          </button>
          <button
            class="duration-300 mb-2 md:mb-0 bg-blue-400 border border-blue-400 px-5 py-2 text-sm shadow-sm font-medium tracking-wider text-white rounded-full hover:shadow-lg hover:bg-blue-600"
            @click="moveConfirm"
            v-if="type === 'move'">
            이동
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    message: {
      type: String,
      required: true,
    },
    type: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      modalTitle: "",
    };
  },
  methods: {
    handleConfirm() {
      this.$emit("confirm", true);
    },
    moveConfirm() {
      this.$emit("moveConfirm", true);
    },
    cancelModal() {
      this.$emit("cancel");
    },
    sendTitle() {
      const title = this.modalTitle;
      this.$emit("sendTitle", title);
    },
  },
};
</script>

<style lang="scss" scoped></style>
