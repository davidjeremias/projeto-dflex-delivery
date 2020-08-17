	<template>
<div>
  <v-navigation-drawer v-model="drawer" :clipped="$vuetify.breakpoint.lgAndUp" app>

      <v-list dense>
        <template v-for="item in items">

          <v-row v-if="item.heading" :key="item.heading" align="center">
            <v-col cols="6">
              <v-subheader v-if="item.heading"> {{ item.heading }} </v-subheader>
            </v-col>
            <v-col cols="6" class="text-center">
              <a href="#!" class="body-2 black--text">EDIT</a>
            </v-col>
          </v-row>

          <v-list-group v-else-if="item.children" :key="item.text" v-model="item.model" :prepend-icon="item.model ? item.icon : item['icon-alt']" append-icon="">
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title>
                  {{ item.text }}
                </v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item v-for="(child, i) in item.children" :key="i" link @click="toRoute(item.nameRota)">
              <v-list-item-action v-if="child.icon">
                <v-icon>{{ child.icon }}</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>
                  {{ child.text }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>

          <v-list-item v-else :key="item.text" link @click="toRoute(item.nameRota)">
            <v-list-item-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>
                {{ item.text }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>

        </template>
      </v-list>
    </v-navigation-drawer>

    <!-- HEADER -->
    <v-app-bar :clipped-left="$vuetify.breakpoint.lgAndUp" app color="red darken-3" dark elevation="5" height="69">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
        <span class="hidden-sm-and-down">RicoPay Pagamentos</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
  
      <div class="espacamento">
        <v-list-item>
            <v-list-item-avatar>
            <v-icon>mdi-account-circle</v-icon>
            </v-list-item-avatar>

            <v-list-item-content>
            <v-list-item-title>{{nomeUsuarioLogado}}</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
      </div>
      
      <v-spacer></v-spacer>
      <v-btn rounded dark @click="logoff">
        <span>Sair</span>
        <v-icon>mdi-logout</v-icon>
      </v-btn>
      
    </v-app-bar>
  
    </div>
</template>

<script>
  export default {
    name: 'Menu',
    data () {
      return {
        nomeUsuarioLogado: '',
        drawer: true,
        dialog: false,
      items: [
        { icon: 'mdi-plus-circle', text: 'Cadastro de Logista', nameRota: 'CadastroEmpresa' },
        { icon: 'mdi-account-plus', text: 'Cadastro de Operador', nameRota: 'CadastroOperador'},
        { icon: 'mdi-credit-card-plus', text: 'Efetuar Vendas', nameRota: 'EfetuarVenda' },
        { icon: 'mdi-credit-card-outline', text: 'Consultar Vendas', nameRota: 'ConsultarVenda' },
        { icon: 'mdi-cancel', text: 'Cancelar Vendas', nameRota: 'CancelarVenda' },
        { icon: 'mdi-cog', text: 'Configuracao', nameRota: 'Configuracao' }
      ],
      }
    },
    methods:{
        toRoute(nameRota){
            this.$router.push({ name: nameRota });
        },
        logoff(){
            sessionStorage.removeItem("token");
            sessionStorage.removeItem("user");
            this.nomeUsuarioLogado = '';
            this.$router.push({ name: "Login" });
        }
    },
    created(){
        this.nomeUsuarioLogado = this.$username;
    }
  }
</script>

<style scoped>
  .espacamento{
    padding-left: 51em;
  }
  .v-application--is-ltr .v-list-item__avatar:first-child{
    margin-right: -1px;
  }
</style>