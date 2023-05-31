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
                  <router-link to="/mypagesetting"
                    ><span>Edit Profile</span></router-link
                  >
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

          <div id="bottom" class="flex flex-col items-start mt-8 gap-8">
            <!-- Body > Listing -->
            <div class="flex items-center w-full pb-12 gap-8">
              <div id="listingSection" class="w-full px-8">
                <div
                  id="heading"
                  class="flex items-center justify-between pb-6">
                  <h1 class="text-xl font-bold">BookMarks</h1>
                </div>

                <div id="secContainer" class="grid grid-cols-3 gap-4">
                  <div
                    v-for="bookmark in bookmarks"
                    :key="`${bookmark.title}`"
                    class="max-w-2xl mx-auto h-[27rem]">
                    <div
                      class="flex flex-col justify-center bg-white shadow-md border border-gray-200 rounded-lg max-w-sm dark:bg-gray-800 dark:border-gray-700 h-full">
                      <img
                        class="rounded-t-lg h-[255px] w-[382px] object-cover"
                        :src="
                          bookmark.firstImage
                            ? bookmark.firstImage
                            : '/img/no-image.jpg'
                        "
                        alt="" />

                      <div class="p-5">
                        <h5
                          class="text-gray-900 font-bold text-2xl tracking-tight mb-2 dark:text-white h-[6rem]">
                          {{ bookmark.title }}
                        </h5>

                        <div
                          @click="goDetail(bookmark.contentId)"
                          class="text-white mt-2 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-3 py-2 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                          자세히 보기
                          <svg
                            class="-mr-1 ml-2 h-4 w-4"
                            fill="currentColor"
                            viewBox="0 0 20 20"
                            xmlns="http://www.w3.org/2000/svg">
                            <path
                              fill-rule="evenodd"
                              d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z"
                              clip-rule="evenodd"></path>
                          </svg>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
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
import { mapGetters, mapState } from "vuex";
import moment from "moment";
export default {
  computed: {
    ...mapState("bookmark", { bookmarks: "userBookmarks" }),
    ...mapState("auth", { userInfo: "info" }),
  },
  created() {
    this.$store.dispatch("bookmark/getBookmarks", this.userInfo.id);
  },
  methods: {
    getReviews() {
      this.$store.dispatch("review/getReviewsToAccountId", this.userInfo.id);
    },
    changeDateFormat(value) {
      const now = moment();
      const date = moment(value);
      if (now.isSame(date, "day")) {
        return date.format("HH:mm:ss");
      } else {
        return date.format("YY.MM.DD");
      }
    },
    goDetail(contentId) {
      this.$router.push({
        name: "detail",
        params: { id: contentId },
      });
    },
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/");
    },
  },
};
</script>

<style lang="scss" scoped>
html {
  font-size: 90%;
}
</style>
