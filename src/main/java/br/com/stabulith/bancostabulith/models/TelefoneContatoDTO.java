package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.TipoTelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelefoneContatoDTO {
    private UUID id;

    @Column(length = 2)
    private int ddd;
    private long numero;
    private TipoTelefoneEnum tipoTelefoneContato;
    private int ramal;
    private boolean isRamal;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private boolean isAtivo;
}
