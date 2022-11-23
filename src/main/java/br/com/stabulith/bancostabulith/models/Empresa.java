package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.EmpresaEntity;
import br.com.stabulith.bancostabulith.enums.TipoDocumento;
import br.com.stabulith.bancostabulith.enums.TipoEmpresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Empresa {
    private int id;
    private TipoEmpresa tipoEmpresa;
    private String razaoSocial;
    private String nomeFantasia;
    private TipoDocumento tipoDocumento;

    public EmpresaEntity convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, EmpresaEntity.class);
    }
}
