package br.com.stabulith.bancostabulith.entities;

import br.com.stabulith.bancostabulith.enums.TipoTelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "STBLTH_003_TELEFONE_CONTATO")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelefoneContato {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "telefone_contato_id", updatable = false, nullable = false)
    private UUID id;

    @Column(length = 2)
    private int ddd;
    private long numero;
    private TipoTelefoneEnum tipoTelefoneContato;
    private int ramal;
    private boolean isRamal;


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
