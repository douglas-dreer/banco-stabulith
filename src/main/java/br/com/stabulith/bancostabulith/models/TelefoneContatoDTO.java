package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.TipoTelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelefoneContatoDTO {
    private UUID id;

    private int ddd;
    private long numero;
    private TipoTelefoneEnum tipoTelefoneContato;
    private int ramal;
    private boolean isRamal;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private boolean isAtivo;

    public TelefoneContatoDTO(int ddd, long numero, TipoTelefoneEnum tipoTelefoneContato){
        this.ddd = ddd;
        this.numero = numero;
        this.tipoTelefoneContato = tipoTelefoneContato;
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }
    public TelefoneContatoDTO(int ddd, long numero, TipoTelefoneEnum tipoTelefoneContato, int ramal, boolean isRamal){
        this.ddd = ddd;
        this.numero = numero;
        this.tipoTelefoneContato = tipoTelefoneContato;
        this.ramal = ramal;
        this.isRamal = isRamal;
    }


}
