<template>
  <div class="w-full h-full">
    <div class="flex items-center justify-center mx-auto p-4">
      <!-- Container -->
      <div
        class="relative w-[90vw] h-[95vh] flex items-center bg-gray-200 justify-center bg-grey-500 overflow-hidden rounded-2xl shadow-md shadow-gray-300"
      >
        <!-- sidebar grid -->
        <div
          class="flex flex-col items-start bg-white shadow shadow-gray-300 border-r-2 border-slate-200 w-[18%] h-full overflow-auto"
        >
          <div class="flex items-center px-6 pt-6 cursor-pointer">
            <router-link to="/"
              ><img
                src="/img/sample/logo.png"
                class="max-w-[125px] mr-2 p-1"
                alt="EnjoyTrip"
            /></router-link>
          </div>
          <!-- <div class="border-[1px] border-black w-full my-8"></div> -->
          <div class="w-full">
            <h3 id="divTitle" class="text-base text-blue-900 px-6 pb-6 mt-12">
              My Page
            </h3>
            <ul class="grid items-center">
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800"
              >
                <div class="flex items-center font-medium">
                  <img src="/img/sample/home.svg" class="mr-2" alt="home" />
                  <router-link to="/mypagehome"><span>Home</span></router-link>
                </div>
              </li>
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800"
              >
                <div class="flex items-center font-medium">
                  <img
                    src="/img/sample/bookmark.svg"
                    class="mr-2"
                    alt="bookmark"
                  />
                  <router-link to="/mypagebookmark"
                    ><span>BookMark</span></router-link
                  >
                </div>
              </li>
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800"
              >
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
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800"
              >
                <div class="flex items-center font-medium">
                  <img src="/img/sample/user.svg" class="mr-2" alt="user" />
                  <router-link to="/mypagesetting"
                    ><span>Edit Profile</span></router-link
                  >
                </div>
              </li>
              <li
                class="px-6 py-3 relative w-full hover:bg-blue-100 border-l-4 border-transparent hover:border-sky-800"
              >
                <div class="flex items-center font-medium">
                  <img src="/img/sample/logout.svg" class="mr-2" alt="logout" />
                  <span @click="logout">Logout</span>
                </div>
              </li>
            </ul>
          </div>
        </div>

        <!-- Body -->
        <div class="w-[82%] bg-white h-full p-8 overflow-auto">
          <div class="flex items-center justify-center">
            <div
              class="w-1/2 gap-8 items-center relative my-32 py-12 px-5 md:px-10 bg-white shadow-md rounded border border-gray-400"
            >
              <div class="flex justify-between flex-col mt-4">
                <h1 class="text-2xl text-blue-800 font-extrabold mt-4">
                  Edit Review
                </h1>

                <form v-on:submit.prevent="updateReview(review.id)" action="">
                  <label for="name" class="text-gray-700 text-lg font-bold"
                    >review</label
                  >
                  <textarea
                    id="name"
                    class="mb-5 mt-2 h-[10rem] text-gray-600 focus:outline-none focus:border focus:border-indigo-700 font-normal w-full h-10 flex items-center pl-3 text-sm border-gray-300 rounded border"
                    placeholder="write down your review"
                    v-model="review.content"
                  />
                  <div for="point" class="text-gray-800 text-sm font-bold mb-2">
                    Rate
                  </div>
                  <span class="mb-2">
                    <fieldset class="rate">
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating10"
                        name="rating"
                        value="5"
                      /><label for="rating10" title="5점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating9"
                        name="rating"
                        value="4.5"
                      /><label class="half" for="rating9" title="4.5점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating8"
                        name="rating"
                        value="4"
                      /><label for="rating8" title="4점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating7"
                        name="rating"
                        value="3.5"
                      /><label class="half" for="rating7" title="3.5점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating6"
                        name="rating"
                        value="3"
                      /><label for="rating6" title="3점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating5"
                        name="rating"
                        value="2.5"
                      /><label class="half" for="rating5" title="2.5점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating4"
                        name="rating"
                        value="2"
                      /><label for="rating4" title="2점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating3"
                        name="rating"
                        value="1.5"
                      /><label class="half" for="rating3" title="1.5점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating2"
                        name="rating"
                        value="1"
                      /><label for="rating2" title="1점"></label>
                      <input
                        v-model="review.point"
                        type="radio"
                        id="rating1"
                        name="rating"
                        value="0.5"
                      /><label class="half" for="rating1" title="0.5점"></label>
                    </fieldset>
                  </span>
                  <div class="flex items-center justify-start w-full">
                    <button
                      type="submit"
                      class="focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-700 transition duration-150 ease-in-out hover:bg-blue-600 bg-blue-700 rounded text-white px-8 py-2 text-sm"
                    >
                      Submit
                    </button>
                    <router-link to="/mypagehome">
                      <button
                        type="button"
                        class="focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-400 ml-3 bg-gray-100 transition duration-150 text-gray-600 ease-in-out hover:border-gray-400 hover:bg-gray-300 border rounded px-8 py-2 text-sm"
                      >
                        Cancel
                      </button>
                    </router-link>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters, mapState } from "vuex";
import moment from "moment";
export default {
  data() {
    return {
      review: {
        id: 0,
        accountUid: 0,
        content: "",
        point: 0,
        createTime: "",
        contentId: 0,
        accountId: "",
      },
    };
  },
  created() {
    const id = this.$route.params.id;
    this.review.id = id;
  },
  computed: {
    ...mapState("auth", { userInfo: "info" }),
  },
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/");
    },
    updateReview(id) {
      console.log("updatereview 실행");
      const url = `http://localhost:8080/api/review/${id}`;
      axios
        .put(url, this.review)
        .then(() => {
          alert("success");
          this.$router.push("/mypagehome");
        })
        .catch((err) => {
          console.log(err);
          alert("Failed in Ediitting Review");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
.rate {
  display: inline-block;
  border: 0;
}
.rate > input {
  display: none;
}
.rate > label {
  float: right;
  color: #ddd;
}
.rate > label:before {
  display: inline-block;
  font-size: 1.5rem;
  padding-right: 1rem;
  margin: 0;
  cursor: pointer;
  font-family: FontAwesome;
  content: "\f005 ";
}
.rate .half:before {
  content: "\f089 ";
  position: absolute;
  padding-right: 0;
}
.rate input:checked ~ label,
.rate label:hover,
.rate label:hover ~ label {
  color: #fde047 !important;
}
.rate input:checked + .rate label:hover,
.rate input input:checked ~ label:hover,
.rate input:checked ~ .rate label:hover ~ label,
.rate label:hover ~ input:checked ~ label {
  color: #fde047 !important;
}
</style>
