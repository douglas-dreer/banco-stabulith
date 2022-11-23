package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.EnderecoEntity;
import br.com.stabulith.bancostabulith.enums.TipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco {
    private String logradouro;
    private int numero;
    private String complemento;
    private String pontoReferencia;
    private long CEP;
    private String cidade;
    private String estado;
    private TipoEndereco tipoEndereco;

    public EnderecoEntity convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, EnderecoEntity.class);
    }
}
