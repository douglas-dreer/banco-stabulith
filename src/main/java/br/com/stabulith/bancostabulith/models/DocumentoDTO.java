package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.TipoDocumentoEnum;
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
public class DocumentoDTO {
    private UUID id;
    private TipoDocumentoEnum tipoDocumento;
    private long numero;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private boolean isAtivo;

    public DocumentoDTO(TipoDocumentoEnum tipoDocumento, long numero) {
        this.tipoDocumento = tipoDocumento;
        this.numero = numero;
        this.dataCriacao = LocalDateTime.now();
        this.isAtivo = true;
    }
}
