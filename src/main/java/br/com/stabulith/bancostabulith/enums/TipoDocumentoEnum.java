package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDocumentoEnum {
    CPF("Cadastro de Pessoa Física"),
    RG("Registro Geral"),
    CNH("Carteira Nacional de Habilitação"),
    IE("Inscrição Estadual"),
    CNPJ("Cadastro Nacional Pessoa Jurídica");

    private String descricao;
}
