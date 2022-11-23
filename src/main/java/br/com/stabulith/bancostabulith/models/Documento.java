package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.DocumentoEntity;
import br.com.stabulith.bancostabulith.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Documento {
    private UUID id;
    private long numeroDocumento;
    private TipoDocumento tipoDocumento;

   public Documento(long numeroDocumento, TipoDocumento tipoDocumento){
       this.numeroDocumento = numeroDocumento;
       this.tipoDocumento = tipoDocumento;
   }

    public DocumentoEntity convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, DocumentoEntity.class);
    }
}
