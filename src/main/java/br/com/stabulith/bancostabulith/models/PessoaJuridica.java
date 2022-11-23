package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.ContaPessoaJuridicaEntity;
import br.com.stabulith.bancostabulith.enums.RamoAtividade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaJuridica extends Pessoa {
    private String nomeFantasia;
    private String razaoSocial;
    private Date dataAbertura;
    private Date dataFechamento;
    private RamoAtividade ramoAtividade;

    public ContaPessoaJuridicaEntity convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ContaPessoaJuridicaEntity.class);
    }
}
