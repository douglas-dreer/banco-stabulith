package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum TipoTelefoneEnum {
    PARTICULAR("Particular"),
    COMERCIAL("Comercial"),
    EMPRESARIAL("Empresarial"),
    RECADO("Recado"),
    OUTROS("Outros");

    private String descricao;

    GeneroEnum buscarPorSigla(String name) {
        return GeneroEnum.valueOf(name);
    }
}
