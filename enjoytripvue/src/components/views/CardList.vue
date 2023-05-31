<template>
  <div class="w-[93%] my-10 mx-auto">
    <ul class="grid gap-10 grid-cols-4 pb-16">
      <li
        class="h-96"
        id="card_li"
        v-for="result in getSearchResult"
        :key="result.contentId">
        <div
          class="transition ease-in-out delay-100 hover:scale-110 duration-100 h-full relative block overflow-hidden rounded-xl bg-cover bg-center bg-no-repeat cursor-pointer"
          :style="{
            backgroundImage: result.firstImage
              ? `url(${result.firstImage})`
              : 'url(img/no-image.jpg)',
          }"
          @click="goPath(`detail/${result.contentId}`)">
          <div class="absolute inset-0 bg-black/25"></div>
          <div
            class="h-full relative flex items-end justify-between p-4 sm:p-6 lg:p-8">
            <div class="sm:pt-18 pt-12 text-white lg:pt-24">
              <h3 class="text-xl font-bold sm:text-2xl">{{ result.title }}</h3>
              <p class="text-sm">{{ result.addr1 }}</p>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters("search", ["getSearchResult"]),
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
};
</script>

<style lang="scss" scoped></style>
