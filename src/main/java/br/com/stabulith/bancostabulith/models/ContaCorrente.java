package br.com.stabulith.bancostabulith.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContaCorrente extends Conta {
    private int quantidadeTransferencia;
    private int quantidadeSaque;
    private boolean hasCheque;
    private boolean isContaConjunta;


}
