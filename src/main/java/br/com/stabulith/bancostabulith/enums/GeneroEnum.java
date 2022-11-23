package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum GeneroEnum {
    M("Masculino"),
    F("Feminino"),
    OUTROS("Outros");

    private String descricao;

    GeneroEnum buscarPorSigla(String name) {
        return GeneroEnum.valueOf(name);
    }
}
