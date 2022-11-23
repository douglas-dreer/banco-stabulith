package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.AfiliacaoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Afiliacao {
    private String nomeCompletoMae;
    private Date dataNascimentoMae;
    private String nomeCompletoPai;
    private Date dataNascimentoPai;
    private boolean hasPaternidadeDeconhecida;
    private boolean hasMaternidadeDesconhecida;

    public AfiliacaoEntity convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AfiliacaoEntity.class);
    }
}
