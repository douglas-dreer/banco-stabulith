package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.TipoDocumento;
import br.com.stabulith.bancostabulith.enums.TipoEmpresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContaPessoaJuridica extends Conta {
    private TipoDocumento tipoDocumento;
    private TipoEmpresa tipoEmpresa;
    private int quantidadeTransferencia;
    private int quantidadeSaque;
    private boolean hasCheque;
    private boolean isContaConjunta;
}
