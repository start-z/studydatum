<template>
  <div>
  </div>
</template>

<script>
import {esri} from 'esri-loader';

const options = {
  url: "https://js.arcgis.com/4.23/init.js",
  css: "https://js.arcgis.com/4.23/esri/themes/light/main.css",
};
export default {
  name: 'hello',
  data() {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted() {
    this.initMapView()
  },
  methods: {
    async initMapView() {
      const [Map, MapView] = await esri(
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

}
</script>

