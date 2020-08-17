<template>
    <div>
        <TitlePage title="Efetuar Venda"></TitlePage>

         <v-row style="height: 80px;" justify="center">
            <v-col cols="3" sm="3" md="3">
                <v-btn x-large color="primary" @click="onModalCartao('label')">
                    <v-icon>mdi-credit-card</v-icon>
                    Cartão Rico
                </v-btn>
            </v-col>

            <v-col cols="3" sm="3" md="3">
                <v-btn x-large color="primary" @click="onModalCartao('credito')">
                    <v-icon>mdi-credit-card</v-icon>
                    Cartão de Crédito
                </v-btn>
            </v-col>

            <v-col cols="3" sm="3" md="3">
                <v-btn x-large color="primary" @click="onModalCartao('debito')">
                    <v-icon>mdi-credit-card</v-icon>
                    Cartão de Débito
                </v-btn>
            </v-col>
        </v-row>

        <v-row justify="center">
            <v-dialog v-model="dialog" persistent max-width="290">
                <v-card>
                    <v-card-title class="headline">Use Google's location service?</v-card-title>
                    <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green darken-1" text @click="dialog = false">Disagree</v-btn>
                    <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>

    </div>
    
</template>

<script>
import TitlePage from '@/components/TitlePage.vue'
export default {
    name: 'EfetuarVenda',
    components:{
        TitlePage
    },
    data(){
        return{
            dialog: false,
        }
    },
    methods:{
        onModalCartao(tipo){
            this.dialog = true;
            console.log(tipo);
        }
    }
}
</script>3