<template>
  <div :class="{ 'h-full': isMap }">
    <sidebar />
    <main
      class="h-full"
      :class="{ main: !isMap, flex: isCard, 'flex-col': isCard }"
      id="main"
      v-bind:style="{ 'margin-left': marginLeft }">
      <Header class="header" v-show="!isMap" />
      <router-view :style="{ 'min-height': computedHeight }" />
      <Footer class="footer" v-show="!isMap" />
    </main>
  </div>
</template>

<script>
import Sidebar from "@/components/sidebar/Sidebar.vue";
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import { mapGetters } from "vuex";
export default {
  name: "App",
  components: {
    Sidebar,
    Header,
    Footer,
  },
  data() {
    return {
      isMap: false,
      isCard: false,
      isDetail: false,
      headerHeight: 0,
      footerHeight: 0,
    };
  },
  computed: {
    ...mapGetters("search", ["getIsOpen"]),
    marginLeft() {
      if (this.$route.path === "/card_search") {
        return this.getIsOpen ? "25rem" : "0rem";
      }
      return "0rem";
    },
    computedHeight() {
      const windowHeight = window.innerHeight;
      const totalHeight = windowHeight - this.headerHeight - this.footerHeight;
      return `${totalHeight}px`;
    },
  },
  methods: {
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
  },
  created() {
    if (this.$route.path === "/map_search") {
      this.isMap = true;
    }
  },
  watch: {
    $route(to, from) {
      if (to.path == "/map_search") {
        this.isMap = true;
      } else {
        this.isMap = false;
      }
      if (to.path == "/card_search") {
        this.isCard = true;
      } else {
        this.isCard = false;
      }
    },
    "$store.state.auth.token": {
      immediate: true,
      handler(token) {
        if (token !== undefined) {
          if (token) {
            this.$store.dispatch("auth/decodeToken", token);
          } else {
            this.$store.dispatch("auth/logout");
          }
        }
      },
    },
  },
  mounted() {
    const headerHeight = document.querySelector(".header");
    if (headerHeight) {
      this.headerHeight = headerHeight.offsetHeight;
    }
    const footerHeight = document.querySelector(".footer");
    if (footerHeight) {
      this.footerHeight = footerHeight.offsetHeight;
    }
  },
};
</script>

<style lang="scss" scoped>
@import url("@/assets/css/scrollbar.css");
.main {
  transition: margin-left 0.5s;
}
.main_kakaoMap {
  transition: margin-left 0s;
}
</style>
