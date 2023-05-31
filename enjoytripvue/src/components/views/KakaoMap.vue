<template>
  <div class="map_wrap h-full w-full" id="map_view">
    <div
      id="map"
      style="
        width: 100%;
        height: 100%;
        position: relative;
        overflow: hidden;
      "></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      markers: [],
      infoWindows: [],
      clusterer: null,
    };
  },
  mounted() {
    this.checkScript();
  },
  methods: {
    checkScript() {
      if (window.kakao && window.kakao.maps) {
        this.loadMap();
      } else {
        this.loadScript();
      }
    },
    loadScript() {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_API_KAKAO_KEY}&libraries=clusterer&autoload=false`;
      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
    async loadMap() {
      if (this.map === null) {
        const mapContainer = document.getElementById("map");
        const mapOption = {
          center: new kakao.maps.LatLng(
            this.nowPosition.latitude,
            this.nowPosition.longitude
          ),
          level: 4,
        };
        this.map = await new window.kakao.maps.Map(mapContainer, mapOption);
        const zoomControl = new kakao.maps.ZoomControl();
        this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      }
      this.clusterer = await new kakao.maps.MarkerClusterer({
        map: this.map,
        markers: this.markers,
        gridSize: 60,
        averageCenter: true,
        minLevel: 5,
      });
      this.loadResultToMap(this.searchResult);
    },
    check() {
      if (this.map === null) {
        return false;
      }
      return true;
    },
    async loadResultToMap(result) {
      if (!this.check()) {
        return;
      }
      await this.clearMarkers();
      let totalPoint = 0;
      let totalLatitude = 0;
      let totalLongitude = 0;
      totalPoint = result.length;
      for (let i in result) {
        const data = result[i];
        totalLatitude += Number(data.latitude);
        totalLongitude += Number(data.longitude);
        const position = new kakao.maps.LatLng(data.latitude, data.longitude);
        this.addMarker(position, data);
      }
      const myLocate = new kakao.maps.LatLng(
        this.nowPosition.latitude,
        this.nowPosition.longitude
      );
      this.myLocateMarker(myLocate, "현재 위치");
      this.setMarkers(1);
      const center = new kakao.maps.LatLng(
        totalLatitude / totalPoint,
        totalLongitude / totalPoint
      );
      if (totalPoint !== 0) {
        this.map.setLevel(7);
        this.map.setCenter(center);
      } else {
        this.map.setLevel(4);
        this.map.setCenter(myLocate);
      }
    },

    myLocateMarker(position, data) {
      const imgSrc = "/img/myLocate.svg";
      const imgSize = new kakao.maps.Size(48, 69);
      const imgOption = { offset: new kakao.maps.Point(24, 48) };
      const markerImage = new kakao.maps.MarkerImage(
        imgSrc,
        imgSize,
        imgOption
      );
      const marker = new kakao.maps.Marker({
        image: markerImage,
        position: position,
      });
      marker.setMap(this.map);
      this.markers.push(marker);
      kakao.maps.event.addListener(marker, "mouseover", () => {
        infowindow.setMap(this.map);
      });

      kakao.maps.event.addListener(marker, "mouseout", () => {
        infowindow.setMap(null);
      });
      const infow = document.createElement("div");
      infow.classList.add(
        "p-5",
        "relative",
        "w-[200px]",
        "h-[70px]",
        "bg-no-repeat",
        "rounded-xl",
        "overlaybox",
        "bg-cover",
        "bg-center",
        "bg-white"
      );
      const div1 = document.createElement("div");
      div1.classList.add("absolute", "inset-0", "bg-black/25", "rounded-xl");
      const div2 = document.createElement("div");
      div2.classList.add(
        "h-full",
        "relative",
        "flex",
        "items-end",
        "justify-between",
        "p-4",
        "sm:p-6",
        "lg:p-8"
      );
      infow.appendChild(div1);
      infow.appendChild(div2);
      const div3 = document.createElement("div");
      div3.classList.add("sm:pt-18", "pt-12", "text-white", "lg:pt-24");
      const h3 = document.createElement("h3");
      h3.classList.add("text-xl", "font-bold", "sm:text-2xl");
      h3.innerText = data;
      div2.appendChild(div3);
      div3.appendChild(h3);
      const infowindow = new kakao.maps.CustomOverlay({
        content: infow,
        map: this.map,
        xAnchor: 0.5,
        yAnchor: 1.75,
        position: marker.getPosition(),
      });
      infowindow.setMap(null);
      this.infoWindows.push(infowindow);
    },

    // position : 맵 위에 있는 핑의 위도, 경도
    // message : 인포 윈도우에 넣을 message
    addMarker(position, data) {
      const imgSrc = "/img/geo-alt-fill.svg";
      const imgSize = new kakao.maps.Size(48, 69);
      const imgOption = { offset: new kakao.maps.Point(24, 48) };
      const markerImage = new kakao.maps.MarkerImage(
        imgSrc,
        imgSize,
        imgOption
      );
      const marker = new kakao.maps.Marker({
        image: markerImage,
        position: position,
      });
      marker.setMap(this.map);
      this.markers.push(marker);

      kakao.maps.event.addListener(marker, "mouseover", () => {
        infowindow.setMap(this.map);
      });

      kakao.maps.event.addListener(marker, "mouseout", () => {
        infowindow.setMap(null);
      });
      kakao.maps.event.addListener(marker, "click", () => {
        this.goPath(`detail/${data.contentId}`);
      });

      // 인포윈도우에 표시할 내용
      const infow = document.createElement("div");
      infow.classList.add(
        "p-5",
        "relative",
        "w-[360px]",
        "h-[350px]",
        "bg-no-repeat",
        "rounded-xl",
        "overlaybox",
        "bg-cover",
        "bg-center"
      );
      infow.style.backgroundImage = `url(${data.firstImage})`;
      if (data.firstImage == "" || data.firstImage == undefined) {
        infow.style.backgroundImage = `url("/img/no-image.jpg")`;
      }
      const div1 = document.createElement("div");
      div1.classList.add("absolute", "inset-0", "bg-black/25", "rounded-xl");
      const div2 = document.createElement("div");
      div2.classList.add(
        "h-full",
        "relative",
        "flex",
        "items-end",
        "justify-between",
        "p-4",
        "overflow-hidden"
      );
      infow.appendChild(div1);
      infow.appendChild(div2);
      const div3 = document.createElement("div");
      div3.classList.add("sm:pt-18", "pt-12", "text-white", "lg:pt-24");
      const h3 = document.createElement("h3");
      h3.classList.add("text-xl", "font-bold", "sm:text-2xl", "w-full");
      h3.innerText = data.title;
      const p = document.createElement("p");
      p.classList.add("text-sm");
      p.innerText = data.addr1;
      div2.appendChild(div3);
      div3.appendChild(h3);
      div3.appendChild(p);
      const infowindow = new kakao.maps.CustomOverlay({
        content: infow,
        map: this.map,
        xAnchor: 0.5,
        yAnchor: 1.15,
        position: marker.getPosition(),
      });
      infowindow.setMap(null);
      this.infoWindows.push(infowindow);
    },
    setMarkers(value) {
      if (value === null) {
        this.clusterer.clear();
        this.clusterer.removeMarkers(this.markers);
      } else {
        for (let marker of this.markers) {
          if (value === null) {
            marker.setMap(null);
          } else {
            marker.setMap(this.map);
          }
        }
        this.clusterer.addMarkers(this.markers);
      }
    },
    async clearMarkers() {
      this.setMarkers(null);
      this.markers = [];
      for (let info of this.infoWindows) {
        info.setMap(null);
      }
      this.infoWindows = [];
    },
    goPath(path) {
      this.$store.dispatch("movePath/goPath", {
        path,
        router: this.$router,
        route: this.$route,
      });
    },
  },
  computed: {
    ...mapState("search", ["searchResult"]),
    ...mapState("kakaoMap", ["nowPosition"]),
  },
  created() {
    this.$store.dispatch("kakaoMap/getGeoLocation");
  },
  watch: {
    searchResult(result) {
      if (result.length !== 0) {
        this.loadMap();
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import url("@/assets/css/kakaoMapOverlay.css");
</style>
