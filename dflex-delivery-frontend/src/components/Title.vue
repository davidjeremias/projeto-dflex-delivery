<template>
<div>
    <h5>{{title}}</h5>
    <v-divider></v-divider>
</div>
    
</template>

<script>
export default {
    name: 'Title',
    props:{
        title:{
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
</style>