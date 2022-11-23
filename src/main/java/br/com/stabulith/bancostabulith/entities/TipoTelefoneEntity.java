package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.enums.TipoTelefone;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tipo_telefone")
@Getter
@Setter
public class TipoTelefoneEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean status;

    public TipoTelefone convertToModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, TipoTelefone.class);
    }
}
