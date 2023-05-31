<template>
  <div class="my-10 overflow-hidden flex items-center justify-center">
    <div class="w-[80%]">
      <!-- 검색 창 -->
      <div
        class="align-middle rounded-tl-lg rounded-tr-lg w-full py-4 bg-white px-12">
        <div class="flex justify-between">
          <div class="items-center flex min-w-[8rem] justify-center">
            <p
              class="text-lg text-center font-bold text-gray-600"
              v-if="type === 1">
              공지사항
            </p>
            <p
              class="text-lg text-center font-bold text-gray-600"
              v-if="type === 2">
              자유게시판
            </p>
          </div>
          <div
            class="inline-flex border rounded w-4/12 pl-3 h-12 bg-transparent">
            <div
              class="flex flex-wrap items-stretch w-full h-full mb-6 relative">
              <div class="flex">
                <span
                  class="flex items-center leading-normal bg-transparent rounded rounded-r-none border border-r-0 border-none py-2 whitespace-no-wrap text-grey-dark text-sm">
                  <svg
                    width="18"
                    height="18"
                    class="w-4 lg:w-auto"
                    viewBox="0 0 18 18"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg">
                    <path
                      d="M8.11086 15.2217C12.0381 15.2217 15.2217 12.0381 15.2217 8.11086C15.2217 4.18364 12.0381 1 8.11086 1C4.18364 1 1 4.18364 1 8.11086C1 12.0381 4.18364 15.2217 8.11086 15.2217Z"
                      stroke="#455A64"
                      stroke-linecap="round"
                      stroke-linejoin="round" />
                    <path
                      d="M16.9993 16.9993L13.1328 13.1328"
                      stroke="#455A64"
                      stroke-linecap="round"
                      stroke-linejoin="round" />
                  </svg>
                </span>
              </div>
              <input
                type="text"
                class="w-full leading-normal tracking-wide flex-1 border border-none border-l-0 rounded rounded-l-none px-3 relative focus:outline-none text-md font-thin"
                placeholder="Search"
                v-model="word" />
            </div>
            <select
              class="items-center text-base text-gray-800 outline-none border px-4 py-2 rounded-l"
              v-model="key">
              <option value="regtime" selected readonly>선택</option>
              <option value="subject">제목</option>
              <option value="accountId">작성자</option>
            </select>
            <div
              class="rounded-r bg-blue-500 text-white text-md w-[30%] flex items-center justify-center cursor-pointer"
              @click="search">
              검색
            </div>
          </div>
        </div>
      </div>
      <!-- 글들 -->
      <div
        class="align-middle inline-block min-h-[39rem] min-w-full shadow overflow-hidden bg-white shadow-dashboard px-8 pt-3 pb-6 border-y-2 border-gray-300">
        <table class="w-full">
          <thead>
            <tr>
              <th
                class="px-6 py-3 border-b-2 border-gray-300 text-center leading-4 text-blue-500 tracking-wider">
                <span class="cursor-pointer" @click="selectOrder('subject')">
                  제목
                </span>
                <span>
                  <i
                    class="bi bi-arrow-down-circle text-blue-200"
                    :class="{
                      hidden: sort && order === 'subject',
                      'text-blue-500': order === 'subject',
                    }"></i>
                  <i
                    class="bi bi-arrow-up-circle text-blue-200"
                    :class="{
                      'text-blue-500': order === 'subject',
                    }"
                    v-if="sort && order === 'subject'"></i>
                </span>
              </th>
              <th
                class="px-6 py-3 border-b-2 border-gray-300 text-center leading-4 text-blue-500 tracking-wider">
                <span class="cursor-pointer" @click="selectOrder('accountId')">
                  작성자
                </span>
                <span>
                  <i
                    class="bi bi-arrow-up-circle text-blue-200"
                    :class="{
                      hidden: sort && order === 'accountId',
                      'text-blue-500': order === 'accountId',
                    }"></i>
                  <i
                    class="bi bi-arrow-down-circle text-blue-200"
                    :class="{
                      'text-blue-500': order === 'accountId',
                    }"
                    v-if="sort && order === 'accountId'"></i>
                </span>
              </th>
              <th
                class="px-6 py-3 border-b-2 border-gray-300 text-center text-sm leading-4 text-blue-500 tracking-wider">
                <span class="cursor-pointer" @click="selectOrder('hit')">
                  조회수
                </span>
                <span>
                  <i
                    class="bi bi-arrow-down-circle text-blue-200"
                    :class="{
                      hidden: sort && order === 'hit',
                      'text-blue-500': order === 'hit',
                    }"></i>
                  <i
                    class="bi bi-arrow-up-circle text-blue-200"
                    :class="{
                      'text-blue-500': order === 'hit',
                    }"
                    v-if="sort && order === 'hit'"></i>
                </span>
              </th>
              <th
                class="px-6 py-3 border-b-2 border-gray-300 text-center text-sm leading-4 text-blue-500 tracking-wider">
                <span class="cursor-pointer" @click="selectOrder('regtime')">
                  작성일
                </span>
                <span>
                  <i
                    class="bi bi-arrow-down-circle text-blue-200"
                    :class="{
                      hidden: sort && order === 'regtime',
                      'text-blue-500': order === 'regtime',
                    }"></i>
                  <i
                    class="bi bi-arrow-up-circle text-blue-200"
                    :class="{
                      'text-blue-500': order === 'regtime',
                    }"
                    v-if="sort && order === 'regtime'"></i>
                </span>
              </th>
            </tr>
          </thead>
          <tbody class="bg-white">
            <tr v-for="item in items" :key="item.id">
              <td
                class="px-6 py-4 whitespace-no-wrap border-b border-gray-500 w-[40%]"
                @click="goPath(`board/detail/${item.articleno}`)">
                <div
                  class="text-sm leading-5 text-gray-800 text-center cursor-pointer overflow-hidden line-clamp-1">
                  {{ item.subject }}
                </div>
              </td>
              <td
                class="px-6 py-4 whitespace-no-wrap border-b border-gray-500 w-[20%]">
                <div class="text-sm leading-5 text-blue-900 text-center">
                  {{ item.accountId }}
                </div>
              </td>
              <td
                class="px-6 py-4 whitespace-no-wrap border-b text-blue-900 border-gray-500 text-sm leading-5 text-center w-[20%]">
                {{ item.hit }}
              </td>
              <td
                class="px-6 py-4 whitespace-no-wrap border-b border-gray-500 text-blue-900 text-sm leading-5 text-center w-[20%]">
                {{ changeDateFormat(item.regtime) }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- 하단 -->
      <div
        class="inline-block min-w-full shadow overflow-hidden bg-white shadow-dashboard px-8 py-3 rounded-bl-lg rounded-br-lg">
        <div class="flex flex-row items-center justify-center">
          <Pagination
            :records="totalItems"
            :per-page="perPage"
            :options="{ chunk: 5 }"
            v-model="currentPage"
            @paginate="pageChanged"
            class="m-2">
          </Pagination>
        </div>
        <div
          class="flex flex-row items-center justify-end mb-6 overflow-hidden">
          <div
            class="bg-blue-500 rounded-xl px-4 py-2 text-white cursor-pointer"
            @click="checkAccess(userInfo.accessId)">
            글 작성
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Pagination from "vue-pagination-2";
import { mapState } from "vuex";
import moment from "moment";

export default {
  components: {
    Pagination,
  },
  data() {
    return {
      currentPage: 1, //현재 페이지
      perPage: 10, //한 페이지에 보여줄 데이터
      word: null,
      key: "regtime",
      sort: false,
      order: "regtime",
    };
  },
  computed: {
    ...mapState("auth", { userInfo: "info" }),
    ...mapState("board", { items: "noticeBoardResult" }),
    ...mapState("board", ["totalItems"]),
    type() {
      if (this.$route.path === "/board/notice") {
        return 1;
      } else if (this.$route.path === "/board/board") {
        return 2;
      }
      return 0;
    },
  },
  mounted() {
    this.search();
  },
  methods: {
    checkAccess(accessId) {
      if (this.type == 1) {
        if (accessId != 2) {
          alert("작성 권한이 없습니다.");
          return;
        } else {
          this.goPath("board/write");
        }
      } else {
        this.goPath("board/write");
      }
    },
    pageChanged(newPage) {
      this.currentPage = newPage;
      this.search();
    },
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
    search() {
      if (this.word) {
        this.currentPage = 1;
      }
      this.$store.dispatch("board/getNoticeBoard", {
        currentPage: this.currentPage,
        perPage: this.perPage,
        word: this.word,
        key: this.key,
        sort: this.sort,
        order: this.order,
        type: this.type,
      });
      this.$store.dispatch("board/getTotalItems", {
        word: this.word,
        key: this.key,
        type: this.type,
      });
    },
    selectOrder(value) {
      this.order = value;
      this.sort = !this.sort;
      this.search();
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
  },
  created() {
    this.search();
  },
  watch: {
    $route(to, from) {
      if (to.path === "/board/notice") {
        this.search();
      } else if (to.path === "/board/board") {
        this.search();
      }
    },
  },
};
</script>

<style lang="scss">
.VuePagination__pagination {
  display: flex;
  justify-content: space-between;
}
.VuePagination__pagination > li {
  margin-left: 0.2rem;
  margin-right: 0.2rem;
}
.VuePagination__count {
  display: none;
}
.page-link {
  cursor: pointer;
  padding: 0.5rem;
}
.VuePagination__pagination-item-prev-chunk {
  @apply rounded-l-xl;
}
.VuePagination__pagination-item-next-chunk {
  @apply rounded-r-xl;
}

.page-item {
  @apply text-blue-600 border
        border-gray-300
        hover:text-gray-600
        hover:bg-gray-300
        transition
        ease-in-out
        duration-150
        overflow-hidden;
}
.active {
  background-color: rgb(209 213 219);
  color: rgb(75 85 99);
}
</style>
