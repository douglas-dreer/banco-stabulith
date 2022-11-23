package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.models.Afiliacao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "afiliacao")
@Getter
@Setter
@Transactional
public class AfiliacaoEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nomeCompletoMae;
    private Date dataNascimentoMae;
    private String nomeCompletoPai;
    private Date dataNascimentoPai;
    private boolean hasPaternidadeDeconhecida;
    private boolean hasMaternidadeDesconhecida;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean status;

    public Afiliacao convertToModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Afiliacao.class);
    }


}
