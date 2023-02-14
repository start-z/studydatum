import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  // mode:History,
  routes: [
    {
      path: '/',
      name: 'Index',
      component: ()=>import('@/components/Index.vue')
    }
  ]
})
