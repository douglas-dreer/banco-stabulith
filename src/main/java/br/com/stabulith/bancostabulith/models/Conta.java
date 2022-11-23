package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.ContaEntity;
import br.com.stabulith.bancostabulith.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    private int agencia;
    private int numeroConta;
    private int digitoVerificador;
    private TipoConta tipoConta;
    private String nomeTitular;
    private double valorLimite;
    private boolean hasPix;
    private boolean status;

    public ContaEntity convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ContaEntity.class);
    }


}
