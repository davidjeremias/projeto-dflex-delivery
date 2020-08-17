package com.u2d.projeto.auditoria;

public enum TipoEventoAuditoriaEnum {

    BUSCA ("BUSCA"),
	ALTERACAO ("ALTERAÇÃO"),
	INSERCAO ("INSERÇÃO"),
	EXCLUSAO ("EXCLUSÃO"),
	UPLOAD ("UPLOAD"),
	DOWNLOAD ("DOWNLOAD"),
	SOLICITA_GERACAO("SOLICITAR GERAÇÃO"),
	ENVIO_PARA_ANALISE("ENVIO PARA ANÁLISE"),
	ANALISE("ANÁLISE");

    private String nome;

    private TipoEventoAuditoriaEnum (String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
