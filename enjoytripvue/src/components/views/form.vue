<template>
  <div>
    <h2>파일 업로드</h2>

    <h4>단일 파일 업로드</h4>
    <input type="file" name="file" @change="handleFile" />

    <input type="submit" @click="sendFile" />

    <div
      class="w-[30rem] h-[30rem]"
      :style="{
        backgroundImage: profileImage
          ? `url(img/profile/${profileImage})`
          : 'url(img/no-image.jpg)',
      }">
      {{ profileImage }}
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      sigleFile: null,
    };
  },
  computed: {
    ...mapState("auth", { userInfo: "info" }),
    ...mapState("file", ["profileImage"]),
  },
  methods: {
    handleFile(event) {
      const singleFile = new FormData();
      singleFile.append("uploadFile", event.target.files[0]);
      this.singleFile = singleFile;
    },
    sendFile() {
      this.$store.dispatch("file/uploadFile", {
        uploadFile: this.singleFile,
        accountId: this.userInfo.id,
        type: "profile",
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

<style lang="scss" scoped></style>
