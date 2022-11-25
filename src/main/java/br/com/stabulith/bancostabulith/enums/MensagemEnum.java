package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensagemEnum {
    NAO_FOI_ENCONTRADO_REGISTROS("Não foi encontrado nenhum registro com esse parâmetro"),
    PARAMETROS_INCORRETOS("Paramentro(s) enviado(s) está(ão) inválido(s) ou incorretos(s)"),
    ERRO_INTERNO("Houve um erro ao tentar executar a operação");

    private String descricao;
}
