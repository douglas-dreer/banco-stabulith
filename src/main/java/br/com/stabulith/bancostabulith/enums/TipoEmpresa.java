package br.com.stabulith.bancostabulith.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TipoEmpresa {
    MEI("MEI"),
    MICROEMPRESA("Microempresa"),
    EMPRESA_PEQUENO_PORTE("EPP"),
    EMPRESARIO_INDIVIDUAL("Empresário Individual"),
    SOCIEDADE_LIMITADA_UNIPESSOAL("Sociedade Limitada Unipessoal"),
    SOCIEDADE_EMPRESARIA_LIMITADA("Sociedade Empresária Limitada"),
    SOCIEDADE_SIMPLES(""),
    SOCIEDADE_ANONIMA("S.A.");

    private String nome;


}
