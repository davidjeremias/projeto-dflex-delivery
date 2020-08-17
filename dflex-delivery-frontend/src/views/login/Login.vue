<template>
    <v-dialog v-model="dialog" width="500">
        
        <v-card>
        <v-card-title
          class="headline red lighten-2"
          primary-title
          c
        >
          Acessar Sistema
        </v-card-title>

        <v-card-text>
            <v-form v-model="valid">
                <v-container>
                    <v-text-field
                        v-model="username"
                        :rules="loginRules"
                        label="Login"
                        type="number"
                        required
                        prepend-icon="mdi-account"
                        @keyup="validaLogin()"
                    ></v-text-field>

                    <v-text-field
                        v-model="password"
                        :rules="passwordRules"
                        label="Senha"
                        type="password"
                        min="6" max="6"
                        required
                        prepend-icon="mdi-lock"
                        @keyup="validaLogin()"
                    ></v-text-field>

                </v-container>
            </v-form>
        </v-card-text>

        <v-divider></v-divider>
       

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            @click="login"
            :disabled="isButton"
          >
            Entrar
          </v-btn>
        </v-card-actions>

      </v-card>
    </v-dialog>
</template>

<script>
export default {
    name: 'Login',
    data(){
        return{
            dialog: true,
            valid: false,
            isButton: true,
            username: '',
            password: '',
            loginRules: [
                v => !!v || 'Login é obrigatório',
                v => {
                    if(v.length < 11){
                        return 'Login não pode ter menos que 11 caracteres'
                    }
                }
            ],
            passwordRules: [
                v => !!v || 'Senha é obrigatória',
                v => {
                    if(v.length < 6){
                        return 'Senha não pode ter menos que 6 caracteres'
                    }
                }
            ],
        }
    },
    methods:{
       validaLogin(){
           if(!this.username || this.username.length < 11 || !this.password || this.password.length < 6){
               this.isButton = true;
           }else{
               this.isButton = false;
           }
       },
       login(){
           let obj = {
               username: this.username ? this.username : null,
               password: this.password ? this.password : null
           }
            var params = new URLSearchParams();
            params.append('grant_type', 'password');
            params.append('client', 'app-u2d-client');
            params.append('username', obj.username);
            params.append('password', obj.password);
            return this.$http({
                method: 'post',
                url:'oauth/token',
                auth:{username: 'app-u2d-client', password: 'u2d'},
                headers: {"Content-type": "application/x-www-form-urlencoded"},
                data: params
            }).then(response => {
                if(response.status === 200){
                    sessionStorage.setItem("token", response.data.access_token);
                    let jwtData = sessionStorage.getItem('token').split('.')[1];
                    let decodedJwtJsonData = atob(jwtData);
                    this.$http.get(`/usuario/${JSON.parse(decodedJwtJsonData).user_name}`)
                        .then(response => {
                             if(response.status === 200){
                                sessionStorage.setItem("user", JSON.stringify(response.data));
                                this.$router.push({ name: "home" });
                                location.reload();
                             }
                        })
                    
                }
            })
       }
    }
}
</script>