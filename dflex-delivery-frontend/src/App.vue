<template>
  <v-app>
    <Menu></Menu>
    

    <v-main>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
//import Header from '@/views/templates/Header.vue'
import Menu from '@/views/templates/Menu.vue'
export default {
  name: 'App',
  props: {
      source: String,
    },
  components: {
    //Header,
    Menu
  },
  
};
</script>
