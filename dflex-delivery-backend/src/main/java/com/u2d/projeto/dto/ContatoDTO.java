package com.u2d.projeto.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ContatoDTO implements Serializable {

    private static final long serialVersionUID = -6502322630101247492L;

    private Long id;

    @Size(max=11)
    private String telefoneMovel;

    @Size(max=11)
    private String telefoneFixo;

    @Size(max=100)
    private String email;
}
