package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.enums.TipoEndereco;
import br.com.stabulith.bancostabulith.models.Endereco;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class EnderecoEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String logradouro;
    private int numero;
    private String complemento;
    private String pontoReferencia;
    private long CEP;
    private String cidade;
    private String estado;
    private TipoEndereco tipoEndereco;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean status;

    public Endereco convertToModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Endereco.class);
    }
}
