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
            <div
              v-if="bookmarks.length === 0"
              class="flex items-center w-full border-b-2 border-slate-600 pb-12 gap-8">
              <div id="listingSection" class="w-[750px] h-[250px] px-8">
                <div
                  id="heading"
                  class="flex items-center justify-between pb-6">
                  <h1 class="text-xl font-bold my-4">BookMarks</h1>
                </div>

                <div class="flex">
                  <div class="bg-white rounded-lg p-3 shadow-lg">
                    <div class="flex flex-row">
                      <div class="ml-2 mr-6 px-2 py-4">
                        <div>
                          <span class="font-semibold">No Bookmark</span>

                          <div class="flex items-center">
                            <span class="block text-gray-500 text-sm"
                              >Save BookMarks for your own</span
                            >
                            <button id="btn" class="">
                              <router-link
                                to=""
                                class="flex items-center ml-20 rounded-lg px-2 py-2 hover:bg-slate-300 text-sm font-medium text-slate-600"
                                >Search Destination

                                <img
                                  src="/img/sample/arrowright.svg"
                                  id="icon"
                                  alt="arrow" />
                              </router-link>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div
              v-else
              class="flex items-center w-full border-b-2 border-slate-600 pb-12 gap-8">
              <div id="listingSection" class="w-full px-8">
                <div
                  id="heading"
                  class="flex items-center justify-between pb-6">
                  <h1 class="text-xl font-bold">BookMarks</h1>

                  <button id="btn" class="">
                    <router-link
                      to="/mypagebookmark"
                      class="flex items-center ml-20 rounded-lg px-2 py-2 hover:bg-slate-300 font-medium text-slate-600"
                      >See All

                      <img
                        src="/img/sample/arrowright.svg"
                        id="icon"
                        alt="arrow" />
                    </router-link>
                  </button>
                </div>

                <div
                  id="secContainer"
                  class="flex items-center justify-between gap-4">
                  <div
                    v-for="bookmark in bookmarks"
                    class="max-w-2xl mx-auto"
                    :key="`${bookmark.title}`">
                    <div
                      class="bg-white shadow-md border border-gray-200 rounded-lg max-w-sm dark:bg-gray-800 dark:border-gray-700">
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
                          class="text-gray-900 font-bold text-2xl tracking-tight mb-2 dark:text-white">
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

            <!-- Body > Activity -->
            <div
              v-if="getUser3Reviews && getUser3Reviews.length === 0"
              class="flex items-center w-full border-b-2 border-slate-600 pb-12 gap-8"
            >
              <div id="listingSection" class="w-[750px] h-[250px] px-8">
                <div
                  id="heading"
                  class="flex items-center justify-between pb-6">
                  <h1 class="text-xl font-bold my-4">Reviews</h1>
                </div>

                <div class="flex">
                  <div class="bg-white rounded-lg p-3 shadow-lg">
                    <div class="flex flex-row">
                      <div class="ml-2 mr-6 px-2 py-4">
                        <div>
                          <span class="font-semibold">No Review</span>

                          <div class="flex items-center">
                            <span class="block text-gray-500 text-sm"
                              >Regist new Reveiw Now</span
                            >
                            <button id="btn" class="">
                              <router-link
                                to=""
                                class="flex items-center ml-20 rounded-lg px-2 py-2 hover:bg-slate-300 text-sm font-medium text-slate-600"
                                >Search Destination

                                <img
                                  src="/img/sample/arrowright.svg"
                                  id="icon"
                                  alt="arrow" />
                              </router-link>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div
              v-else
              class="flex items-center w-full border-b-2 border-slate-600 gap-8">
              <div id="listingSection" class="w-full px-8">
                <div
                  id="heading"
                  class="flex items-center justify-between pb-6">
                  <h1 class="text-xl font-bold">Reviews</h1>

                  <button id="btn" class="">
                    <router-link
                      to="/mypagereview"
                      class="flex items-center ml-20 rounded-lg px-2 mr-2 py-2 hover:bg-slate-300 font-medium text-slate-600"
                      >See All

                      <img
                        src="/img/sample/arrowright.svg"
                        id="icon"
                        alt="arrow" />
                    </router-link>
                  </button>
                </div>
                <div
                  class="-my-2 pt-2 pb-12 overflow-x-auto sm:-mx-6 sm:px-6 lg:-mx-8 pr-10 lg:px-8">
                  <div
                    class="align-middle inline-block w-full shadow-xl bg-white shadow-dashboard px-8 pt-3 rounded-lg">
                    <table class="w-full mt-8">
                      <tr class="w-full">
                        <th
                          class="px-6 py-3 w-[5%] border-b-2 border-gray-300 text-left leading-4 text-blue-500 tracking-wider">
                          No.
                        </th>
                        <th
                          class="px-6 py-3 w-[15%] border-b-2 border-gray-300 text-left text-sm leading-4 text-blue-500 tracking-wider">
                          Name
                        </th>
                        <th
                          class="px-6 py-3 w-[40%] border-b-2 border-gray-300 text-left text-sm leading-4 text-blue-500 tracking-wider">
                          Review
                        </th>
                        <th
                          class="px-6 py-3 w-[15%] border-b-2 border-gray-300 text-left text-sm leading-4 text-blue-500 tracking-wider">
                          Rate
                        </th>
                        <th
                          class="px-6 py-3 w-[15%] border-b-2 border-gray-300 text-left text-sm leading-4 text-blue-500 tracking-wider">
                          Created At
                        </th>
                        <th
                          class="px-6 py-3 w-[5%] border-b-2 border-gray-300 text-left text-sm leading-4 text-blue-500 tracking-wider">
                          Edit
                        </th>
                        <th
                          class="px-6 py-3 w-[5%] border-b-2 border-gray-300 text-left text-sm leading-4 text-blue-500 tracking-wider">
                          Delete
                        </th>
                      </tr>

                      <tr
                        v-for="(review, idx) in getUser3Reviews"
                        :key="'review' + idx"
                        class="border-b border-gray-500">
                        <td
                          class="px-6 py-4 whitespace-nowrap border-b border-gray-500">
                          <div class="flex items-center">
                            <div>
                              <div class="text-sm leading-5 text-gray-800">
                                #{{ idx + 1 }}
                              </div>
                            </div>
                          </div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                          <div class="text-sm leading-5 text-blue-900">
                            {{ review.title }}
                          </div>
                        </td>
                        <td class="">
                          <div
                            class="px-6 py-4 whitespace-nowrap overflow-hidden line-clamp-1 text-blue-900 text-sm leading-5 flex items-center">
                            {{ review.content }}
                          </div>
                        </td>
                        <td
                          class="px-6 py-4 whitespace-nowrap text-blue-900 text-sm leading-5">
                          {{ review.point }} out of 5
                        </td>
                        <td
                          class="px-6 py-4 whitespace-nowraptext-blue-900 text-sm leading-5">
                          {{ changeDateFormat(review.createTime) }}
                        </td>
                        <td
                          @click="updateReview(review.id)"
                          class="px-6 py-4 whitespace-nowrap text-right text-sm leading-5"
                        >
                          <svg
                            class="hover:cursor-pointer hover:fill-blue-800"
                            xmlns="http://www.w3.org/2000/svg"
                            stroke="currentColor"
                            fill="currentColor"
                            stroke-width="2"
                            viewBox="0 0 1024 1024"
                            height="1.3em"
                            width="1.3em">
                            <path
                              d="M257.7 752c2 0 4-.2 6-.5L431.9 722c2-.4 3.9-1.3 5.3-2.8l423.9-423.9a9.96 9.96 0 0 0 0-14.1L694.9 114.9c-1.9-1.9-4.4-2.9-7.1-2.9s-5.2 1-7.1 2.9L256.8 538.8c-1.5 1.5-2.4 3.3-2.8 5.3l-29.5 168.2a33.5 33.5 0 0 0 9.4 29.8c6.6 6.4 14.9 9.9 23.8 9.9zm67.4-174.4L687.8 215l73.3 73.3-362.7 362.6-88.9 15.7 15.6-89zM880 836H144c-17.7 0-32 14.3-32 32v36c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-36c0-17.7-14.3-32-32-32z" />
                          </svg>
                        </td>
                        <td
                          class="px-6 py-4 whitespace-no-wrap text-right text-sm leading-5">
                          <svg
                            @click="deleteById(review.id)"
                            class="hover:cursor-pointer hover:stroke-red-600"
                            xmlns="http://www.w3.org/2000/svg"
                            stroke="currentColor"
                            fill="none"
                            stroke-width="2"
                            viewBox="0 0 24 24"
                            aria-hidden="true"
                            height="1.3em"
                            width="1.3em">
                            <path
                              stroke-linecap="round"
                              stroke-linejoin="round"
                              d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                          </svg>
                        </td>
                      </tr>
                    </table>
                    <div
                      class="sm:flex-1 sm:flex sm:items-center flex-col mb-6 sm:justify-between mt-4 work-sans">
                      <div>
                        <nav
                          class="relative z-0 inline-flex shadow-sm items-center"></nav>
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
import axios from "axios";
import { mapGetters, mapState } from "vuex";
import moment from "moment";

export default {
  computed: {
    ...mapGetters("review", ["getUser3Reviews"]),
    ...mapState("bookmark", { bookmarks: "user3Bookmarks" }),
    ...mapState("auth", { userInfo: "info" }),
  },
  created() {
    this.$store.dispatch("review/get3ReviewsToAccountId", this.userInfo.id);
    this.$store.dispatch("bookmark/get3Bookmarks", this.userInfo.id);
  },
  methods: {
    get3Reviews() {
      this.$store.dispatch("review/get3ReviewsToAccountId", this.userInfo.id);
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
    deleteById(id) {
      const url = `http://localhost:8080/api/review/${id}`;
      axios.delete(url).then(() => {
        alert("success in deleting review");
        this.get3Reviews();
      });
    },
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/");
    },
    updateReview(id) {
      this.$router.push({
        name: "editreview",
        params: { id: id },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
html {
  font-size: 90%;
}
</style>
