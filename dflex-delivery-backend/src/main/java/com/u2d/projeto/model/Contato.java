package com.u2d.projeto.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "TB002_CONTATO")
public class Contato implements Serializable{

	private static final long serialVersionUID = -5638331205488770864L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "CO_CONTATO")
	@EqualsAndHashCode.Include
	private Long id;
	
	@Size(max=11)
	@Column(name = "NU_TELEFONE_MOVEL")
	private String telefoneMovel;
	
	@Size(max=11)
	@Column(name = "NU_TELEFONE_FIXO")
	private String telefoneFixo;
	
	@Size(max=100)
	@Column(name = "NO_EMAIL")
	private String email;
}
