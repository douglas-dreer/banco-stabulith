package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TipoDocumento {
    CPF("Cadastro de Pessoa Física"),
    RG("Registro Geral"),
    CHN( "Carteira Habilitação Nacional"),
    TITULO_ELEITOR("Título Eleitor");

    private String descricao;
}
