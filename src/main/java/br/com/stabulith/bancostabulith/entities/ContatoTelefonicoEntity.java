package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.models.ContatoTelefonico;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contato_telefonico")
@Getter
@Setter
public class ContatoTelefonicoEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private int ddd;
    private long numero;
    private int ramal;

    @OneToOne
    private TipoTelefoneEntity tipoTelefone;
    @OneToOne
    private TipoContatoTelefonicoEntity tipoContatoTelefonico;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean status;

    public ContatoTelefonico convertToModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ContatoTelefonico.class);
    }
}
