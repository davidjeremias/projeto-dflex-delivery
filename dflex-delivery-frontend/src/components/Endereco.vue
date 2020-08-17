<template>
    <div>
        <TitleTab title='Endereço'></TitleTab>

        <v-row>
            <v-col cols="12" sm="6" md="3">
                <v-text-field outlined placeholder="99.999-999" v-mask="'##.###-###'" v-model="cep" label="CEP" @keyup="buscaEndereco"></v-text-field>
            </v-col>
        </v-row>

        <v-row style="height: 80px;">
            <v-col cols="4" sm="4" md="4">
                <v-text-field outlined v-model="logradouro" label="Logradouro"></v-text-field>
            </v-col>
            <v-col cols="4" sm="6" md="3">
                <v-text-field outlined v-model="numero" label="Número"></v-text-field>
            </v-col>
            <v-col cols="4" sm="6" md="3">
                <v-text-field outlined v-model="complemento" label="Complemento"></v-text-field>
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="4" sm="6" md="4">
                <v-text-field outlined v-model="bairro" label="Bairro"></v-text-field>
            </v-col>
            <v-col cols="4" sm="6" md="3">
                <v-text-field outlined v-model="cidade" label="Cidade"></v-text-field>
            </v-col>
            <v-col cols="4" sm="6" md="3">
                <v-text-field outlined v-model="uf" label="UF"></v-text-field>
            </v-col>
            
        </v-row>
    </div>
</template>

<script>
import TitleTab from '@/components/TitleTab.vue'
import methodsGlobais from '@/mixins/methodsGlobais.js'
import bus from '@/bus'
export default {
    name: 'Endereco',
    mixins: [methodsGlobais],
    components:{
        TitleTab
    },
    created(){
        bus.$on('endereco', endereco => {
            if(endereco !== null){
                this.extraiParametros(endereco);
            }
        })
    },
    data(){
        return{
            enderecoDTO: null,
            cep: null,
            logradouro: null,
            bairro: null,
            cidade: null,
            uf: null,
            complemento: null,
            numero: null
        }
    },
    methods:{
        buscaEndereco(){
            if(this.cep && this.cep.length >= 10){
                console.log(this.removeMascaraCEP( this.cep));
                this.$http.get('/endereco', {params: {cep: this.removeMascaraCEP( this.cep)}})
                    .then(response =>{
                        console.log(response);
                        if(response.status === 200){
                            this.extraiParametros(response.data);
                        }
                    })
                    .catch(erro =>{
                        console.log(erro);
                    })
            }
        },
        extraiParametros(enderecoDTO){
            this.cep = enderecoDTO.cep;
            this.logradouro = enderecoDTO.logradouro;
            this.bairro = enderecoDTO.bairro;
            this.cidade = enderecoDTO.localidade;
            this.uf = enderecoDTO.uf;
            this.complemento = enderecoDTO.complemento;
        }
    }
}
</script>