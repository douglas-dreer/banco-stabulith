package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.models.Documento;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "documentos")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DocumentoEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private long numeroDocumento;
    @OneToOne
    private TipoDocumentoEntity tipoDocumento;


    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean status;


    public Documento convertToModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Documento.class);
    }
}
