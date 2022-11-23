package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tipo_documento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoDocumentoEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String sigla;

    private String descricao;


    public TipoDocumentoEntity(TipoDocumento tipoDocumento) {
        this.sigla = tipoDocumento.name();
        this.descricao = tipoDocumento.getDescricao();
    }

    public TipoDocumento convertToModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, TipoDocumento.class);
    }
}
