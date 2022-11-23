package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Genero {
    M("Masculino"),
    F("Feminino"),
    OUTROS("Outros");

    private String descricaoPorExtenso;
}
