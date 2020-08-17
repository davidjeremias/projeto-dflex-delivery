const methodsGlobais = {
    methods:{
        removeMascaraCNPJ(cnpj){
            cnpj = cnpj.replace(".", "");
            cnpj = cnpj.replace(".", "");
            cnpj = cnpj.replace("-", "");
            cnpj = cnpj.replace("/", "");
        return cnpj;
        },
        removeMascaraCEP(cep){
            cep = cep.replace(".", "");
            cep = cep.replace("-", "");
        return cep;
        },
        formatDateLocaleBR(date){
            return date = this.$moment(date).format('DD/MM/YYYY')
        }
    }
}
export default methodsGlobais