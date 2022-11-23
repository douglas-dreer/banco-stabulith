package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.enums.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="STBLTH_001_PESSOA_FISICA")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PessoaFisica implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "pessoa_fisica_id", updatable = false, nullable = false)
    private UUID id;

    private String nomeCompleto;

    @Column(unique = true)
    private String email;

    @Column(length = 2)
    private int idade;

    private GeneroEnum genero;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    private Afiliacao afiliacao;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    private List<TelefoneContato> telefonesContato;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataModificacao;

    @Column(name = "status")
    private boolean isAtivo;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.isAtivo = true;
    }

    @PreUpdate
    public void preUpdate(){
        this.dataModificacao = LocalDateTime.now();
    }
}
