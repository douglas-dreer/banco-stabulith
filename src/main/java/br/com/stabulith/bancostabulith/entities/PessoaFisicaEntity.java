package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.enums.Genero;
import br.com.stabulith.bancostabulith.models.PessoaFisica;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Entity
@Table(name = "pessoa_fisica")
@Getter
@Setter
public class PessoaFisicaEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String email;

    private String nomeCompleto;
    private int idade;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<DocumentoEntity> documentoList;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="afiliacao_id")
    private AfiliacaoEntity afiliacao;
    private Genero genero;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    @Cascade(SAVE_UPDATE)
    private List<ContatoTelefonicoEntity> contatoTelefonicoList;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    @Cascade(SAVE_UPDATE)
    private List<EnderecoEntity> enderecoList;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean isAtivo;

    @PrePersist
    public void prePersist(){
        if (dataModificacao == null) {
            dataModificacao = LocalDate.now();
        }
    }

    @PreUpdate
    public void preUpdate(){
        dataModificacao = LocalDate.now();
    }

    public PessoaFisica convertToModel(){
        ModelMapper modelMapper = new ModelMapper();
        PessoaFisica pessoaFisica = modelMapper.map(this, PessoaFisica.class);
        return pessoaFisica;

    }

}
