package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum TipoEnderecoEnum {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial");

    private String descricao;

}
