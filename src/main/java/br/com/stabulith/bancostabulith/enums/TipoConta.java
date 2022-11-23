package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TipoConta {
    CONTA_CORRENTE("Conta Corrente"),
        CONTA_POUPANCA("Conta Poupança");

    private String descricao;

}
