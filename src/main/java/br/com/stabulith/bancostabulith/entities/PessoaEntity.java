package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.models.Pessoa;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.UUID;

@Table
@Entity(name = "pessoa")
@Getter
@Setter
public class PessoaEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    public Pessoa convertToModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Pessoa.class);
    }
}
