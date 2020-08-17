<template>
        <v-app-bar app color="red" dark elevation="5" height="69" :clipped-left="$vuetify.breakpoint.lgAndUp">
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
        
            <v-btn @click="logoff" rounded>
                <span class="mr-1">Sair</span>
                <v-icon>mdi-logout</v-icon>
            </v-btn>
        </v-app-bar>
</template>

<script>
export default {
    name: 'Header',
    data(){
        return{
            nomeUsuarioLogado: '',
            activeBtn: 1,
        }
    },
    created(){
        this.nomeUsuarioLogado = this.$username;
    },
    methods:{
        logoff(){
            sessionStorage.removeItem("token");
            sessionStorage.removeItem("user");
            this.nomeUsuarioLogado = '';
            this.$router.push({ name: "Login" });
        }
    }
}
</script>

<style scoped>
    .espacamento{
        padding-right: 5em;
    }
</style>