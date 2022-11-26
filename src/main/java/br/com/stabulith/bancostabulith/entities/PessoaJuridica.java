package br.com.stabulith.bancostabulith.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stblth_006_pessoa_juridica")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaJuridica {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "pessoa_fisica_id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String razaoSocial;
    private String nomeFantasia;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaFisica> funcionarios;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefoneContato> telefonesContato;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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
    public void preUpdate() {
        this.dataModificacao = LocalDateTime.now();
    }
}
