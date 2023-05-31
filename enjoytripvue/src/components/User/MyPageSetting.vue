<template>
  <div class="w-full h-full">
    <div class="flex items-center justify-center mx-auto p-4">
      <!-- Container -->
      <div
        class="relative w-[90vw] h-[95vh] flex items-center bg-gray-200 justify-center bg-grey-500 overflow-hidden rounded-2xl shadow-md shadow-gray-300">
        <!-- sidebar grid -->
        <div
          class="flex flex-col items-start bg-white shadow shadow-gray-300 border-r-2 border-slate-200 w-[18%] h-full overflow-auto">
          <div class="flex items-center px-6 pt-6 cursor-pointer">
            <router-link to="/">
              <img
                src="/img/sample/logo.png"
                class="max-w-[125px] mr-2 p-1"
                alt="EnjoyTrip" />
            </router-link>
          </div>
          <!-- <div class="border-[1px] border-black w-full my-8"></div> -->
          <div class="w-full">
            <h3 id="divTitle" class="text-base text-blue-900 px-6 pb-6 mt-12">
              My Page
            </h3>
            <ul class="grid items-center">
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800">
                <div class="flex items-center font-medium">
                  <img src="/img/sample/home.svg" class="mr-2" alt="home" />
                  <router-link to="/mypagehome"><span>Home</span></router-link>
                </div>
              </li>
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800">
                <div class="flex items-center font-medium">
                  <img
                    src="/img/sample/bookmark.svg"
                    class="mr-2"
                    alt="bookmark" />
                  <router-link to="/mypagebookmark"
                    ><span>BookMark</span></router-link
                  >
                </div>
              </li>
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800">
                <div class="flex items-center font-medium">
                  <img src="/img/sample/review.svg" class="mr-2" alt="review" />
                  <router-link to="/mypagereview"
                    ><span>Reviews</span></router-link
                  >
                </div>
              </li>
            </ul>
          </div>

          <div class="pb-40 w-full">
            <h3 class="text-base text-blue-900 px-6 pb-6 mt-8">SETTINGS</h3>
            <ul class="grid items-center">
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800">
                <div class="flex items-center font-medium">
                  <img src="/img/sample/user.svg" class="mr-2" alt="user" />
                  <div @click="resetinfo"><span>Edit Profile</span></div>
                </div>
              </li>
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800">
                <div class="flex items-center font-medium">
                  <img src="/img/sample/logout.svg" class="mr-2" alt="logout" />
                  <span @click="logout">Logout</span>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <!-- ./ sidebar grid -->

        <!-- Body -->
        <div class="w-[82%] bg-white h-full p-8 overflow-auto">
          <!-- Body > Top -->
          <div>
            <div
              class="flex items-center justify-between border-b-2 border-slate-600 px-8 pb-8">
              <div>
                <h1 class="font-bold text-2xl mb-4">Welcome to EnjoyTrip.</h1>
                <p class="text-gray-700 font-medium">
                  Hello Saffy, Welcome back!
                </p>
              </div>
            </div>
          </div>

          <!-- component -->
          <div class="w-full px-8 pb-8 border-b-2 border-slate-600">
            <div id="heading" class="flex items-center justify-between py-6">
              <h1 class="text-xl font-bold">프로필 사진 변경</h1>
            </div>

            <div class="text-center">
              <!-- Current Profile Photo -->
              <div class="mt-2 ml-10 flex justify-evenly">
                <div
                  class="w-[10rem] h-[10rem] rounded-full bg-cover cursor-pointer shadow-xl border-2 border-slate-400"
                  :style="{
                    backgroundImage: profileImage
                      ? `url(/img/profile/${profileImage})`
                      : 'url(/img/no-image.jpg)',
                  }"></div>
                <div class="w-[10rem] h-[10rem]">
                  <i
                    class="bi bi-caret-right-fill h-full w-full flex justify-center items-center text-8xl"></i>
                </div>
                <div
                  class="w-[10rem] h-[10rem] rounded-full bg-cover shadow-xl border-2 border-slate-400 flex"
                  :class="
                    picture == null
                      ? ['justify-center', 'items-center']
                      : ['justify-center', 'items-end']
                  "
                  :style="{
                    backgroundImage: preview ? `url(${preview})` : '',
                  }">
                  <label class="">
                    <input
                      class="text-sm cursor-pointer hidden h-full"
                      type="file"
                      @change="loadPicture" />
                    <div
                      class="text bg-indigo-600 text-white border border-gray-300 rounded font-semibold cursor-pointer p-1 px-3 hover:bg-indigo-500">
                      사진 선택
                    </div>
                  </label>
                </div>
              </div>
              <!-- New Profile Photo Preview -->
              <div class="mt-2" style="display: none">
                <span
                  class="block w-40 h-40 rounded-full m-auto shadow"
                  style="
                    background-size: cover;
                    background-repeat: no-repeat;
                    background-position: center center;
                    background-image: url('null');
                  ">
                </span>
              </div>
              <button
                type="button"
                class="inline-flex items-center px-4 py-2 bg-white border border-gray-300 rounded-md font-semibold text-xs text-gray-700 uppercase tracking-widest shadow-sm hover:text-gray-500 focus:outline-none focus:border-blue-400 focus:shadow-outline-blue active:text-gray-800 active:bg-gray-50 transition ease-in-out duration-150 ml-8"
                @click="uploadPicture">
                변경하기
              </button>
            </div>
          </div>

          <div
            id="listingSection"
            class="w-full px-8 pb-8 border-b-2 border-slate-600">
            <div id="heading" class="flex items-center justify-between py-6">
              <h1 class="text-xl font-bold">Edit Profile</h1>
            </div>

            <div class="mx-8 px-8 py-8 w-2/5 bg-white rounded-lg shadow-lg">
              <div class="flex">
                <div class="flex mb-8 items-center">
                  <h1 class="inline text-2xl font-semibold mr-4">Info</h1>
                  <div>
                    <svg
                      class="inline align-text-top"
                      xmlns="http://www.w3.org/2000/svg"
                      height="24px"
                      viewBox="0 0 24 24"
                      width="24px"
                      fill="#000000">
                      <path d="M0 0h24v24H0V0z" fill="none"></path>
                      <path
                        d="M5 18.08V19h.92l9.06-9.06-.92-.92z"
                        opacity=".3"></path>
                      <path
                        d="M20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.2-.2-.45-.29-.71-.29s-.51.1-.7.29l-1.83 1.83 3.75 3.75 1.83-1.83zM3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM5.92 19H5v-.92l9.06-9.06.92.92L5.92 19z"></path>
                    </svg>
                  </div>
                </div>
              </div>
              <form
                v-on:submit.prevent="upadateUserInfo(userInfo.id)"
                class="bg-white">
                <div class="flex flex-col justify-between gap-4">
                  <h2 class="flex items-center px-3 font-bold">
                    ID : {{ userInfo.id }}
                  </h2>
                  <div
                    class="flex items-center border-2 py-2 px-3 rounded-2xl mb-4">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5 text-gray-400"
                      viewBox="0 0 20 20"
                      fill="currentColor">
                      <path
                        fill-rule="evenodd"
                        d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z"
                        clip-rule="evenodd" />
                    </svg>
                    <input
                      v-model.trim="editInfo.newid"
                      class="pl-2 outline-none border-none"
                      type="text"
                      name=""
                      id=""
                      placeholder="new ID" />
                  </div>
                  <h2 class="flex items-center px-3 font-bold">
                    Name : {{ userInfo.name }}
                  </h2>
                  <div
                    class="flex items-center border-2 py-2 px-3 rounded-2xl mb-4">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5 text-gray-400"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke="currentColor">
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M12 11c0 3.517-1.009 6.799-2.753 9.571m-3.44-2.04l.054-.09A13.916 13.916 0 008 11a4 4 0 118 0c0 1.017-.07 2.019-.203 3m-2.118 6.844A21.88 21.88 0 0015.171 17m3.839 1.132c.645-2.266.99-4.659.99-7.132A8 8 0 008 4.07M3 15.364c.64-1.319 1-2.8 1-4.364 0-1.457.39-2.823 1.07-4" />
                    </svg>
                    <input
                      v-model.trim="editInfo.name"
                      class="pl-2 outline-none border-none"
                      type="text"
                      name=""
                      id=""
                      placeholder="new Name" />
                  </div>

                  <h2 class="flex items-center px-3 font-bold">
                    Email : {{ userInfo.email }}
                  </h2>
                  <div
                    class="flex items-center border-2 py-2 px-3 rounded-2xl mb-4">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5 text-gray-400"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke="currentColor">
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207" />
                    </svg>
                    <input
                      v-model.trim="editInfo.email"
                      class="pl-2 outline-none border-none"
                      type="text"
                      name=""
                      id=""
                      placeholder="new Email Address" />
                  </div>
                </div>
                <div class="flex flex-row-reverse p-3">
                  <button
                    type="submit"
                    class="block w-2/5 bg-blue-600 mt-4 py-2 rounded-2xl text-white font-semibold mb-2">
                    Edit
                  </button>
                </div>
              </form>
            </div>
          </div>

          <div class="w-full px-8 pb-8 border-b-2 border-slate-600">
            <div id="heading" class="flex items-center justify-between py-6">
              <h1 class="text-xl font-bold">Change Password</h1>
            </div>
            <div class="mx-8 px-8 py-8 w-2/5 bg-white rounded-lg shadow">
              <div class="flex">
                <div class="flex mb-8 items-center">
                  <h1 class="inline text-2xl font-semibold mr-4">Password</h1>
                  <div>
                    <svg
                      class="inline align-text-top"
                      xmlns="http://www.w3.org/2000/svg"
                      height="24px"
                      viewBox="0 0 24 24"
                      width="24px"
                      fill="#000000">
                      <path d="M0 0h24v24H0V0z" fill="none"></path>
                      <path
                        d="M5 18.08V19h.92l9.06-9.06-.92-.92z"
                        opacity=".3"></path>
                      <path
                        d="M20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.2-.2-.45-.29-.71-.29s-.51.1-.7.29l-1.83 1.83 3.75 3.75 1.83-1.83zM3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM5.92 19H5v-.92l9.06-9.06.92.92L5.92 19z"></path>
                    </svg>
                  </div>
                </div>
              </div>
              <form
                v-on:submit.prevent="upadateUserpwd(userInfo.id)"
                class="bg-white">
                <div class="flex flex-col justify-between gap-4">
                  <div
                    class="flex items-center border-2 py-2 px-3 rounded-2xl mb-4">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5 text-gray-400"
                      viewBox="0 0 20 20"
                      fill="currentColor">
                      <path
                        fill-rule="evenodd"
                        d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z"
                        clip-rule="evenodd" />
                    </svg>
                    <input
                      v-model.trim="editPassword.password"
                      class="pl-2 outline-none border-none"
                      type="text"
                      name=""
                      id=""
                      placeholder="Password" />
                  </div>
                  <div
                    class="flex items-center border-2 py-2 px-3 rounded-2xl mb-4">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5 text-gray-400"
                      viewBox="0 0 20 20"
                      fill="currentColor">
                      <path
                        fill-rule="evenodd"
                        d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z"
                        clip-rule="evenodd" />
                    </svg>
                    <input
                      v-model.trim="editPassword.newpassword"
                      class="pl-2 outline-none border-none"
                      type="text"
                      name=""
                      id=""
                      placeholder="Confirm Password" />
                  </div>
                </div>

                <div class="flex flex-row-reverse p-3">
                  <button
                    type="submit"
                    class="block w-2/5 bg-blue-600 mt-4 py-2 rounded-2xl text-white font-semibold mb-2">
                    Edit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
        <!-- ./Body -->
      </div>
    </div>
    <!-- ./Container -->
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  data() {
    return {
      editInfo: {
        id: "",
        newid: "",
        name: "",
        email: "",
      },
      editPassword: {
        id: "",
        password: "",
        newpassword: "",
      },
      picture: null,
      preview: null,
    };
  },
  computed: {
    ...mapState("auth", { userInfo: "info" }),
    ...mapState("file", ["profileImage"]),
  },
  methods: {
    resetinfo() {
      this.editInfo.newid = "";
      this.editInfo.name = "";
      this.editInfo.email = "";
      this.editPassword.newpassword = "";
      this.editPassword.password = "";
    },
    upadateUserInfo(id) {
      this.editInfo.id = id;
      const url = `http://localhost:8080/api/auth/info/${id}`;
      axios
        .put(url, this.editInfo)
        .then(() => {
          alert("Success");
          console.log(this);
          this.$store.commit("auth/setInfo", {
            id: this.editInfo.newid,
            name: this.editInfo.name,
            email: this.editInfo.email,
          });
          this.resetinfo();
        })
        .catch((err) => {
          console.log(this.account);
          console.log(err);
          alert("Failed in Editing");
        });
    },
    upadateUserpwd(id) {
      this.editPassword.id = id;
      const url = `http://localhost:8080/api/auth/pwd/${id}`;
      axios
        .put(url, this.editPassword)
        .then(() => {
          alert("Success");
          this.resetinfo();
        })
        .catch((err) => {
          console.log(this.account);
          console.log(err);
          alert("Failed in Editing");
        });
    },
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/");
    },
    loadPicture(event) {
      const singleFile = new FormData();
      singleFile.append("uploadFile", event.target.files[0]);
      this.picture = singleFile;

      this.previewImage(event.target.files[0]);
    },
    previewImage(image) {
      const reader = new FileReader();
      reader.onload = (event) => {
        this.preview = event.target.result;
      };
      reader.readAsDataURL(image);
    },
    uploadPicture() {
      this.$store
        .dispatch("file/uploadFile", {
          uploadFile: this.picture,
          accountId: this.userInfo.id,
          type: "profile",
        })
        .then(() => {
          this.$store.dispatch("file/getProfileImage", {
            accountId: this.userInfo.id,
            type: "profile",
          });
        });
    },
  },
  created() {
    this.$store.dispatch("file/getProfileImage", {
      accountId: this.userInfo.id,
      type: "profile",
    });
  },
};
</script>

<style lang="scss" scoped>
html {
  font-size: 90%;
}
</style>
