<template>
    <div>
        <TitlePage title="Cadastro de Logista" tamanho="h5"></TitlePage>
        <v-card>
            <v-tabs v-model="tab" background-color="red" dark>
                <v-tab>Dados da Empresa</v-tab>
                <v-tab @click="setEndereco">Endereço</v-tab>
                <v-tab>Contato</v-tab>
            </v-tabs>

            <v-tabs-items v-model="tab">
                <v-tab-item>
                    <v-card flat>
                    <v-card-text>
                        <TitleTab title="Dados da Empresa"></TitleTab>
                        
                        <v-row>
                            <v-col cols="12" sm="6" md="3">
                                <v-text-field outlined placeholder="99.999.999/9999-99" v-mask="'##.###.###/####-##'" v-model="cnpj" label="CNPJ" @keyup="buscaEmpresa"></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row style="height: 80px;">
                            <v-col cols="4" sm="4" md="3">
                                <v-text-field outlined v-model="nomeFantasia" label="Nome Fantasia"></v-text-field>
                            </v-col>
                            <v-col cols="4" sm="6" md="4">
                                <v-text-field outlined v-model="razaoSocial" label="Razão Social"></v-text-field>
                            </v-col>
                            <v-col cols="4" sm="6" md="4">
                                <v-text-field outlined v-model="naturezaJuridica" label="Natureza Jurídica"></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="4" sm="4" md="3">
                                <v-text-field outlined placeholder="99/99/9999" v-mask="'##/##/####'" v-model="dataAbertura" label="Data de Abertura"></v-text-field>
                            </v-col>
                            <v-col cols="4" sm="6" md="4">
                                <v-text-field outlined v-model="porte" label="Porte"></v-text-field>
                            </v-col>
                            <v-col cols="4" sm="6" md="4">
                                <v-text-field outlined v-model="situacao" label="Situação"></v-text-field>
                            </v-col>
                        </v-row>
                    </v-card-text>
                    </v-card>
                </v-tab-item>
                <v-tab-item eager>
                    <v-card flat>
                    <v-card-text>
                        <Endereco enderecoDTO="enderecoDTO"></Endereco>
                    </v-card-text>
                    </v-card>
                </v-tab-item>
                <v-tab-item>
                    <v-card flat>
                    <v-card-text><TitleTab title="Contato"></TitleTab></v-card-text>
                    </v-card>
                </v-tab-item>
            </v-tabs-items>
        </v-card>

        <v-btn rounded right color="blue" absolute bottom>
            <span class="btn-form-save">Salvar</span>
        </v-btn>
        
    </div>
</template>

<script>
import TitlePage from '@/components/TitlePage.vue'
import TitleTab from '@/components/TitleTab.vue'
import Endereco from '@/components/Endereco.vue'
import methodsGlobais from '@/mixins/methodsGlobais.js'
import bus from '@/bus'

export default {
    name: 'CadastroEmpresa',
    mixins: [methodsGlobais],
    components:{
        TitlePage,
        TitleTab,
        Endereco
    },
    data(){
        return{
            cnpj: null,
            razaoSocial: null,
            nomeFantasia: null,
            porte: null,
            naturezaJuridica: null,
            dataAbertura: null,
            situacao: null,
            tab: null,
            enderecoDTO: null
        }
    },
    methods:{
        buscaEmpresa(){
            if(this.cnpj && this.cnpj.length >= 18){
                console.log(this.removeMascaraCNPJ(this.cnpj));
                this.$http
                    .get('/empresa/buscaPorCnpjReceita', {params:{cnpj: this.cnpj}})
                    .then(response =>{
                        console.log(response.data);
                        if(response.status === 200){
                            this.extraiParametros(response.data);
                        }
                    })
                    .catch(erro =>{
                        console.log(erro);
                    })
            }
        },
        extraiParametros(empresaDTO){
            this.cnpj = empresaDTO.cnpj;
            this.nomeFantasia = empresaDTO.fantasia;
            this.razaoSocial = empresaDTO.nome;
            this.naturezaJuridica = empresaDTO.natureza_juridica;
            this.dataAbertura = this.formatDateLocaleBR(empresaDTO.dataAbertura);
            this.porte = empresaDTO.porte;
            this.situacao = empresaDTO.situacao
            if(empresaDTO.enderecoDTO !== null){
                this.enderecoDTO = empresaDTO.enderecoDTO;
            }
        },
        setEndereco(){
            bus.$emit('endereco', this.enderecoDTO);
        }
    }
}
</script>
