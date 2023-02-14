<template>
  <div id="cesiumContainer"></div>
</template>

<script>
import mapView from '@/api/map.js'
export default {
  name: 'Index',
  data () {
    return {
      msg: 'cesuim程序'
    }
  },
  mounted() {
    //初始化地图
    mapView.initMapViewer()
  }
}
</script>

<style scoped>
#cesiumContainer{
  height: 100%;
  width: 100%;
},
body{
  margin:  0 auto;
}
</style>
