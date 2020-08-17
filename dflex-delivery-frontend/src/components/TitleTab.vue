<template>
    <div class="sp-h3">
        <h3>{{title}}</h3>
        <v-divider></v-divider>
    </div>
</template>

<script>
export default {
    name: 'TitleTab',
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
    h3{
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        font-size: 20px;
    }
    .sp-h3{
        height: 40px !important; 
    }

</style>