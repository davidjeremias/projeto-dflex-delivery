package com.u2d.projeto.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PermissaoDTO implements Serializable{
	
	private static final long serialVersionUID = 708100695666834545L;

	@EqualsAndHashCode.Include
	private Long id;
	
	private String descricao;

}
