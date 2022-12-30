package br.com.stabulith.bancostabulith.models;

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
public class AfiliacaoDTO {
    private UUID id;

    private String nomeCompletoMae;
    private String nomeCompletoPai;
    private boolean isPaisDesconhecidos;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private boolean isAtivo;

    public AfiliacaoDTO(String nomeCompletoMae, String nomeCompletoPai) {
        this.nomeCompletoMae = nomeCompletoMae;
        this.nomeCompletoPai = nomeCompletoPai;
        this.isPaisDesconhecidos = false;
        this.dataCriacao = LocalDateTime.now();
        this.isAtivo = true;
    }
}
