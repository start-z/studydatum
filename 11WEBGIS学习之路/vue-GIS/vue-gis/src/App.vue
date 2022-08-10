<template>
  <div id="mapview"></div>
</template>

<script>
import {loadModules} from "esri-loader";

const options = {
  url: "https://js.arcgis.com/4.23/init.js",
  css: "https://js.arcgis.com/4.23/esri/themes/light/main.css",
};

export default {
  name: "App",
  components: {},
  mounted() {
    this.initMapView();
  },
  methods: {
    async initMapView() {
      const [Map, MapView] = await loadModules(
        ["esri/Map", "esri/views/MapView"],
        options
      );
      const map = new Map({
        basemap: "osm",
      });
      const view = new MapView({
        container: "mapview",
        map: map,
        zoom: 4,
        center: [104.064857, 30.580685],
      });
      console.log(view);
    },
  },
};
</script>

<style>
html,
body,
#mapview {
  width: 100%;
  height: 100%;
  margin: 0;
}
</style>
