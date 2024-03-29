import Vue from 'vue'
import uploader from 'vue-simple-uploader'
import App from './App.vue'

Vue.use(uploader)

/* eslint-disable no-new */
new Vue({
  render(createElement) {
    return createElement(App)
  }
}).$mount('#app')