package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.enums.TipoEnderecoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "STBLTH_004_ENDERECO")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "telefone_contato_id", updatable = false, nullable = false)
    private UUID id;

    private String logradouro;
    private int numero;
    private String complemento;
    private String pontoReferencia;
    private long cep;
    private String cidade;
    private String estado;
    private TipoEnderecoEnum tipoEndereco;

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
