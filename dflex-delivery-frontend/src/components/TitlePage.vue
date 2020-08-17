<template>
    <div class="sp-h5">
        <h5>{{title}}</h5>
        <v-divider></v-divider>
    </div>
</template>

<script>
export default {
    name: 'TitlePage',
    props:{
        title:{
            type: String,
            default: ""
        },
        tamanho:{
            type: String,
            default: ""
        }
    }
}
</script>

<style scoped>
    h5{
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        font-size: 25px;
    }
    .sp-h5{
        height: 60px !important; 
    }

</style>