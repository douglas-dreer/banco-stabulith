package br.com.stabulith.bancostabulith.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "STBLTH_002_AFILIACAO")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Afiliacao implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "afiliacao_id", updatable = false, nullable = false)
    private UUID id;

    private String nomeCompletoMae;
    private String nomeCompletoPai;

    @Column(name = "paisDesconhecido")
    private boolean isPaisDesconhecidos;

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
