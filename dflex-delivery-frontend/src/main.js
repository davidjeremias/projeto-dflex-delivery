import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import moment from 'moment'
import vuetify from './plugins/vuetify'
import VueMask from 'v-mask'

Vue.use(vuetify)
Vue.use(VueMask)

axios.defaults.baseURL = 'http://localhost:9080/ricopay-backend/'

axios.interceptors.request.use(async config => {
  const token = sessionStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

Vue.config.productionTip = false

Vue.prototype.$http = axios
Vue.prototype.$moment = moment

Vue.prototype.$auth = function(authority) {
  let auth = false
  let user = JSON.parse(sessionStorage.getItem('user'));
  if (user !== null && user.permissoes.length > 0) {
      auth = user.funcionalidades.some(a => a === authority);
  }
  return auth
}

const user = JSON.parse(sessionStorage.getItem('user') ? sessionStorage.getItem('user') : null)
if (user !== null && user.nome !== null) {
  Vue.prototype.$username = user.nome;
}

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
