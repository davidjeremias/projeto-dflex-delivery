package com.u2d.projeto.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.u2d.projeto.model.Empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 5420741256083441157L;

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotNull
    @NotBlank
    @Size(max = 11, min = 11)
    private String cpf;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String email;
    
    @NotNull
    @NotBlank
    @Size(max = 6, min = 6)
    private String senha;
    
    @NotNull
    private Long idEmpresa;
    
    private Empresa empresa;
    
    @NotNull
    private List<PermissaoDTO> permissoes;
    
    
}


