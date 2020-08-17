package com.u2d.projeto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "TB001_ENDERECO")
public class Endereco implements Serializable{

	private static final long serialVersionUID = 166308449528778915L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CO_ENDERECO")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "CO_CEP")
	private String cep;

	@Column(name = "NO_LOGRADOURO")
	private String logradouro;

	@Column(name = "NO_COMPLEMENTO")
	private String complemento;

	@Column(name = "NO_BAIRRO")
	private String bairro;

	@Column(name = "NO_CIDADE")
	private String localidade;

	@Column(name = "NO_UF")
	private String uf;

	@Column(name = "CO_IBGE")
	private String ibge;
}
